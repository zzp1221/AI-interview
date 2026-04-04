import {ChangeEvent, DragEvent, useCallback, useState} from 'react';
import {AnimatePresence, motion} from 'framer-motion';
import {AlertCircle, FileText, Loader2, Upload, X} from 'lucide-react';

export interface FileUploadCardProps {
  /** 标题 */
  title: string;
  /** 副标题 */
  subtitle: string;
  /** 接受的文件类型 */
  accept: string;
  /** 支持的格式说明 */
  formatHint: string;
  /** 最大文件大小说明 */
  maxSizeHint: string;
  /** 是否正在上传 */
  uploading?: boolean;
  /** 上传按钮文字 */
  uploadButtonText?: string;
  /** 选择按钮文字 */
  selectButtonText?: string;
  /** 是否显示名称输入框 */
  showNameInput?: boolean;
  /** 名称输入框占位符 */
  namePlaceholder?: string;
  /** 名称输入框标签 */
  nameLabel?: string;
  /** 错误信息 */
  error?: string;
  /** 文件选择回调 */
  onFileSelect?: (file: File) => void;
  /** 上传回调 */
  onUpload: (file: File, name?: string) => void;
  /** 返回回调 */
  onBack?: () => void;
}

export default function FileUploadCard({
  title,
  subtitle,
  accept,
  formatHint,
  maxSizeHint,
  uploading = false,
  uploadButtonText = '开始上传',
  selectButtonText = '选择文件',
  showNameInput = false,
  namePlaceholder = '留空则使用文件名',
  nameLabel = '名称（可选）',
  error,
  onFileSelect,
  onUpload,
  onBack,
}: FileUploadCardProps) {
  const [selectedFile, setSelectedFile] = useState<File | null>(null);
  const [dragOver, setDragOver] = useState(false);
  const [name, setName] = useState('');

  const handleDragOver = useCallback((e: DragEvent) => {
    e.preventDefault();
    setDragOver(true);
  }, []);

  const handleDragLeave = useCallback((e: DragEvent) => {
    e.preventDefault();
    setDragOver(false);
  }, []);

  const handleDrop = useCallback((e: DragEvent) => {
    e.preventDefault();
    setDragOver(false);
    const files = e.dataTransfer.files;
    if (files.length > 0) {
      setSelectedFile(files[0]);
      onFileSelect?.(files[0]);
    }
  }, [onFileSelect]);

  const handleFileChange = useCallback((e: ChangeEvent<HTMLInputElement>) => {
    const files = e.target.files;
    if (files && files.length > 0) {
      setSelectedFile(files[0]);
      onFileSelect?.(files[0]);
    }
  }, [onFileSelect]);

  const handleUpload = () => {
    if (!selectedFile) return;
    onUpload(selectedFile, name.trim() || undefined);
  };

  const formatFileSize = (bytes: number): string => {
    if (bytes < 1024) return bytes + ' B';
    if (bytes < 1024 * 1024) return (bytes / 1024).toFixed(1) + ' KB';
    return (bytes / (1024 * 1024)).toFixed(1) + ' MB';
  };

  return (
    <motion.div
      className="max-w-4xl mx-auto pt-10 md:pt-14"
      initial={{ opacity: 0, y: 20 }}
      animate={{ opacity: 1, y: 0 }}
      transition={{ duration: 0.5 }}
    >
      {/* 标题 */}
      <div className="text-center mb-10">
        <motion.h1
            className="text-4xl md:text-5xl font-bold sci-fi-title text-neon mb-4 tracking-tight"
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.1 }}
        >
          {title}
        </motion.h1>
        <motion.p
            className="text-lg text-cyan-100/70"
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ delay: 0.2 }}
        >
          {subtitle}
        </motion.p>
      </div>

      {/* 上传区域 */}
      <motion.div
          className={`relative sci-fi-panel rounded-3xl p-8 md:p-12 cursor-pointer transition-all duration-300 overflow-hidden
          ${dragOver ? 'scale-[1.01] shadow-[0_0_40px_rgba(34,211,238,0.35)] border-cyan-300/60' : 'hover:shadow-[0_0_28px_rgba(99,102,241,0.35)]'}`}
        onDragOver={handleDragOver}
        onDragLeave={handleDragLeave}
        onDrop={handleDrop}
        onClick={() => document.getElementById('file-upload-input')?.click()}
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ delay: 0.3 }}
      >
        {/* 渐变边框效果 */}
        <div className="pointer-events-none absolute inset-0 bg-[radial-gradient(circle_at_top,rgba(56,189,248,0.14),transparent_52%)]" />
        <div className="pointer-events-none absolute -right-12 -top-12 h-36 w-36 rounded-full bg-fuchsia-500/15 blur-3xl" />
        <div className="pointer-events-none absolute -left-16 -bottom-16 h-44 w-44 rounded-full bg-cyan-400/15 blur-3xl" />

        <input
          type="file"
          id="file-upload-input"
          className="hidden"
          accept={accept}
          onChange={handleFileChange}
          disabled={uploading}
        />

        <div className="text-center">
          <AnimatePresence mode="wait">
            {selectedFile ? (
              <motion.div
                key="file-selected"
                initial={{ opacity: 0, scale: 0.9 }}
                animate={{ opacity: 1, scale: 1 }}
                exit={{ opacity: 0, scale: 0.9 }}
                className="space-y-4"
              >
                <div
                    className="w-20 h-20 mx-auto bg-cyan-400/15 rounded-full flex items-center justify-center border border-cyan-300/30">
                  <FileText className="w-10 h-10 text-cyan-300"/>
                </div>
                <div
                    className="flex items-center justify-center gap-4 bg-slate-900/60 border border-cyan-400/20 px-6 py-4 rounded-xl max-w-md mx-auto">
                  <div className="text-left flex-1 min-w-0">
                    <p className="font-semibold text-cyan-100 truncate">{selectedFile.name}</p>
                    <p className="text-sm text-slate-400">{formatFileSize(selectedFile.size)}</p>
                  </div>
                  <button
                      className="w-8 h-8 bg-rose-500/20 text-rose-300 rounded-lg hover:bg-rose-500/30 transition-colors flex items-center justify-center"
                    onClick={(e) => {
                      e.stopPropagation();
                      setSelectedFile(null);
                    }}
                  >
                    <X className="w-4 h-4" />
                  </button>
                </div>
              </motion.div>
            ) : (
              <motion.div
                key="no-file"
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                exit={{ opacity: 0 }}
                className="space-y-4"
              >
                <motion.div
                  className={`w-20 h-20 mx-auto rounded-2xl flex items-center justify-center transition-colors
                    ${dragOver ? 'bg-cyan-400/20 text-cyan-200 border border-cyan-300/40' : 'bg-slate-800 text-slate-400 border border-slate-700'}`}
                  animate={{ y: dragOver ? -5 : 0 }}
                >
                  <Upload className="w-10 h-10" />
                </motion.div>
                <div>
                  <h3 className="text-xl font-semibold text-cyan-100 mb-2">点击或拖拽文件至此处</h3>
                  <p className="text-slate-400 mb-4">
                    {formatHint}（{maxSizeHint}）
                  </p>
                </div>
                <motion.button
                  className="bg-gradient-to-r from-cyan-500 via-indigo-500 to-fuchsia-500 text-white px-8 py-3.5 rounded-xl font-semibold shadow-[0_0_20px_rgba(56,189,248,0.4)] hover:shadow-[0_0_24px_rgba(217,70,239,0.45)] transition-all"
                  whileHover={{ scale: 1.02, y: -2 }}
                  whileTap={{ scale: 0.98 }}
                  onClick={(e) => {
                    e.stopPropagation();
                    document.getElementById('file-upload-input')?.click();
                  }}
                >
                  {selectButtonText}
                </motion.button>
              </motion.div>
            )}
          </AnimatePresence>
        </div>
      </motion.div>

      {/* 名称输入框 */}
      {showNameInput && selectedFile && (
        <motion.div
            className="mt-6 sci-fi-panel rounded-2xl p-6"
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
        >
          <label className="block text-sm font-semibold text-cyan-100 mb-2">{nameLabel}</label>
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder={namePlaceholder}
            className="w-full px-4 py-3 border border-cyan-400/25 rounded-xl focus:outline-none focus:ring-2 focus:ring-cyan-400/60 focus:border-transparent bg-slate-900/70 text-cyan-100 placeholder-slate-500"
            disabled={uploading}
            onClick={(e) => e.stopPropagation()}
          />
        </motion.div>
      )}

      {/* 错误提示 */}
      <AnimatePresence>
        {error && (
          <motion.div
            initial={{ opacity: 0, y: -10 }}
            animate={{ opacity: 1, y: 0 }}
            exit={{ opacity: 0, y: -10 }}
            className="mt-6 p-4 bg-rose-500/10 border border-rose-400/30 rounded-xl text-rose-200 text-center flex items-center justify-center gap-2"
          >
            <AlertCircle className="w-5 h-5" />
            {error}
          </motion.div>
        )}
      </AnimatePresence>

      {/* 操作按钮 */}
      <div className="mt-8 flex gap-4 justify-center">
        {onBack && (
          <motion.button
            onClick={onBack}
            className="px-6 py-3 border border-cyan-400/30 rounded-xl text-cyan-100 font-medium hover:bg-cyan-500/10 transition-all"
            whileHover={{ scale: 1.02 }}
            whileTap={{ scale: 0.98 }}
          >
            返回
          </motion.button>
        )}
        {selectedFile && (
          <motion.button
            onClick={handleUpload}
            disabled={uploading}
            className="px-8 py-3 bg-gradient-to-r from-emerald-400 to-cyan-500 text-slate-950 rounded-xl font-semibold shadow-[0_0_20px_rgba(16,185,129,0.45)] hover:shadow-[0_0_24px_rgba(56,189,248,0.45)] transition-all disabled:opacity-60 disabled:cursor-not-allowed flex items-center gap-2"
            whileHover={{ scale: uploading ? 1 : 1.02 }}
            whileTap={{ scale: uploading ? 1 : 0.98 }}
          >
            {uploading ? (
              <>
                <Loader2 className="w-5 h-5 animate-spin" />
                处理中...
              </>
            ) : (
              uploadButtonText
            )}
          </motion.button>
        )}
      </div>
    </motion.div>
  );
}
