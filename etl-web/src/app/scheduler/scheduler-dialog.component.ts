import { Component, OnInit, Optional, Inject } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { SchedulerService } from './scheduler.service';
import { SchedulerDataSource } from './scheduler.datasource';
import { Scheduler } from './scheduler';
import { MatSlideToggleModule, MatSlideToggle, MatSlideToggleChange } from '@angular/material/slide-toggle';
import { FormsModule, ReactiveFormsModule, FormGroup, Validators, FormControl } from '@angular/forms';

@Component({
  selector: 'scheduler-dialog',
  templateUrl: 'scheduler-dialog.component.html',
  styleUrls: ['scheduler.component.css']
})
export class SchedulerDialogComponent implements OnInit {

  dataSource: SchedulerDataSource;
  formGroup: FormGroup;

  constructor(private schedulerService: SchedulerService,
              public dialogRef: MatDialogRef<SchedulerDialogComponent>,
              @Optional() @Inject(MAT_DIALOG_DATA) public formData: Scheduler) { }

  ngOnInit() {
    this.dataSource = new SchedulerDataSource(this.schedulerService);
    this.dialogRef.updateSize('40%', '470px');
    this.formGroup = new FormGroup({ id: new FormControl(this.formData.id,[Validators.required]),
                                     name: new FormControl(this.formData.name, [Validators.required, Validators.maxLength(60)]),
                                     cron: new FormControl(this.formData.cron, [Validators.required, Validators.maxLength(20)]),
                                     enabled: new FormControl(this.formData.enabled,[Validators.required])
                                   });
  }

  save()  {
      this.dataSource.saveScheduler(this.formGroup.value);
      this.dialogRef.close(this.formGroup.value);
  }

  close() {
     this.dialogRef.close();
  }
}
