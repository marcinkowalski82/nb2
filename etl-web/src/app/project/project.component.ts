import { Component, AfterViewInit, ViewChild, OnInit, HostListener } from '@angular/core';
import { tap } from 'rxjs/operators';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { ProjectService } from './project.service';
import { ProjectDataSource } from './project.datasource';
import { Project } from './project';
import { FormsModule, ReactiveFormsModule, FormGroup, Validators, FormControl } from '@angular/forms';

@Component({
  selector: 'project',
  templateUrl: 'project.component.html',
  styleUrls: ['project.component.css']
})
export class ProjectComponent implements OnInit {
  displayedColumns: string[] = ['name', 'type'];
  dataSource: ProjectDataSource;
  filter : string;
    formGroup: FormGroup;

  constructor(private projectService: ProjectService){}

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngOnInit() {
    this.dataSource = new ProjectDataSource(this.projectService);
    this.dataSource.loadData();
     this.formGroup = new FormGroup({ id: new FormControl([Validators.required])
                                       });
  }

  ngAfterViewInit() {
    this.dataSource.counter$
      .pipe(tap((count) => {this.paginator.length = count;}))
      .subscribe();

    this.paginator.page
      .pipe(tap(() => this.loadData()))
      .subscribe();

    this.sort.sortChange
      .pipe(tap(() => this.loadData()))
      .subscribe();
  }

  loadData() {
    this.dataSource.loadData(this.paginator.pageIndex, this.paginator.pageSize, this.sort.active + ',' + this.sort.direction, this.filter);
  }
  search() {

  }
}
