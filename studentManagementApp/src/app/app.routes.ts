import { Routes } from '@angular/router';
import { Welcome } from './components/welcome/welcome';
import { Login } from './components/login/login';
import { Info } from './components/info/info';
import { infoGuard } from './guard/info-guard';
import { Failure } from './components/failure/failure';

export const routes: Routes = [
  { path: '', component: Welcome },
  { path: 'login', component: Login },
  { path: 'students', component: Info, canActivate: [infoGuard], data: ['STAFF', 'ADMIN'] },
  { path: 'failure', component: Failure },
];
