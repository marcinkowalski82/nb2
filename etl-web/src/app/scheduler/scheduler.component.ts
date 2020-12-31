import { Component, AfterViewInit, ViewChild, OnInit, HostListener } from '@angular/core';
import { tap } from 'rxjs/operators';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { SchedulerService } from './scheduler.service';
import { SchedulerDataSource } from './scheduler.datasource';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { SchedulerDialogComponent } from './scheduler-dialog.component';
import { Scheduler } from './scheduler';

@Component({
  selector: 'scheduler',
  templateUrl: 'scheduler.component.html',
  styleUrls: ['scheduler.component.css']
})
export class SchedulerComponent implements OnInit {
  displayedColumns: string[] = ['name', 'enabled', 'cron', 'className', 'getdetails'];
  dataSource: SchedulerDataSource;
  filter : string;

  constructor(private schedulerService: SchedulerService,
              public dialog: MatDialog){}

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngOnInit() {
    this.dataSource = new SchedulerDataSource(this.schedulerService);
    this.dataSource.loadData();
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

  applyFilter(filterValue: string) {
      filterValue = filterValue.trim();
      filterValue = filterValue.toLowerCase();
      this.loadData()
    }

  showDialog(element: Scheduler){
     const dialogRef = this.dialog.open(SchedulerDialogComponent, {
        data : element
      }).afterClosed().subscribe(result => {
          this.loadData();
      });
    }

    search(event) {
         this.filter =  event.target.value;
         this.loadData();
    }
}
