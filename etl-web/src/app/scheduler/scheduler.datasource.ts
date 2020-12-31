import { DataSource } from '@angular/cdk/table';
import { SchedulerListResponse, Scheduler } from './scheduler';
import { CollectionViewer } from '@angular/cdk/collections';
import { Observable, BehaviorSubject, of } from "rxjs";
import { SchedulerService } from './scheduler.service';
import { catchError, finalize } from "rxjs/operators";

export class SchedulerDataSource implements DataSource<Scheduler>{

    private schedulerSubject = new BehaviorSubject<Scheduler[]>([]);
    private loadingSubject = new BehaviorSubject<boolean>(false);
    private countSubject = new BehaviorSubject<number>(0);
    public counter$ = this.countSubject.asObservable();

    constructor(private schedulerService: SchedulerService) { }

    connect(collectionViewer: CollectionViewer): Observable<Scheduler[]> {
        return this.schedulerSubject.asObservable();
    }

    disconnect(collectionViewer: CollectionViewer): void {
        this.schedulerSubject.complete();
        this.loadingSubject.complete();
        this.countSubject.complete();
    }

    loadData(page = 0, size = 10, sort = 'name,asc', search='') {
        this.loadingSubject.next(true);
        this.schedulerService.listSchedulers({ page, size, sort, search })
            .pipe(
                catchError(() => of([])),
                finalize(() => this.loadingSubject.next(false))
            )
            .subscribe((result: SchedulerListResponse) => {
                this.schedulerSubject.next(result.content);
                this.countSubject.next(result.totalElements);
            }
            );
    }

    saveScheduler(scheduler: Scheduler) {
       this.schedulerService.saveScheduler(scheduler);
    }
}
