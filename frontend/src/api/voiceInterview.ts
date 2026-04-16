import { request } from './request';

export interface CreateSessionRequest {
  roleType?: string;
  skillId: string;
  difficulty?: string;
  customJdText?: string;
  resumeId?: number;
  introEnabled?: boolean;
  techEnabled?: boolean;
  projectEnabled?: boolean;
  hrEnabled?: boolean;
  plannedDuration?: number;
  llmProvider?: string;
}

export interface SessionResponse {
  sessionId: number;
  roleType: string;
  currentPhase: string;
  status: string;
  startTime: string;
  plannedDuration: number;
  webSocketUrl: string;
}

export interface InterviewMessage {
  id: number;
  sessionId: number;
  messageType: string;
  phase: string;
  userRecognizedText: string;
  aiGeneratedText: string;
  timestamp: string;
  sequenceNum: number;
}

export interface VoiceAnswerDetail {
  questionIndex: number;
  question: string;
  category: string;
  userAnswer: string;
  score: number;
  feedback: string;
  referenceAnswer?: string | null;
  keyPoints?: string[] | null;
}

export interface VoiceEvaluationDetail {
  sessionId: number;
  totalQuestions: number;
  overallScore: number;
  overallFeedback: string;
  strengths: string[];
  improvements: string[];
  answers: VoiceAnswerDetail[];
}

export interface EvaluationStatusResponse {
  evaluateStatus: string | null;
  evaluateError?: string | null;
  evaluation?: VoiceEvaluationDetail | null;
}

export interface SessionMeta {
  sessionId: number;
  roleType: string;
  status: string;
  currentPhase: string;
  createdAt: string;
  updatedAt: string;
  actualDuration?: number;
  messageCount: number;
  evaluateStatus?: string;
  evaluateError?: string;
}

export const voiceInterviewApi = {
  async createSession(data: CreateSessionRequest): Promise<SessionResponse> {
    return request.post<SessionResponse>('/api/voice-interview/sessions', data);
  },
  async getSession(sessionId: number): Promise<SessionResponse> {
    return request.get<SessionResponse>(`/api/voice-interview/sessions/${sessionId}`);
  },
  async endSession(sessionId: number): Promise<void> {
    return request.post<void>(`/api/voice-interview/sessions/${sessionId}/end`);
  },
  async getMessages(sessionId: number): Promise<InterviewMessage[]> {
    return request.get<InterviewMessage[]>(`/api/voice-interview/sessions/${sessionId}/messages`);
  },
  async getEvaluation(sessionId: number): Promise<EvaluationStatusResponse> {
    return request.get<EvaluationStatusResponse>(`/api/voice-interview/sessions/${sessionId}/evaluation`);
  },
  async generateEvaluation(sessionId: number): Promise<EvaluationStatusResponse> {
    return request.post<EvaluationStatusResponse>(`/api/voice-interview/sessions/${sessionId}/evaluation`);
  },
  async pauseSession(sessionId: number, reason = 'user_initiated'): Promise<void> {
    return request.put<void>(`/api/voice-interview/sessions/${sessionId}/pause`, { reason });
  },
  async resumeSession(sessionId: number): Promise<SessionResponse> {
    return request.put<SessionResponse>(`/api/voice-interview/sessions/${sessionId}/resume`);
  },
  async getAllSessions(status?: string): Promise<SessionMeta[]> {
    return request.get<SessionMeta[]>('/api/voice-interview/sessions', { params: { status } });
  },
  async deleteSession(sessionId: number): Promise<void> {
    return request.delete<void>(`/api/voice-interview/sessions/${sessionId}`);
  },
};

export interface WebSocketEventHandlers {
  onSubtitle?: (text: string, isFinal: boolean) => void;
  onText?: (content: string) => void;
  onOpen?: () => void;
  onClose?: () => void;
  onError?: (error: Event) => void;
}

export class VoiceInterviewWebSocket {
  private ws: WebSocket | null = null;
  constructor(private url: string, private handlers: WebSocketEventHandlers) {}

  connect() {
    this.ws = new WebSocket(this.url);
    this.ws.onopen = () => this.handlers.onOpen?.();
    this.ws.onclose = () => this.handlers.onClose?.();
    this.ws.onerror = (err) => this.handlers.onError?.(err);
    this.ws.onmessage = (event) => {
      try {
        const data = JSON.parse(event.data) as { type: string; text?: string; isFinal?: boolean; content?: string };
        if (data.type === 'subtitle' && data.text != null) {
          this.handlers.onSubtitle?.(data.text, !!data.isFinal);
        }
        if (data.type === 'text' && data.content != null) {
          this.handlers.onText?.(data.content);
        }
      } catch {
        // ignore malformed messages
      }
    };
  }

  sendAudio(base64: string) {
    if (this.ws?.readyState === WebSocket.OPEN) {
      this.ws.send(JSON.stringify({ type: 'audio', data: base64 }));
    }
  }

  submitText(text: string) {
    if (this.ws?.readyState === WebSocket.OPEN) {
      this.ws.send(JSON.stringify({ type: 'control', action: 'submit', data: { text } }));
    }
  }

  disconnect() {
    this.ws?.close();
    this.ws = null;
  }
}

