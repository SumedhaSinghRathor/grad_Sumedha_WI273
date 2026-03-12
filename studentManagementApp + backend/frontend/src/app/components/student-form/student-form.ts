import { Component, OnInit } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Students } from '../../services/students/students';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-student-form',
  imports: [FormsModule],
  templateUrl: './student-form.html',
  styleUrl: './student-form.css',
})
export class StudentForm implements OnInit {
  student: any = {};
  isEdit = false;

  constructor(
    private route: ActivatedRoute,
    private sts: Students,
    private router: Router,
  ) {}

  ngOnInit(): void {
    let regNo = this.route.snapshot.paramMap.get('regNo');

    if (regNo) {
      this.isEdit = true;
      this.sts.getStudentByRegNo(+regNo).subscribe((res) => {
        this.student = res;
      });
    }
  }

  onSubmit(form: NgForm) {
    if (form.invalid) {
      alert('Please fill all fields correctly');
      return;
    }

    console.log('Sending: ', form.value);

    if (this.isEdit) {
      this.sts.updateStudentByRegNo(this.student.regNo, form.value).subscribe(() => {
        alert('Student updated successfully');
        this.router.navigate(['students']);
      });
    } else {
      this.sts.postStudent(form.value).subscribe({
        next: () => {
          alert('Student added successfully');
          form.reset();
          this.router.navigate(['students']);
        },
        error: (err) => {
          console.error(err);
          alert('Failed to add student');
        },
      });
    }
  }
}
