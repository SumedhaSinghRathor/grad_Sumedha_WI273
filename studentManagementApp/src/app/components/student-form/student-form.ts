import { Component, Input } from '@angular/core';
import { Students } from '../../services/students/students';

@Component({
  selector: 'app-student-form',
  imports: [],
  templateUrl: './student-form.html',
  styleUrl: './student-form.css',
})
export class StudentForm {
  constructor(private sts: Students) {}

  studentAdd(event: any) {
    event.preventDefault();

    let regNo: number = event.target.elements[0].value;
    let name: string = event.target.elements[1].value;
    let rollNo: number = event.target.elements[2].value;
    let school: string = event.target.elements[3].value;
    let standard: number = event.target.elements[4].value;

    let newStudent = {
      reg_no: regNo,
      name: name,
      roll_no: rollNo,
      school: school,
      standard: standard,
    };

    this.sts.setStudent(newStudent);
    event.target.reset();
  }
}
