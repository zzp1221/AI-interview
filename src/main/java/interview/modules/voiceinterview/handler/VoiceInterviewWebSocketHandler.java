package interview.modules.voiceinterview.handler;

import interview.modules.voiceinterview.dto.WebSocketControlMessage;
import interview.modules.voiceinterview.model.VoiceInterviewSessionEntity;
import interview.modules.voiceinterview.service.VoiceInterviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
@Slf4j
public class VoiceInterviewWebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final VoiceInterviewService interviewService;
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String sessionId = extractSessionId(session);
        sessions.put(sessionId, session);
        send(session, Map.of(
                "type", "control",
                "action", "welcome",
                "message", "语音连接已建立，请开始作答"
        ));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        JsonNode node = objectMapper.readTree(message.getPayload());
        String type = node.path("type").asText();
        String sessionId = extractSessionId(session);
        Long id = Long.parseLong(sessionId);

        if ("audio".equals(type)) {
            send(session, Map.of("type", "subtitle", "text", "已接收语音片段，可点击提交触发追问", "isFinal", false));
            return;
        }

        if ("control".equals(type)) {
            WebSocketControlMessage control = objectMapper.treeToValue(node, WebSocketControlMessage.class);
            handleControl(id, session, control);
        }
    }

    private void handleControl(Long sessionId, WebSocketSession session, WebSocketControlMessage control) {
        if (control == null || control.action() == null) {
            return;
        }
        switch (control.action()) {
            case "submit" -> {
                String userText = "";
                if (control.data() != null && control.data().get("text") instanceof String t) {
                    userText = t.trim();
                }
                if (userText.isBlank()) {
                    return;
                }
                VoiceInterviewSessionEntity entity = interviewService.getById(sessionId);
                String phase = entity == null ? "TECH" : entity.getCurrentPhase().name();
                String aiText = "收到你的回答。继续追问（" + phase + "）：请补充你在该场景中的技术取舍、风险点与量化结果。";
                interviewService.saveMessage(sessionId, userText, aiText);
                send(session, Map.of("type", "subtitle", "text", userText, "isFinal", true));
                send(session, Map.of("type", "text", "content", aiText));
            }
            case "end_interview" -> send(session, Map.of("type", "control", "action", "ended", "message", "面试已结束"));
            default -> log.debug("ignore control action={}", control.action());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(extractSessionId(session));
    }

    private void send(WebSocketSession session, Object payload) {
        try {
            if (session != null && session.isOpen()) {
                session.sendMessage(new TextMessage(objectMapper.writeValueAsString(payload)));
            }
        } catch (Exception e) {
            log.warn("发送WebSocket消息失败: {}", e.getMessage());
        }
    }

    private String extractSessionId(WebSocketSession session) {
        String path = session.getUri() == null ? "" : session.getUri().getPath();
        return path.substring(path.lastIndexOf('/') + 1);
    }
}

