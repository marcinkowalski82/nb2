import { Component, AfterViewInit, ViewChild, OnInit, HostListener } from '@angular/core';
import { FormsModule, ReactiveFormsModule, FormGroup, Validators, FormControl } from '@angular/forms';

@Component({
  selector: 'login',
  templateUrl: 'login.component.html',
  styleUrls: ['login.component.css']
})
export class LoginFormComponent {
  form: FormGroup = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  });

  submit() {
    if (this.form.valid) {
      this.submitEM.emit(this.form.value);
    }
  }
  @Input() error: string | null;

  @Output() submitEM = new EventEmitter();
}
