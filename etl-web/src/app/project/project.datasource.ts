import { DataSource } from '@angular/cdk/table';
import { ProjectListResponse, Project } from './project';
import { CollectionViewer } from '@angular/cdk/collections';
import { Observable, BehaviorSubject, of } from "rxjs";
import { ProjectService } from './project.service';
import { catchError, finalize } from "rxjs/operators";

export class ProjectDataSource implements DataSource<Project>{

    private projectSubject = new BehaviorSubject<Project[]>([]);
    private loadingSubject = new BehaviorSubject<boolean>(false);
    private countSubject = new BehaviorSubject<number>(0);
    public counter$ = this.countSubject.asObservable();

    constructor(private projectService: ProjectService) { }

    connect(collectionViewer: CollectionViewer): Observable<Project[]> {
        return this.projectSubject.asObservable();
    }

    disconnect(collectionViewer: CollectionViewer): void {
        this.projectSubject.complete();
        this.loadingSubject.complete();
        this.countSubject.complete();
    }

    loadData(page = 0, size = 10, sort = 'name,asc', search='') {
        this.loadingSubject.next(true);
        this.projectService.retrieve({ page, size, sort, search })
            .pipe(
                catchError(() => of([])),
                finalize(() => this.loadingSubject.next(false))
            )
            .subscribe((result: ProjectListResponse) => {
                this.projectSubject.next(result.content);
                this.countSubject.next(result.totalElements);
            }
            );
    }
}
