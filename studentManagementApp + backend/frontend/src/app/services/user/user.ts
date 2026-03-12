import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class User {
  private name: string = 'Username';

  constructor() {
    this.name = localStorage.getItem('username') || 'Username';
  }

  public setName(uname: string): void {
    this.name = uname;
    localStorage.setItem('username', uname);
  }

  public getName(): string {
    return this.name;
  }

  public clear(): void {
    this.name = 'Username';
    localStorage.removeItem('username');
  }
}
