import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class Role {
  private role: string = 'ROLE';

  public setRole(role: string): void {
    this.role = role;
  }

  public getRole(): string {
    return this.role;
  }
}
