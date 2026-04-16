import { useCallback, useEffect, useMemo, useState } from 'react';
import { interviewScheduleApi } from '../api/interviewSchedule';
import type {
  CreateInterviewScheduleRequest,
  InterviewSchedule,
  InterviewStatus,
} from '../types/interviewSchedule';
import { formatDateTime } from '../utils/date';
import { CalendarDays, Loader2, Plus, Save, Wand2 } from 'lucide-react';

const EMPTY_FORM: CreateInterviewScheduleRequest = {
  companyName: '',
  position: '',
  interviewTime: '',
  interviewType: 'VIDEO',
  meetingLink: '',
  roundNumber: 1,
  interviewer: '',
  notes: '',
};

const STATUS_OPTIONS: Array<{ value: InterviewStatus; label: string }> = [
  { value: 'PENDING', label: '待进行' },
  { value: 'COMPLETED', label: '已完成' },
  { value: 'CANCELLED', label: '已取消' },
  { value: 'RESCHEDULED', label: '已改期' },
];

function toInputDateTime(iso?: string): string {
  if (!iso) return '';
  const date = new Date(iso);
  if (Number.isNaN(date.getTime())) return '';
  const pad = (n: number) => String(n).padStart(2, '0');
  const yyyy = date.getFullYear();
  const mm = pad(date.getMonth() + 1);
  const dd = pad(date.getDate());
  const hh = pad(date.getHours());
  const min = pad(date.getMinutes());
  return `${yyyy}-${mm}-${dd}T${hh}:${min}`;
}

function toApiDateTime(input: string): string {
  if (!input) return input;
  return input.length === 16 ? `${input}:00` : input;
}

