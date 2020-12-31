import { NgModule } from '@angular/core';
import { Component, ViewChild } from '@angular/core';
import { MatSidenavModule } from '@angular/material/sidenav';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.css'],
})

export class AppComponent {

  isAuthenticated: boolean;
  constructor(public oktaAuth: AuthService) {}

  @ViewChild('sidenav') sidenav: MatSidenavModule;
  isExpanded = true;
  showSubmenu: boolean = false;
  isShowing = false;
  showSubSubMenu: boolean = false;

  mouseenter() {
    if (!this.isExpanded) {
      this.isShowing = true;
    }
  }

  mouseleave() {
    if (!this.isExpanded) {
      this.isShowing = false;
    }
  }

  ngOnInit(): void {
      this.oktaAuth.$isAuthenticated.subscribe(val => this.isAuthenticated = val);
    }

}




/**  Copyright 2017 Google Inc. All Rights Reserved.
    Use of this source code is governed by an MIT-style license that
    can be found in the LICENSE file at http://angular.io/license */
