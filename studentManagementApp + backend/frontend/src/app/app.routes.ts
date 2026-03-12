import { Routes } from '@angular/router';
import { Welcome } from './components/welcome/welcome';
import { Login } from './components/login/login';
import { Display } from './components/display/display';
import { Failure } from './components/failure/failure';
import { infoGuard } from './guard/info-guard';
import { StudentForm } from './components/student-form/student-form';
import { StudentDetail } from './components/student-detail/student-detail';
import { Filter } from './components/filter/filter';

export const routes: Routes = [
  {
    path: '',
    component: Welcome,
  },
  {
    path: 'login',
    component: Login,
  },
  {
    path: 'students',
    component: Display,
    canActivate: [infoGuard],
    data: ['STAFF', 'ADMIN'],
  },
  {
    path: 'failure',
    component: Failure,
  },
  {
    path: 'form',
    component: StudentForm,
    canActivate: [infoGuard],
    data: ['ADMIN'],
  },
  {
    path: 'form/:regNo',
    component: StudentForm,
    canActivate: [infoGuard],
    data: ['ADMIN'],
  },
  {
    path: 'students/:regNo',
    component: StudentDetail,
  },
  {
    path: 'filter',
    component: Filter,
  },
];
