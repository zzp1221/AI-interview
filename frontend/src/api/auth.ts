import { request } from './request';

export interface AuthUser {
  id: number;
  username: string;
}

export interface AuthKnowledgeBase {
  id: number;
  name: string;
  category?: string | null;
  sizeBytes?: number;
  chunkCount?: number;
  uploadedAt?: string;
  status?: string;
}

export interface AuthResponse {
  user: AuthUser;
  knowledgeBases: AuthKnowledgeBase[];
}

export interface AuthPayload {
  username: string;
  password: string;
}

export const authApi = {
  login(payload: AuthPayload): Promise<AuthResponse> {
    return request.post<AuthResponse>('/ai/interview/login', payload);
  },
  register(payload: AuthPayload): Promise<AuthResponse> {
    return request.post<AuthResponse>('/ai/interview/registant', payload);
  },
};
