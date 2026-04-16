import { BrowserRouter, Navigate, Route, Routes, useLocation, useNavigate, useParams } from 'react-router-dom';
import Layout from './components/Layout';
import { useEffect, useState, Suspense, lazy } from 'react';
import { historyApi } from './api/history';
import type { UploadKnowledgeBaseResponse } from './api/knowledgebase';

// Lazy load components
const UploadPage = lazy(() => import('./pages/UploadPage'));
const HistoryList = lazy(() => import('./pages/HistoryPage'));
const ResumeDetailPage = lazy(() => import('./pages/ResumeDetailPage'));
const Interview = lazy(() => import('./pages/InterviewPage'));
const InterviewHistoryPage = lazy(() => import('./pages/InterviewHistoryPage'));
const InterviewSchedulePage = lazy(() => import('./pages/InterviewSchedulePage'));
const VoiceInterviewPage = lazy(() => import('./pages/VoiceInterviewPage'));
const VoiceInterviewEvaluationPage = lazy(() => import('./pages/VoiceInterviewEvaluationPage'));
const KnowledgeBaseQueryPage = lazy(() => import('./pages/KnowledgeBaseQueryPage'));
const KnowledgeBaseUploadPage = lazy(() => import('./pages/KnowledgeBaseUploadPage'));
const KnowledgeBaseManagePage = lazy(() => import('./pages/KnowledgeBaseManagePage'));
const AuthPage = lazy(() => import('./pages/AuthPage'));

const AUTH_USER_STORAGE_KEY = 'auth_user';
const USER_ID_STORAGE_KEY = 'userId';
const AUTH_SESSION_STORAGE_KEY = 'auth_session';

function clearAuthState() {
  localStorage.removeItem(AUTH_USER_STORAGE_KEY);
  localStorage.removeItem(USER_ID_STORAGE_KEY);
  sessionStorage.removeItem(AUTH_SESSION_STORAGE_KEY);
}

function hasValidAuthState(): boolean {
  const sessionFlag = sessionStorage.getItem(AUTH_SESSION_STORAGE_KEY);
  if (sessionFlag !== '1') {
    return false;
  }

  const rawAuthUser = localStorage.getItem(AUTH_USER_STORAGE_KEY);
  if (!rawAuthUser) {
    return false;
  }

  const storedUserId = localStorage.getItem(USER_ID_STORAGE_KEY)?.trim();
  if (storedUserId) {
    return true;
  }

  try {
    const parsed = JSON.parse(rawAuthUser) as { id?: number | string };
    const userId = parsed?.id;
    if (userId === undefined || userId === null) {
      return false;
    }
    const normalized = String(userId).trim();
    if (!normalized) {
      return false;
    }
    localStorage.setItem(USER_ID_STORAGE_KEY, normalized);
    return true;
  } catch {
    return false;
  }
}

// Loading component
const Loading = () => (
  <div className="flex items-center justify-center min-h-[50vh]">
    <div className="w-10 h-10 border-3 border-slate-200 border-t-primary-500 rounded-full animate-spin" />
  </div>
);

// 上传页面包装器
function UploadPageWrapper() {
  const navigate = useNavigate();

  const handleUploadComplete = (resumeId: number) => {
    // 异步模式：上传成功后跳转到简历库，让用户在列表中查看分析状态
    navigate('/history', { state: { newResumeId: resumeId } });
  };

  return <UploadPage onUploadComplete={handleUploadComplete} />;
}

// 历史记录列表包装器
function HistoryListWrapper() {
  const navigate = useNavigate();

  const handleSelectResume = (id: number) => {
    navigate(`/history/${id}`);
  };

  return <HistoryList onSelectResume={handleSelectResume} />;
}

// 简历详情包装器
function ResumeDetailWrapper() {
  const { resumeId } = useParams<{ resumeId: string }>();
  const navigate = useNavigate();

  if (!resumeId) {
    return <Navigate to="/history" replace />;
  }

  const handleBack = () => {
    navigate('/history');
  };

  const handleStartInterview = (resumeText: string, resumeId: number) => {
    navigate(`/interview/${resumeId}`, { state: { resumeText } });
  };

  return (
    <ResumeDetailPage
      resumeId={parseInt(resumeId, 10)}
      onBack={handleBack}
      onStartInterview={handleStartInterview}
    />
  );
}

// 模拟面试包装器
function InterviewWrapper() {
  const { resumeId } = useParams<{ resumeId: string }>();
  const navigate = useNavigate();
  const location = useLocation();
  const [resumeText, setResumeText] = useState<string>('');
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // 优先从location state获取resumeText
    const stateText = (location.state as { resumeText?: string })?.resumeText;
    if (stateText) {
      setResumeText(stateText);
      setLoading(false);
    } else if (resumeId) {
      // 如果没有，从API获取简历详情
      historyApi.getResumeDetail(parseInt(resumeId, 10))
        .then(resume => {
          setResumeText(resume.resumeText);
          setLoading(false);
        })
        .catch(err => {
          console.error('获取简历文本失败', err);
          setLoading(false);
        });
    } else {
      setLoading(false);
    }
  }, [resumeId, location.state]);

  if (!resumeId) {
    return <Navigate to="/history" replace />;
  }

  const handleBack = () => {
    // 尝试返回详情页，如果失败则返回历史列表
    navigate(`/history/${resumeId}`, { replace: false });
  };

  const handleInterviewComplete = () => {
    // 面试完成后跳转到面试记录页
    navigate('/interviews');
  };

  if (loading) {
    return (
      <div className="flex items-center justify-center min-h-screen">
        <div className="text-center">
          <div className="w-10 h-10 border-3 border-slate-200 border-t-primary-500 rounded-full mx-auto mb-4 animate-spin" />
          <p className="text-slate-500">加载中...</p>
        </div>
      </div>
    );
  }

  return (
    <Interview
      resumeText={resumeText}
      resumeId={parseInt(resumeId, 10)}
      onBack={handleBack}
      onInterviewComplete={handleInterviewComplete}
    />
  );
}

