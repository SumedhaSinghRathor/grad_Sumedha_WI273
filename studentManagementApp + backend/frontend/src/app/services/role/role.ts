import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class Role {
  private role: string = 'ROLE';

  constructor() {
    this.role = localStorage.getItem('role') || 'ROLE';
  }

  public setRole(role: string): void {
    this.role = role;
    localStorage.setItem('role', role);
  }

  public getRole(): string {
    return this.role;
  }

  public clear(): void {
    this.role = 'ROLE';
    localStorage.removeItem('role');
  }
}
