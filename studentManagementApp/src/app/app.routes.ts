import { RouterModule, Routes } from '@angular/router';
import { Welcome } from './components/welcome/welcome';
import { NgModule } from '@angular/core';
import { Login } from './components/login/login';
import { Info } from './components/info/info';
import { Failure } from './components/failure/failure';
import { infoGuard } from './guard/info-guard';

export const routes: Routes = [
  { path: '', component: Welcome },
  { path: 'login', component: Login },
  { path: 'students', component: Info, canActivate: [infoGuard], data: ['STAFF', 'ADMIN'] },
  { path: 'failure', component: Failure },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
