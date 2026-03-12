import { Injectable } from '@angular/core';
import { Student } from '../../models/student.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class Students {
  url: string = 'http://localhost:8080/students';

  constructor(private http: HttpClient) {}

  // Standard HTTP Requests
  getAllStudents() {
    return this.http.get<Student[]>(this.url);
  }

  postStudent(st: Student) {
    return this.http.post(this.url, st);
  }

  getStudentByRegNo(regNo: number) {
    return this.http.get<Student>(`${this.url}/${regNo}`);
  }

  deleteStudentByRegNo(regNo: number) {
    return this.http.delete(`${this.url}/${regNo}`);
  }

  updateStudentByRegNo(regNo: number, st: Student) {
    return this.http.patch(`${this.url}/${regNo}`, st, { responseType: 'text' });
  }

  // Custom HTTP Requests
  getStrengthByGenderAndStandard(gender: string, standard: number) {
    return this.http.get(`${this.url}/strength?gender=${gender}&standard=${standard}`);
  }

  getStrengthByStandard(standard: number) {
    return this.http.get(`${this.url}/standard/count?class=${standard}`);
  }

  getStudentsBySchool(school: string) {
    return this.http.get(`${this.url}/school?name=${school}`);
  }

  getStrengthBySchool(school: string) {
    return this.http.get(`${this.url}/school/count?name=${school}`);
  }

  getStudentsByResult(pass: boolean) {
    return this.http.get(`${this.url}/result?pass=${pass}`);
  }
}
