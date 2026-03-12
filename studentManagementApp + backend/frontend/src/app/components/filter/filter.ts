import { Component } from '@angular/core';
import { Students } from '../../services/students/students';

@Component({
  selector: 'app-filter',
  imports: [],
  templateUrl: './filter.html',
  styleUrl: './filter.css',
})
export class Filter {
  strengthGS: number | null = null;
  strengthStandard: number | null = null;
  strengthSchool: number | null = null;

  constructor(private sts: Students) {}

  getStrengthByGenderAndStandard(gender: string, standard: string) {
    return this.sts
      .getStrengthByGenderAndStandard(gender, +standard)
      .subscribe((res) => (this.strengthGS = res as number));
  }

  getStrengthByStandard(standard: string) {
    return this.sts
      .getStrengthByStandard(Number(standard))
      .subscribe((res) => (this.strengthStandard = res as number));
  }

  getStudentsBySchool(school: string) {
    return this.sts.getStudentsBySchool(school);
  }

  getStrengthBySchool(school: string) {
    return this.sts
      .getStrengthBySchool(school)
      .subscribe((res) => (this.strengthSchool = res as number));
  }

  getStudentsByResult(pass: boolean) {
    return this.sts.getStudentsByResult(pass);
  }
}
