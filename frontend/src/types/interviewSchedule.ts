export type InterviewStatus = 'PENDING' | 'COMPLETED' | 'CANCELLED' | 'RESCHEDULED';

export interface InterviewSchedule {
  id: number;
  companyName: string;
  position: string;
  interviewTime: string;
  interviewType?: string;
  meetingLink?: string;
  roundNumber?: number;
  interviewer?: string;
  notes?: string;
  status: InterviewStatus;
  createdAt: string;
  updatedAt: string;
}

export interface CreateInterviewScheduleRequest {
  companyName: string;
  position: string;
  interviewTime: string;
  interviewType?: string;
  meetingLink?: string;
  roundNumber?: number;
  interviewer?: string;
  notes?: string;
}

export interface ParseScheduleResponse {
  success: boolean;
  data: CreateInterviewScheduleRequest | null;
  confidence: number;
  parseMethod: string;
  log: string;
}

