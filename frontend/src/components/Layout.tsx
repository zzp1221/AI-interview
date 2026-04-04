import {Link, Outlet, useLocation} from 'react-router-dom';
import {motion} from 'framer-motion';
import {ChevronRight, Database, FileStack, MessageSquare, Moon, Sparkles, Sun, Upload, Users,} from 'lucide-react';
import {useTheme} from '../hooks/useTheme';

interface NavItem {
  id: string;
  path: string;
  label: string;
  icon: React.ComponentType<{ className?: string }>;
  description?: string;
}

interface NavGroup {
  id: string;
  title: string;
  items: NavItem[];
}

export default function Layout() {
  const location = useLocation();
  const currentPath = location.pathname;
    const {theme, toggleTheme} = useTheme();

  // 按业务模块组织的导航项
  const navGroups: NavGroup[] = [
    {
      id: 'career',
      title: '简历与面试',
      items: [
        { id: 'upload', path: '/upload', label: '上传简历', icon: Upload, description: 'AI 分析简历' },
        { id: 'resumes', path: '/history', label: '简历库', icon: FileStack, description: '管理所有简历' },
        { id: 'interviews', path: '/interviews', label: '面试记录', icon: Users, description: '查看面试历史' },
      ],
    },
    {
      id: 'knowledge',
      title: '知识库',
      items: [
        { id: 'kb-manage', path: '/knowledgebase', label: '知识库管理', icon: Database, description: '管理知识文档' },
        { id: 'chat', path: '/knowledgebase/chat', label: '问答助手', icon: MessageSquare, description: '基于知识库问答' },
      ],
    },
  ];

  // 判断当前页面是否匹配导航项
  const isActive = (path: string) => {
    if (path === '/upload') {
      return currentPath === '/upload' || currentPath === '/';
    }
    if (path === '/knowledgebase') {
      return currentPath === '/knowledgebase' || currentPath === '/knowledgebase/upload';
    }
    return currentPath.startsWith(path);
  };

  return (
      <div className="relative flex min-h-screen bg-slate-950 text-slate-100">
          <div className="pointer-events-none absolute inset-0 sci-fi-grid opacity-40" />
          <div className="pointer-events-none absolute -left-24 top-12 h-72 w-72 rounded-full bg-cyan-400/15 blur-3xl" />
          <div className="pointer-events-none absolute right-0 top-1/3 h-80 w-80 rounded-full bg-indigo-500/20 blur-3xl" />
          <aside
              className="w-72 bg-slate-950/80 border-r border-cyan-400/20 fixed h-screen left-0 top-0 z-50 flex flex-col backdrop-blur-xl">
        {/* Logo */}
              <div className="p-6 border-b border-cyan-400/20 flex items-center justify-between">
          <Link to="/upload" className="flex items-center gap-3">
            <div className="w-10 h-10 bg-gradient-to-br from-cyan-400 via-indigo-500 to-fuchsia-500 rounded-xl flex items-center justify-center text-white shadow-[0_0_20px_rgba(56,189,248,0.45)]">
              <Sparkles className="w-5 h-5" />
            </div>
            <div>
                <span className="text-lg font-bold sci-fi-title text-neon tracking-tight block">AI Interview</span>
                <span className="text-xs text-cyan-200/70">智能面试助手</span>
            </div>
          </Link>
        </div>

              {/* 主题切换按钮 */}
              <div className="px-4 py-3">
                  <button
                      onClick={toggleTheme}
                      className="w-full flex items-center justify-center gap-2 px-3 py-2.5 rounded-xl sci-fi-panel text-cyan-100 hover:bg-cyan-500/10 transition-all"
                  >
                      {theme === 'dark' ? (
                          <>
                              <Sun className="w-4 h-4"/>
                              <span className="text-sm font-medium">浅色模式</span>
                          </>
                      ) : (
                          <>
                              <Moon className="w-4 h-4"/>
                              <span className="text-sm font-medium">深色模式</span>
                          </>
                      )}
                  </button>
              </div>

        {/* 导航菜单 */}
        <nav className="flex-1 p-4 overflow-y-auto scrollbar-thin">
          <div className="space-y-6">
            {navGroups.map((group) => (
              <div key={group.id}>
                <div className="px-3 mb-2">
                  <span className="text-xs font-semibold text-cyan-200/60 uppercase tracking-[0.24em]">
                    {group.title}
                  </span>
                </div>
                <div className="space-y-1">
                  {group.items.map((item) => {
                    const active = isActive(item.path);
                    return (
                      <Link
                        key={item.id}
                        to={item.path}
                        className={`group relative flex items-center gap-3 px-3 py-3 rounded-xl transition-all duration-200 overflow-hidden
                          ${active
                            ? 'sci-fi-panel text-cyan-200 glow-border'
                            : 'text-slate-300 hover:bg-cyan-500/10 hover:text-cyan-100 border border-transparent hover:border-cyan-400/20'
                          }`}
                      >
                        <div className={`w-9 h-9 rounded-lg flex items-center justify-center transition-colors
                          ${active
                            ? 'bg-cyan-400/15 text-cyan-300'
                            : 'bg-slate-800 text-slate-400 group-hover:bg-cyan-400/15 group-hover:text-cyan-200'
                          }`}
                        >
                          <item.icon className="w-5 h-5" />
                        </div>
                        <div className="flex-1 min-w-0">
                          <span className={`text-sm block ${active ? 'font-semibold' : 'font-medium'}`}>
                            {item.label}
                          </span>
                          {item.description && (
                              <span className="text-xs text-slate-400 truncate block">
                              {item.description}
                            </span>
                          )}
                        </div>
                        {active && (
                          <ChevronRight className="w-4 h-4 text-cyan-300" />
                        )}
                      </Link>
                    );
                  })}
                </div>
              </div>
            ))}
          </div>
        </nav>

        {/* 底部信息 */}
              <div className="p-4 border-t border-cyan-400/20">
                  <div className="px-3 py-2 sci-fi-panel">
                      <p className="text-xs text-cyan-300 font-medium text-neon">AI 面试助手 v1.0</p>
                      <p className="text-xs text-slate-400 mt-0.5">Powered by AI</p>
          </div>
        </div>
      </aside>

      <main className="flex-1 ml-72 px-6 py-8 md:px-10 min-h-screen overflow-y-auto">
        <motion.div
          key={currentPath}
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          exit={{ opacity: 0, y: -20 }}
          transition={{ duration: 0.3 }}
          className="relative z-10"
        >
          <Outlet />
        </motion.div>
      </main>
    </div>
  );
}
