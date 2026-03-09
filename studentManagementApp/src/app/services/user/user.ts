import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class User {
  private name: string = 'Username';

  public setName(uname: string): void {
    this.name = uname;
  }

  public getName(): string {
    return this.name;
  }
}
