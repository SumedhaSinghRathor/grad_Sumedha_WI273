import { Component, OnInit } from '@angular/core';
import { NgForOf, NgIf } from '@angular/common';
import { Students } from '../../services/students/students';
import { User } from '../../services/user/user';
import { Role } from '../../services/role/role';
import { Student } from '../../models/student.model';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-display',
  imports: [NgForOf, NgIf],
  templateUrl: './display.html',
  styleUrl: './display.css',
})
export class Display implements OnInit {
  students: Student[] = [];

  constructor(
    public us: User,
    public rs: Role,
    public sts: Students,
    private router: Router,
    private route: ActivatedRoute,
  ) {}

  ngOnInit() {
    this.refreshStudent();
  }

  refreshStudent(): void {
    this.sts.getAllStudents().subscribe((res) => {
      this.students = res as Student[];
    });
  }

  openStudent(regNo: number) {
    this.router.navigate([regNo], { relativeTo: this.route });
  }

  ensureDelete(regNo: number) {
    if (confirm('Are you sure you would like to delete this student record?')) {
      this.sts.deleteStudentByRegNo(regNo).subscribe(() => {
        this.refreshStudent();
      });
      alert('Record deleted successfully');
      this.router.navigate(['students']);
    } else {
      alert('Deletion cancelled');
    }
  }

  editStudent(regNo: number) {
    this.router.navigate(['form', regNo]);
  }
}
