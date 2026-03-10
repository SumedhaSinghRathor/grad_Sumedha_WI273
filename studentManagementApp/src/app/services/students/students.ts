import { Injectable } from '@angular/core';

interface Student {
  reg_no: number;
  name: string;
  roll_no: number;
  school: string;
  standard: number;
}

@Injectable({
  providedIn: 'root',
})
export class Students {
  public studentLists: Student[] = [
    {
      reg_no: 1,
      name: 'Sumedha Singh Rathor',
      roll_no: 1000,
      school: 'MVN',
      standard: 11,
    },
    {
      reg_no: 2,
      name: 'Yash',
      roll_no: 1001,
      school: 'DPS',
      standard: 8,
    },
    {
      reg_no: 3,
      name: 'Aarohi Mehta',
      roll_no: 1002,
      school: 'Greenwood High',
      standard: 10,
    },
    {
      reg_no: 4,
      name: 'Rohan Iyer',
      roll_no: 1003,
      school: 'Orchid Public School',
      standard: 9,
    },
    {
      reg_no: 5,
      name: 'Diya Sharma',
      roll_no: 1004,
      school: 'Vidyaship Academy',
      standard: 12,
    },
  ];

  public setStudent(student: Student) {
    this.studentLists.push(student);
  }

  public getStudents(): Student[] {
    return this.studentLists;
  }

  public deleteStudent(regNo: number): void {
    this.studentLists = this.studentLists.filter((s) => s.reg_no !== regNo);
  }
}
