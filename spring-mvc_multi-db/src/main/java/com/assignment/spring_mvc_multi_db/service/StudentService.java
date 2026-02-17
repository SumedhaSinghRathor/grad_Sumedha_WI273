package com.assignment.spring_mvc_multi_db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.spring_mvc_multi_db.entity.Student;
import com.assignment.spring_mvc_multi_db.repo.h2repo.StudentH2Repo;
import com.assignment.spring_mvc_multi_db.repo.postgresrepo.StudentPostgresRepo;

@Service
public class StudentService {
    @Autowired
    private StudentH2Repo h2Repo;
    
    @Autowired
    private StudentPostgresRepo pgRepo;

    public void save(Student s) {
        h2Repo.save(s);
        pgRepo.save(s);
    }
}
