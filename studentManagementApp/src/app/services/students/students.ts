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
  public studentsList: Student[] = [
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
    this.studentsList.push(student);
  }

  public getStudent(): Student[] {
    return this.studentsList;
  }

  public updateStudent(updated: any) {
    const index = this.studentsList.findIndex((s) => s.reg_no === updated.reg_no);
    if (index !== -1) this.studentsList[index] = updated;
  }
}
