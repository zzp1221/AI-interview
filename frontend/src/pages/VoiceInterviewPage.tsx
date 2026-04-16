import { useEffect, useMemo, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { voiceInterviewApi, VoiceInterviewWebSocket } from '../api/voiceInterview';
import { Loader2, Mic, PhoneOff, PlayCircle } from 'lucide-react';

export default function VoiceInterviewPage() {
  const navigate = useNavigate();
  const [sessionId, setSessionId] = useState<number | null>(null);
  const [connected, setConnected] = useState(false);
  const [starting, setStarting] = useState(false);
  const [subtitles, setSubtitles] = useState<string[]>([]);
  const [messages, setMessages] = useState<Array<{ role: 'ai' | 'user'; text: string }>>([]);
  const [draft, setDraft] = useState('');
  const [error, setError] = useState('');
  const wsRef = useRef<VoiceInterviewWebSocket | null>(null);
  const mediaRecorderRef = useRef<MediaRecorder | null>(null);
  const isRecordingRef = useRef(false);

  useEffect(() => {
    return () => {
      wsRef.current?.disconnect();
      if (mediaRecorderRef.current && mediaRecorderRef.current.state !== 'inactive') {
        mediaRecorderRef.current.stop();
      }
    };
  }, []);

  const wsUrl = useMemo(() => {
    if (!sessionId) return '';
    const protocol = window.location.protocol === 'https:' ? 'wss' : 'ws';
    const host = window.location.host || 'localhost:5173';
    return `${protocol}://${host.replace('5173', '8081')}/ws/voice-interview/${sessionId}`;
  }, [sessionId]);

  const start = async () => {
    setStarting(true);
    setError('');
    try {
      const session = await voiceInterviewApi.createSession({
        skillId: 'java-backend',
        difficulty: 'mid',
        techEnabled: true,
        projectEnabled: true,
        hrEnabled: true,
        plannedDuration: 20,
      });
      setSessionId(session.sessionId);
      const url = session.webSocketUrl?.startsWith('/ws/')
        ? `${window.location.protocol === 'https:' ? 'wss' : 'ws'}://${window.location.host.replace('5173', '8081')}${session.webSocketUrl}`
        : wsUrl;
      const ws = new VoiceInterviewWebSocket(url, {
        onOpen: () => setConnected(true),
        onClose: () => setConnected(false),
        onError: () => setError('语音连接失败'),
        onSubtitle: (text, isFinal) => {
          if (!text.trim()) return;
          setSubtitles((prev) => [...prev.slice(-3), text]);
          if (isFinal) {
            setMessages((prev) => [...prev, { role: 'user', text }]);
          }
        },
        onText: (content) => {
          setMessages((prev) => [...prev, { role: 'ai', text: content }]);
        },
      });
      ws.connect();
      wsRef.current = ws;
    } catch (e) {
      setError(e instanceof Error ? e.message : '创建语音会话失败');
    } finally {
      setStarting(false);
    }
  };

  const submit = () => {
    if (!draft.trim() || !wsRef.current) return;
    const text = draft.trim();
    setMessages((prev) => [...prev, { role: 'user', text }]);
    wsRef.current.submitText(text);
    setDraft('');
  };

  const toggleRecord = async () => {
    if (!wsRef.current) return;
    if (isRecordingRef.current) {
      mediaRecorderRef.current?.stop();
      isRecordingRef.current = false;
      return;
    }
    try {
      const stream = await navigator.mediaDevices.getUserMedia({ audio: true });
      const recorder = new MediaRecorder(stream);
      recorder.ondataavailable = async (evt) => {
        if (!evt.data || evt.data.size === 0 || !wsRef.current) return;
        const arr = await evt.data.arrayBuffer();
        let binary = '';
        const bytes = new Uint8Array(arr);
        for (let i = 0; i < bytes.length; i++) {
          binary += String.fromCharCode(bytes[i]);
        }
        wsRef.current.sendAudio(btoa(binary));
      };
      recorder.onstop = () => {
        stream.getTracks().forEach((t) => t.stop());
      };
      recorder.start(800);
      mediaRecorderRef.current = recorder;
      isRecordingRef.current = true;
    } catch {
      setError('无法访问麦克风');
    }
  };

  const finish = async () => {
    if (!sessionId) return;
    await voiceInterviewApi.endSession(sessionId);
    wsRef.current?.disconnect();
    navigate(`/voice-interview/${sessionId}/evaluation`);
  };

  return (
    <div className="space-y-4">
      <div className="flex items-center justify-between">
        <h1 className="text-2xl font-bold text-slate-800 dark:text-white">语音模拟面试</h1>
        {!sessionId && (
          <button onClick={start} disabled={starting} className="px-4 py-2 rounded-lg bg-primary-500 text-white">
            {starting ? <Loader2 className="w-4 h-4 animate-spin" /> : <span className="inline-flex items-center gap-1"><PlayCircle className="w-4 h-4" />开始</span>}
          </button>
        )}
      </div>

      {error && <div className="px-3 py-2 rounded-lg bg-red-50 text-red-600 dark:bg-red-900/20 dark:text-red-300">{error}</div>}
      <div className="text-sm text-slate-500 dark:text-slate-400">状态：{connected ? '已连接' : '未连接'}</div>

      <div className="grid grid-cols-1 lg:grid-cols-2 gap-4">
        <div className="bg-white dark:bg-slate-800 border border-slate-200 dark:border-slate-700 rounded-xl p-4">
          <h2 className="font-semibold mb-2 text-slate-800 dark:text-white">实时字幕</h2>
          <div className="min-h-24 text-sm text-slate-700 dark:text-slate-200 space-y-1">
            {subtitles.map((s, i) => <p key={i}>{s}</p>)}
            {subtitles.length === 0 && <p className="text-slate-400">等待语音输入...</p>}
          </div>
          <div className="mt-3 flex gap-2">
            <button onClick={toggleRecord} disabled={!connected} className="px-3 py-2 rounded-lg border border-slate-200 dark:border-slate-600">
              <span className="inline-flex items-center gap-1"><Mic className="w-4 h-4" />录音</span>
            </button>
          </div>
        </div>

        <div className="bg-white dark:bg-slate-800 border border-slate-200 dark:border-slate-700 rounded-xl p-4">
          <h2 className="font-semibold mb-2 text-slate-800 dark:text-white">对话</h2>
          <div className="h-56 overflow-auto space-y-2 text-sm">
            {messages.map((m, i) => (
              <div key={i} className={m.role === 'ai' ? 'text-slate-700 dark:text-slate-200' : 'text-primary-600 dark:text-primary-300'}>
                <span className="font-medium">{m.role === 'ai' ? '面试官' : '我'}：</span>{m.text}
              </div>
            ))}
          </div>
          <div className="mt-3 flex gap-2">
            <input
              value={draft}
              onChange={(e) => setDraft(e.target.value)}
              placeholder="也可直接输入文本回答"
              className="flex-1 px-3 py-2 rounded-lg border border-slate-200 dark:border-slate-600 bg-white dark:bg-slate-900"
            />
            <button onClick={submit} disabled={!connected} className="px-3 py-2 rounded-lg bg-primary-500 text-white">提交</button>
          </div>
        </div>
      </div>

      {sessionId && (
        <button onClick={finish} className="px-4 py-2 rounded-lg bg-red-500 text-white inline-flex items-center gap-1">
          <PhoneOff className="w-4 h-4" />结束并生成评估
        </button>
      )}
    </div>
  );
}

