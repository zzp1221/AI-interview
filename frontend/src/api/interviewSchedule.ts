import { request } from './request';
import type {
  CreateInterviewScheduleRequest,
  InterviewSchedule,
  InterviewStatus,
  ParseScheduleResponse,
} from '../types/interviewSchedule';

export const interviewScheduleApi = {
  async list(params?: { status?: InterviewStatus; start?: string; end?: string }): Promise<InterviewSchedule[]> {
    return request.get<InterviewSchedule[]>('/api/interview-schedules', { params });
  },

  async detail(id: number): Promise<InterviewSchedule> {
    return request.get<InterviewSchedule>(`/api/interview-schedules/${id}`);
  },

  async create(payload: CreateInterviewScheduleRequest): Promise<InterviewSchedule> {
    return request.post<InterviewSchedule>('/api/interview-schedules', payload);
  },

  async update(id: number, payload: CreateInterviewScheduleRequest): Promise<InterviewSchedule> {
    return request.put<InterviewSchedule>(`/api/interview-schedules/${id}`, payload);
  },

  async remove(id: number): Promise<void> {
    return request.delete<void>(`/api/interview-schedules/${id}`);
  },

  async updateStatus(id: number, status: InterviewStatus): Promise<InterviewSchedule> {
    return request.patch<InterviewSchedule>(`/api/interview-schedules/${id}/status`, { status });
  },

  async parse(rawText: string, source?: string): Promise<ParseScheduleResponse> {
    return request.post<ParseScheduleResponse>('/api/interview-schedules/parse', { rawText, source });
  },
};

