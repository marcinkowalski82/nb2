import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth.service';

@Component({   templateUrl: 'project.component.html' })
export class CallbackComponent implements OnInit {

  constructor(private okta: OktaAuthService) {}

  ngOnInit(): void {
    // Handles the response from Okta and parses tokens
    this.okta.handleAuthentication();
  }
}