function ProtectedLayoutRoute() {
  const location = useLocation();
  const isAuthed = hasValidAuthState();
  if (!isAuthed) {
    clearAuthState();
    return <Navigate to="/auth" replace state={{ from: location }} />;
  }
  return <Layout />;
}

function AuthPageWrapper() {
  const navigate = useNavigate();
  const location = useLocation();
  const isAuthed = hasValidAuthState();

  if (isAuthed) {
    return <Navigate to="/upload" replace />;
  }

  const handleAuthSuccess = () => {
    const fromPath = (location.state as { from?: { pathname?: string } } | null)?.from?.pathname;
    navigate(fromPath || '/upload', { replace: true });
  };

  return <AuthPage onAuthSuccess={handleAuthSuccess} />;
}

function App() {
  return (
    <BrowserRouter>
      <Suspense fallback={<Loading />}>
        <Routes>
          <Route path="/auth" element={<AuthPageWrapper />} />
          <Route path="/" element={<ProtectedLayoutRoute />}>
            <Route index element={<Navigate to="/upload" replace />} />
            <Route path="upload" element={<UploadPageWrapper />} />
            <Route path="history" element={<HistoryListWrapper />} />
            <Route path="history/:resumeId" element={<ResumeDetailWrapper />} />
            <Route path="interviews" element={<InterviewHistoryWrapper />} />
            <Route path="interview-schedule" element={<InterviewSchedulePage />} />
            <Route path="voice-interview" element={<VoiceInterviewPage />} />
            <Route path="voice-interview/:sessionId/evaluation" element={<VoiceInterviewEvaluationPage />} />
            <Route path="interview/:resumeId" element={<InterviewWrapper />} />
            <Route path="knowledgebase" element={<KnowledgeBaseManagePageWrapper />} />
            <Route path="knowledgebase/upload" element={<KnowledgeBaseUploadPageWrapper />} />
            <Route path="knowledgebase/chat" element={<KnowledgeBaseQueryPageWrapper />} />
          </Route>
          <Route path="*" element={<Navigate to="/upload" replace />} />
        </Routes>
      </Suspense>
    </BrowserRouter>
  );
}

// 面试记录页面包装器
function InterviewHistoryWrapper() {
  const navigate = useNavigate();

  const handleBack = () => {
    navigate('/upload');
  };

  const handleViewInterview = async (sessionId: string, resumeId?: number) => {
    if (resumeId) {
      // 如果有简历ID，跳转到简历详情页的面试详情
      navigate(`/history/${resumeId}`, {
        state: { viewInterview: sessionId }
      });
    } else {
      // 否则尝试从面试详情中获取简历ID
      try {
        await historyApi.getInterviewDetail(sessionId);
        // 面试详情中没有简历ID，需要从其他地方获取
        // 暂时跳转到历史记录列表
        navigate('/history');
      } catch {
        navigate('/history');
      }
    }
  };

  return <InterviewHistoryPage onBack={handleBack} onViewInterview={handleViewInterview} />;
}

// 知识库管理页面包装器
function KnowledgeBaseManagePageWrapper() {
  const navigate = useNavigate();

  const handleUpload = () => {
    navigate('/knowledgebase/upload');
  };

  const handleChat = () => {
    navigate('/knowledgebase/chat');
  };

  return <KnowledgeBaseManagePage onUpload={handleUpload} onChat={handleChat} />;
}

// 知识库问答页面包装器
function KnowledgeBaseQueryPageWrapper() {
  const navigate = useNavigate();
  const location = useLocation();
  const isChatMode = location.pathname === '/knowledgebase/chat';

  const handleBack = () => {
    if (isChatMode) {
      navigate('/knowledgebase');
    } else {
      navigate('/upload');
    }
  };

  const handleUpload = () => {
    navigate('/knowledgebase/upload');
  };

  return <KnowledgeBaseQueryPage onBack={handleBack} onUpload={handleUpload} />;
}

// 知识库上传页面包装器
function KnowledgeBaseUploadPageWrapper() {
  const navigate = useNavigate();

  const handleUploadComplete = (_result: UploadKnowledgeBaseResponse) => {
    // 上传完成后返回管理页面
    navigate('/knowledgebase');
  };

  const handleBack = () => {
    navigate('/knowledgebase');
  };

  return <KnowledgeBaseUploadPage onUploadComplete={handleUploadComplete} onBack={handleBack} />;
}

export default App;