export default function InterviewSchedulePage() {
  const [items, setItems] = useState<InterviewSchedule[]>([]);
  const [loading, setLoading] = useState(true);
  const [saving, setSaving] = useState(false);
  const [error, setError] = useState('');
  const [statusFilter, setStatusFilter] = useState<'ALL' | InterviewStatus>('ALL');
  const [editingId, setEditingId] = useState<number | null>(null);
  const [form, setForm] = useState<CreateInterviewScheduleRequest>(EMPTY_FORM);
  const [rawText, setRawText] = useState('');
  const [parsing, setParsing] = useState(false);

  const loadSchedules = useCallback(async () => {
    setLoading(true);
    setError('');
    try {
      const data = await interviewScheduleApi.list(
        statusFilter === 'ALL' ? undefined : { status: statusFilter }
      );
      setItems(data);
    } catch (err) {
      setError(err instanceof Error ? err.message : '加载面试日程失败');
    } finally {
      setLoading(false);
    }
  }, [statusFilter]);

  useEffect(() => {
    loadSchedules();
  }, [loadSchedules]);

  const handleEdit = (item: InterviewSchedule) => {
    setEditingId(item.id);
    setForm({
      companyName: item.companyName,
      position: item.position,
      interviewTime: toInputDateTime(item.interviewTime),
      interviewType: item.interviewType ?? 'VIDEO',
      meetingLink: item.meetingLink ?? '',
      roundNumber: item.roundNumber ?? 1,
      interviewer: item.interviewer ?? '',
      notes: item.notes ?? '',
    });
  };

  const resetForm = () => {
    setEditingId(null);
    setForm(EMPTY_FORM);
  };

  const handleSubmit = async () => {
    if (!form.companyName.trim() || !form.position.trim() || !form.interviewTime) {
      setError('请填写公司、岗位和面试时间');
      return;
    }
    setSaving(true);
    setError('');
    try {
      const payload: CreateInterviewScheduleRequest = {
        ...form,
        interviewTime: toApiDateTime(form.interviewTime),
      };
      if (editingId) {
        await interviewScheduleApi.update(editingId, payload);
      } else {
        await interviewScheduleApi.create(payload);
      }
      resetForm();
      await loadSchedules();
    } catch (err) {
      setError(err instanceof Error ? err.message : '保存失败');
    } finally {
      setSaving(false);
    }
  };

  const handleDelete = async (id: number) => {
    if (!window.confirm('确认删除该面试日程吗？')) return;
    try {
      await interviewScheduleApi.remove(id);
      await loadSchedules();
    } catch (err) {
      setError(err instanceof Error ? err.message : '删除失败');
    }
  };

  const handleStatusChange = async (id: number, status: InterviewStatus) => {
    try {
      await interviewScheduleApi.updateStatus(id, status);
      await loadSchedules();
    } catch (err) {
      setError(err instanceof Error ? err.message : '更新状态失败');
    }
  };

  const handleParse = async () => {
    if (!rawText.trim()) {
      setError('请先粘贴邀约文本');
      return;
    }
    setParsing(true);
    setError('');
    try {
      const parsed = await interviewScheduleApi.parse(rawText, 'other');
      if (!parsed.success || !parsed.data) {
        setError(parsed.log || '解析失败，请手动填写');
        return;
      }
      setForm({
        ...parsed.data,
        interviewTime: toInputDateTime(parsed.data.interviewTime),
      });
    } catch (err) {
      setError(err instanceof Error ? err.message : '解析失败');
    } finally {
      setParsing(false);
    }
  };

  const title = useMemo(() => (editingId ? '编辑面试日程' : '新建面试日程'), [editingId]);

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-2xl font-bold text-slate-800 dark:text-white flex items-center gap-2">
            <CalendarDays className="w-6 h-6 text-primary-500" />
            面试日程
          </h1>
          <p className="text-slate-500 dark:text-slate-400 mt-1">统一管理待进行和历史面试安排</p>
        </div>
      </div>

      {error && (
        <div className="rounded-xl border border-red-200 bg-red-50 text-red-700 px-4 py-3 text-sm dark:bg-red-900/20 dark:border-red-800 dark:text-red-300">
          {error}
        </div>
      )}

      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        <div className="lg:col-span-2 bg-white dark:bg-slate-800 rounded-xl border border-slate-200 dark:border-slate-700">
          <div className="p-4 border-b border-slate-100 dark:border-slate-700 flex items-center justify-between">
            <h2 className="font-semibold text-slate-800 dark:text-white">日程列表</h2>
            <select
              value={statusFilter}
              onChange={(e) => setStatusFilter(e.target.value as 'ALL' | InterviewStatus)}
              className="px-3 py-2 text-sm rounded-lg border border-slate-200 dark:border-slate-600 bg-white dark:bg-slate-900 text-slate-700 dark:text-slate-200"
            >
              <option value="ALL">全部状态</option>
              {STATUS_OPTIONS.map((s) => (
                <option key={s.value} value={s.value}>
                  {s.label}
                </option>
              ))}
            </select>
          </div>

          {loading ? (
            <div className="py-12 flex items-center justify-center text-slate-500 dark:text-slate-400">
              <Loader2 className="w-5 h-5 animate-spin mr-2" />
              加载中...
            </div>
          ) : items.length === 0 ? (
            <div className="py-12 text-center text-slate-500 dark:text-slate-400">暂无日程</div>
          ) : (
            <div className="overflow-x-auto">
              <table className="w-full text-sm">
                <thead className="text-slate-500 dark:text-slate-400 border-b border-slate-100 dark:border-slate-700">
                  <tr>
                    <th className="text-left p-3">公司/岗位</th>
                    <th className="text-left p-3">时间</th>
                    <th className="text-left p-3">状态</th>
                    <th className="text-right p-3">操作</th>
                  </tr>
                </thead>
                <tbody>
                  {items.map((item) => (
                    <tr key={item.id} className="border-b border-slate-50 dark:border-slate-700">
                      <td className="p-3">
                        <div className="font-medium text-slate-800 dark:text-white">{item.companyName}</div>
                        <div className="text-slate-500 dark:text-slate-400">{item.position}</div>
                      </td>
                      <td className="p-3 text-slate-600 dark:text-slate-300">{formatDateTime(item.interviewTime)}</td>
                      <td className="p-3">
                        <select
                          value={item.status}
                          onChange={(e) => handleStatusChange(item.id, e.target.value as InterviewStatus)}
                          className="px-2 py-1 rounded-md border border-slate-200 dark:border-slate-600 bg-white dark:bg-slate-900 text-slate-700 dark:text-slate-200"
                        >
                          {STATUS_OPTIONS.map((s) => (
                            <option key={s.value} value={s.value}>
                              {s.label}
                            </option>
                          ))}
                        </select>
                      </td>
                      <td className="p-3 text-right space-x-2">
                        <button
                          onClick={() => handleEdit(item)}
                          className="px-3 py-1.5 rounded-md border border-slate-200 dark:border-slate-600 text-slate-700 dark:text-slate-200"
                        >
                          编辑
                        </button>
                        <button
                          onClick={() => handleDelete(item.id)}
                          className="px-3 py-1.5 rounded-md border border-red-200 text-red-600 dark:border-red-700 dark:text-red-300"
                        >
                          删除
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}
        </div>

        <div className="space-y-4">
          <div className="bg-white dark:bg-slate-800 rounded-xl border border-slate-200 dark:border-slate-700 p-4">
            <h2 className="font-semibold text-slate-800 dark:text-white mb-3">从邀约文本解析</h2>
            <textarea
              value={rawText}
              onChange={(e) => setRawText(e.target.value)}
              rows={5}
              placeholder="粘贴邮件/IM 邀约文本"
              className="w-full p-3 rounded-lg border border-slate-200 dark:border-slate-600 bg-white dark:bg-slate-900 text-slate-700 dark:text-slate-200"
            />
            <button
              onClick={handleParse}
              disabled={parsing}
              className="mt-3 w-full inline-flex items-center justify-center gap-2 px-3 py-2 rounded-lg bg-indigo-500 text-white disabled:opacity-60"
            >
              {parsing ? <Loader2 className="w-4 h-4 animate-spin" /> : <Wand2 className="w-4 h-4" />}
              解析填充表单
            </button>
          </div>

          <div className="bg-white dark:bg-slate-800 rounded-xl border border-slate-200 dark:border-slate-700 p-4">
            <h2 className="font-semibold text-slate-800 dark:text-white mb-3">{title}</h2>
            <div className="space-y-3">
              <input
                value={form.companyName}
                onChange={(e) => setForm({ ...form, companyName: e.target.value })}
                placeholder="公司名称"
                className="w-full px-3 py-2 rounded-lg border border-slate-200 dark:border-slate-600 bg-white dark:bg-slate-900 text-slate-700 dark:text-slate-200"
              />
              <input
                value={form.position}
                onChange={(e) => setForm({ ...form, position: e.target.value })}
                placeholder="岗位"
                className="w-full px-3 py-2 rounded-lg border border-slate-200 dark:border-slate-600 bg-white dark:bg-slate-900 text-slate-700 dark:text-slate-200"
              />
              <input
                type="datetime-local"
                value={form.interviewTime}
                onChange={(e) => setForm({ ...form, interviewTime: e.target.value })}
                className="w-full px-3 py-2 rounded-lg border border-slate-200 dark:border-slate-600 bg-white dark:bg-slate-900 text-slate-700 dark:text-slate-200"
              />
              <select
                value={form.interviewType ?? 'VIDEO'}
                onChange={(e) => setForm({ ...form, interviewType: e.target.value })}
                className="w-full px-3 py-2 rounded-lg border border-slate-200 dark:border-slate-600 bg-white dark:bg-slate-900 text-slate-700 dark:text-slate-200"
              >
                <option value="VIDEO">视频</option>
                <option value="ONSITE">现场</option>
                <option value="PHONE">电话</option>
              </select>
              <input
                value={form.meetingLink ?? ''}
                onChange={(e) => setForm({ ...form, meetingLink: e.target.value })}
                placeholder="会议链接（可选）"
                className="w-full px-3 py-2 rounded-lg border border-slate-200 dark:border-slate-600 bg-white dark:bg-slate-900 text-slate-700 dark:text-slate-200"
              />
              <textarea
                value={form.notes ?? ''}
                onChange={(e) => setForm({ ...form, notes: e.target.value })}
                rows={3}
                placeholder="备注（可选）"
                className="w-full px-3 py-2 rounded-lg border border-slate-200 dark:border-slate-600 bg-white dark:bg-slate-900 text-slate-700 dark:text-slate-200"
              />
            </div>
            <div className="mt-4 flex gap-2">
              <button
                onClick={handleSubmit}
                disabled={saving}
                className="flex-1 inline-flex items-center justify-center gap-2 px-3 py-2 rounded-lg bg-primary-500 text-white disabled:opacity-60"
              >
                {saving ? <Loader2 className="w-4 h-4 animate-spin" /> : editingId ? <Save className="w-4 h-4" /> : <Plus className="w-4 h-4" />}
                {editingId ? '保存修改' : '新增日程'}
              </button>
              {editingId && (
                <button
                  onClick={resetForm}
                  className="px-3 py-2 rounded-lg border border-slate-200 dark:border-slate-600 text-slate-700 dark:text-slate-200"
                >
                  取消
                </button>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
