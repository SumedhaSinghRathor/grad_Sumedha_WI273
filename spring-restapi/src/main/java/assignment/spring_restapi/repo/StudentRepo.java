package assignment.spring_restapi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import assignment.spring_restapi.entity.Gender;
import assignment.spring_restapi.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {
    public List<Student> getBySchool(String school);
    @Query("SELECT COUNT(*) FROM Student WHERE school=?1")
    public Integer countBySchool(String school);

    @Query("SELECT COUNT(*) FROM Student WHERE standard=?1")
    public Integer countByStandard(Integer standard);

    @Query("""
            FROM Student
            WHERE (?1 = true AND percentage >= 40)
            OR (?1 = false AND percentage < 40)
            ORDER BY percentage DESC
        """)
    public List<Student> getPassedStudents(@Param("pass") boolean status);

    @Query("""
        SELECT COUNT(*) FROM Student
        WHERE gender = ?1 AND standard = ?2
       """)
    long getStudentStrength(@Param("gender") Gender gender, @Param("standard") Integer standard);

}