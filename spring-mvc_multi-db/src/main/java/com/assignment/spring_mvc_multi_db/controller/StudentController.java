package com.assignment.spring_mvc_multi_db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignment.spring_mvc_multi_db.entity.Student;
import com.assignment.spring_mvc_multi_db.service.StudentService;

@Controller
public class StudentController {
    @Autowired
    StudentService service;

    @RequestMapping("/")
    public String main() {
        return "form.html";
    }

    @RequestMapping("/student")
    public String student(Student s) {
        service.save(s);
        return "form.html";
    }
}
