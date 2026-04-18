import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { AuthResponse, HistoryItem } from '../models/index';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private baseUrl = `${environment.baseUrl}/api`;

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.baseUrl}/auth/login`, { username, password });
  }

  register(username: string, password: string, email?: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.baseUrl}/auth/register`, { username, password, email });
  }

  logout(): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/auth/logout`, {});
  }

  getProfile(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/auth/profile`);
  }

  convert(payload: { value: number; fromUnit: string; toUnit: string }): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/quantity/convert`, payload);
  }

  add(payload: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/quantity/arithmetic/add`, payload);
  }

  subtract(payload: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/quantity/arithmetic/subtract`, payload);
  }

  multiply(payload: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/quantity/arithmetic/multiply`, payload);
  }

  divide(payload: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/quantity/arithmetic/divide`, payload);
  }

  compare(payload: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/quantity/arithmetic/compare`, payload);
  }

  saveHistory(data: Partial<HistoryItem>): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/history/save`, data);
  }

  getMyHistory(): Observable<HistoryItem[]> {
    return this.http.get<HistoryItem[]>(`${this.baseUrl}/history/my`);
  }

  clearHistory(): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/history/clear`);
  }
}
