import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';
import { Scheduler } from './scheduler';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SchedulerService {
  apiURL: string = 'http://localhost:8480/api/scheduler';

  constructor(private httpClient: HttpClient) {}

  public getSchedulerById(id: number){}

  listSchedulers(request) {
    const endpoint = this.apiURL;
    const params = request;
    return this.httpClient.get(endpoint, { params });
  }

  saveScheduler(scheduler : Scheduler): Observable<Scheduler> {
     const endpoint = this.apiURL;
     let httpHeaders = new HttpHeaders()
          .set('Content-Type', 'application/json')
          .set('Cache-Control', 'no-cache');
     let options = {
          headers: httpHeaders
     };

    return this.httpClient.put<Scheduler>(endpoint, scheduler, options)
                          .subscribe(msg => {
                                        //this.postResult = msg;
                                        console.log(msg);
                                   },
                                   err=>{
                                        console.log(err); throw "";
                                        }
                                   );
 //               .pipe(
 //                     retry(1),
 //                     catchError(this.handleError)
 //               );
  }

 handleError(error) {
         console.log(error);
         let errorMessage = '';
         if (error.error instanceof ErrorEvent) {
             // client-side error
             errorMessage = `Error: ${error.error.message}`;
         } else {
             // server-side error
             errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
         }
         console.log(errorMessage);
         return throwError(errorMessage);
     }

}
