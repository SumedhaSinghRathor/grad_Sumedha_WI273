import { Component } from '@angular/core';
import { NgForOf, NgIf } from '@angular/common';
import { User } from '../../services/user/user';
import { Role } from '../../services/role/role';
import { Students } from '../../services/students/students';
import { StudentForm } from '../student-form/student-form';

@Component({
  selector: 'app-info',
  imports: [NgForOf, StudentForm, NgIf],
  templateUrl: './info.html',
  styleUrl: './info.css',
})
export class Info {
  constructor(
    public us: User,
    public rs: Role,
    public sts: Students,
  ) {}
}
