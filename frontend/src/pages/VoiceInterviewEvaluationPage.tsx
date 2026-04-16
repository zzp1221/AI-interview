import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { voiceInterviewApi, type VoiceEvaluationDetail } from '../api/voiceInterview';
import { ArrowLeft, Loader2, RefreshCw } from 'lucide-react';

export default function VoiceInterviewEvaluationPage() {
  const { sessionId } = useParams<{ sessionId: string }>();
  const navigate = useNavigate();
  const [loading, setLoading] = useState(true);
  const [status, setStatus] = useState<string | null>(null);
  const [error, setError] = useState('');
  const [detail, setDetail] = useState<VoiceEvaluationDetail | null>(null);

  useEffect(() => {
    let timer: number | null = null;

    const poll = async () => {
      if (!sessionId) return;
      try {
        const id = Number(sessionId);
        const result = await voiceInterviewApi.getEvaluation(id);
        setStatus(result.evaluateStatus ?? null);
        if (result.evaluateStatus === 'COMPLETED' && result.evaluation) {
          setDetail(result.evaluation);
          setLoading(false);
          return;
        }
        if (result.evaluateStatus === 'FAILED') {
          setError(result.evaluateError ?? '评估失败');
          setLoading(false);
          return;
        }
      } catch {
        try {
          await voiceInterviewApi.generateEvaluation(Number(sessionId));
        } catch {
          setError('触发评估失败');
          setLoading(false);
          return;
        }
      }
      timer = window.setTimeout(poll, 2500);
    };

    poll();
    return () => {
      if (timer) window.clearTimeout(timer);
    };
  }, [sessionId]);

  const retry = async () => {
    if (!sessionId) return;
    setLoading(true);
    setError('');
    await voiceInterviewApi.generateEvaluation(Number(sessionId));
  };

  if (loading) {
    return (
      <div className="min-h-[40vh] flex items-center justify-center text-slate-600 dark:text-slate-300">
        <Loader2 className="w-5 h-5 animate-spin mr-2" />
        {status === 'PROCESSING' ? '正在评估语音面试...' : '准备评估中...'}
      </div>
    );
  }

  if (error) {
    return (
      <div className="space-y-4">
        <button onClick={() => navigate('/interviews')} className="inline-flex items-center gap-1 text-slate-500 hover:text-slate-700">
          <ArrowLeft className="w-4 h-4" /> 返回
        </button>
        <div className="p-4 rounded-xl bg-red-50 text-red-600 dark:bg-red-900/20 dark:text-red-300">{error}</div>
        <button onClick={retry} className="px-4 py-2 rounded-lg bg-primary-500 text-white inline-flex items-center gap-1">
          <RefreshCw className="w-4 h-4" /> 重试
        </button>
      </div>
    );
  }

  if (!detail) return null;

  return (
    <div className="space-y-4">
      <button onClick={() => navigate('/interviews')} className="inline-flex items-center gap-1 text-slate-500 hover:text-slate-700">
        <ArrowLeft className="w-4 h-4" /> 返回
      </button>
      <h1 className="text-2xl font-bold text-slate-800 dark:text-white">语音面试评估报告</h1>

      <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
        <div className="p-4 rounded-xl border border-slate-200 dark:border-slate-700 bg-white dark:bg-slate-800">
          <div className="text-sm text-slate-500">综合得分</div>
          <div className="text-3xl font-bold text-primary-500">{detail.overallScore}</div>
        </div>
        <div className="p-4 rounded-xl border border-slate-200 dark:border-slate-700 bg-white dark:bg-slate-800">
          <div className="text-sm text-slate-500">问答数量</div>
          <div className="text-3xl font-bold text-slate-800 dark:text-white">{detail.totalQuestions}</div>
        </div>
        <div className="p-4 rounded-xl border border-slate-200 dark:border-slate-700 bg-white dark:bg-slate-800">
          <div className="text-sm text-slate-500">总体反馈</div>
          <div className="text-sm text-slate-700 dark:text-slate-200 mt-1">{detail.overallFeedback}</div>
        </div>
      </div>

      <div className="p-4 rounded-xl border border-slate-200 dark:border-slate-700 bg-white dark:bg-slate-800 space-y-2">
        <div className="font-semibold text-slate-800 dark:text-white">优势</div>
        {detail.strengths.map((s, i) => <div key={i} className="text-sm text-slate-700 dark:text-slate-200">- {s}</div>)}
      </div>

      <div className="p-4 rounded-xl border border-slate-200 dark:border-slate-700 bg-white dark:bg-slate-800 space-y-2">
        <div className="font-semibold text-slate-800 dark:text-white">改进建议</div>
        {detail.improvements.map((s, i) => <div key={i} className="text-sm text-slate-700 dark:text-slate-200">- {s}</div>)}
      </div>
    </div>
  );
}

