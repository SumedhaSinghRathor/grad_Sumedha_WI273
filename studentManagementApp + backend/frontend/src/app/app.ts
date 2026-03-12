import { Component, signal } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { User } from './services/user/user';
import { Role } from './services/role/role';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NgIf],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected readonly title = signal('frontend');

  constructor(
    private us: User,
    private rs: Role,
    private router: Router,
  ) {}

  checkLogin() {
    if (localStorage.getItem('username')) {
      return true;
    } else {
      return false;
    }
  }

  logout() {
    this.us.clear();
    this.rs.clear();
    this.router.navigate(['']);
  }
}
