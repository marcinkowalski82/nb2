import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  apiURL: string = 'http://localhost:8480/api/project';

  constructor(private httpClient: HttpClient) {}

  retrieve(request) {
    const endpoint = this.apiURL;
    const params = request;
    return this.httpClient.get(endpoint, { params });
  }
}
