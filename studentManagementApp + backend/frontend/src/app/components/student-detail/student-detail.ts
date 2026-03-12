import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Students } from '../../services/students/students';
import { Student } from '../../models/student.model';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-student-detail',
  imports: [NgIf],
  templateUrl: './student-detail.html',
  styleUrl: './student-detail.css',
})
export class StudentDetail implements OnInit {
  student: Student | null = null;

  constructor(
    private route: ActivatedRoute,
    public sts: Students,
  ) {}

  ngOnInit(): void {
    let regNo: number = Number(this.route.snapshot.paramMap.get('regNo'));
    this.refreshStudent(regNo);
  }

  refreshStudent(regNo: number): void {
    this.sts.getStudentByRegNo(regNo).subscribe((res) => {
      this.student = res;
    });
  }
}
