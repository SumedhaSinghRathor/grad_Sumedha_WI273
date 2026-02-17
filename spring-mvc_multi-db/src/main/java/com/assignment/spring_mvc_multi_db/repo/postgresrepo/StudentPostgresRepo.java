package com.assignment.spring_mvc_multi_db.repo.postgresrepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.spring_mvc_multi_db.entity.Student;

public interface StudentPostgresRepo extends JpaRepository<Student, Integer> {

}
