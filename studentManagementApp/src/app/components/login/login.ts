import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { User } from '../../services/user/user';
import { Role } from '../../services/role/role';

@Component({
  selector: 'app-login',
  imports: [RouterModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  constructor(
    private router: Router,
    private us: User,
    private rs: Role,
  ) {}

  abc(event: any) {
    event.preventDefault();

    let uname: string = event.target.elements[0].value;
    console.log('Username: ' + uname);

    let pwd: string = event.target.elements[1].value;
    console.log('Password: ' + pwd);

    let role: string = event.target.elements[2].value;
    console.log('Role: ' + role);

    if (uname == pwd) {
      this.us.setName(uname);
      this.rs.setRole(role);
      this.router.navigate(['students']);
    } else {
      this.router.navigate(['failure']);
    }
  }
}
