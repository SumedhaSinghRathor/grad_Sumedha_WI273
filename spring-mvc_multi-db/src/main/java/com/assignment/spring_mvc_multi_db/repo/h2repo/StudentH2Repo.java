package com.assignment.spring_mvc_multi_db.repo.h2repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.spring_mvc_multi_db.entity.Student;

public interface StudentH2Repo extends JpaRepository<Student, Integer> {

}
