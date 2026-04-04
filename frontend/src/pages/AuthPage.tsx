import { FormEvent, useMemo, useState } from 'react';
import { AnimatePresence, motion } from 'framer-motion';
import { authApi } from '../api/auth';
import { getErrorMessage } from '../api/request';
import { ArrowRight, Loader2, Lock, Sparkles, User } from 'lucide-react';

type AuthMode = 'login' | 'register';

interface AuthPageProps {
  onAuthSuccess: () => void;
}

export default function AuthPage({ onAuthSuccess }: AuthPageProps) {
  const [mode, setMode] = useState<AuthMode>('login');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [submitting, setSubmitting] = useState(false);
  const [error, setError] = useState('');

  const title = useMemo(
    () => (mode === 'login' ? '欢迎返回控制舱' : '创建你的驾驶员身份'),
    [mode]
  );

  const subtitle = useMemo(
    () =>
      mode === 'login'
        ? '输入账号与密钥，连接 AI 面试中枢'
        : '注册后即可进入 AI 面试宇宙，开启任务',
    [mode]
  );

  const submitText = mode === 'login' ? '登录系统' : '创建账号';

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (!username.trim() || !password.trim()) {
      setError('请输入用户名和密码');
      return;
    }
    setSubmitting(true);
    setError('');
    try {
      const payload = { username: username.trim(), password };
      const res =
        mode === 'login'
          ? await authApi.login(payload)
          : await authApi.register(payload);
      localStorage.setItem('auth_user', JSON.stringify(res.user));
      localStorage.setItem('userId', String(res.user.id));
      sessionStorage.setItem('auth_session', '1');
      onAuthSuccess();
    } catch (err) {
      setError(getErrorMessage(err));
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <div className="relative min-h-screen overflow-hidden bg-[#050713] text-slate-100">
      <div className="absolute inset-0 bg-[radial-gradient(circle_at_20%_20%,rgba(99,102,241,0.35),transparent_45%),radial-gradient(circle_at_80%_30%,rgba(56,189,248,0.3),transparent_40%),radial-gradient(circle_at_50%_80%,rgba(168,85,247,0.25),transparent_40%)]" />
      <div className="absolute -left-32 top-16 h-72 w-72 rounded-full bg-primary-500/20 blur-3xl animate-pulse" />
      <div className="absolute -right-20 bottom-8 h-80 w-80 rounded-full bg-cyan-400/20 blur-3xl animate-pulse" />

      <div className="relative z-10 flex min-h-screen items-center justify-center px-4 py-10">
        <motion.div
          initial={{ opacity: 0, y: 16, scale: 0.98 }}
          animate={{ opacity: 1, y: 0, scale: 1 }}
          transition={{ duration: 0.35 }}
          className="w-full max-w-md rounded-3xl border border-primary-300/25 bg-slate-950/60 p-8 shadow-[0_0_60px_rgba(99,102,241,0.25)] backdrop-blur-xl"
        >
          <div className="mb-8 flex items-center gap-3">
            <div className="flex h-12 w-12 items-center justify-center rounded-2xl bg-gradient-to-br from-primary-500 to-cyan-400 text-white shadow-[0_0_30px_rgba(56,189,248,0.45)]">
              <Sparkles className="h-6 w-6" />
            </div>
            <div>
              <p className="text-sm uppercase tracking-[0.25em] text-cyan-300/80">AI Interview</p>
              <h1 className="text-lg font-semibold text-white">{title}</h1>
            </div>
          </div>

          <p className="mb-6 text-sm text-slate-300/85">{subtitle}</p>

          <div className="mb-6 grid grid-cols-2 rounded-xl border border-slate-700/80 bg-slate-900/80 p-1">
            <button
              type="button"
              onClick={() => setMode('login')}
              className={`rounded-lg py-2 text-sm font-medium transition ${
                mode === 'login'
                  ? 'bg-primary-500/90 text-white shadow-lg shadow-primary-500/30'
                  : 'text-slate-300 hover:text-white'
              }`}
            >
              登录
            </button>
            <button
              type="button"
              onClick={() => setMode('register')}
              className={`rounded-lg py-2 text-sm font-medium transition ${
                mode === 'register'
                  ? 'bg-primary-500/90 text-white shadow-lg shadow-primary-500/30'
                  : 'text-slate-300 hover:text-white'
              }`}
            >
              注册
            </button>
          </div>

          <form onSubmit={handleSubmit} className="space-y-4">
            <label className="block">
              <span className="mb-1.5 block text-xs uppercase tracking-wider text-slate-300">用户名</span>
              <div className="relative">
                <User className="pointer-events-none absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-cyan-300/70" />
                <input
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                  autoComplete="username"
                  className="w-full rounded-xl border border-slate-700/80 bg-slate-900/80 py-2.5 pl-10 pr-3 text-sm text-white outline-none transition focus:border-primary-400 focus:ring-2 focus:ring-primary-400/30"
                  placeholder="请输入用户名"
                />
              </div>
            </label>

            <label className="block">
              <span className="mb-1.5 block text-xs uppercase tracking-wider text-slate-300">密码</span>
              <div className="relative">
                <Lock className="pointer-events-none absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-cyan-300/70" />
                <input
                  type="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  autoComplete={mode === 'login' ? 'current-password' : 'new-password'}
                  className="w-full rounded-xl border border-slate-700/80 bg-slate-900/80 py-2.5 pl-10 pr-3 text-sm text-white outline-none transition focus:border-primary-400 focus:ring-2 focus:ring-primary-400/30"
                  placeholder={mode === 'login' ? '请输入密码' : '至少 6 位密码'}
                />
              </div>
            </label>

            <AnimatePresence mode="wait">
              {error ? (
                <motion.div
                  key={error}
                  initial={{ opacity: 0, y: -4 }}
                  animate={{ opacity: 1, y: 0 }}
                  exit={{ opacity: 0, y: -4 }}
                  className="rounded-xl border border-rose-500/40 bg-rose-500/10 px-3 py-2 text-sm text-rose-200"
                >
                  {error}
                </motion.div>
              ) : null}
            </AnimatePresence>

            <button
              disabled={submitting}
              type="submit"
              className="mt-2 flex w-full items-center justify-center gap-2 rounded-xl bg-gradient-to-r from-primary-500 to-cyan-400 px-4 py-2.5 text-sm font-semibold text-white shadow-[0_0_35px_rgba(59,130,246,0.45)] transition hover:brightness-110 disabled:cursor-not-allowed disabled:opacity-70"
            >
              {submitting ? (
                <>
                  <Loader2 className="h-4 w-4 animate-spin" />
                  提交中
                </>
              ) : (
                <>
                  {submitText}
                  <ArrowRight className="h-4 w-4" />
                </>
              )}
            </button>
          </form>
        </motion.div>
      </div>
    </div>
  );
}
