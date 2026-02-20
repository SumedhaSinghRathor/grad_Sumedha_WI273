package assignment.spring_restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import assignment.spring_restapi.entity.Gender;
import assignment.spring_restapi.entity.Student;
import assignment.spring_restapi.repo.StudentRepo;

@RestController
public class StudentController {
    @Autowired
    StudentRepo repo;

    @GetMapping("/")
    public String main() {
        return "<h1>Student Controller</h1>";
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    @GetMapping("/students/{regNo}")
    public Student getStudent(@PathVariable int regNo) {
        return repo.findById(regNo).orElse(null);
    }

    @PostMapping("/students")
    public String addStudent(@RequestBody Student s) {
        if (!repo.existsById(s.getRegNo())) {
            repo.save(s);
        } else {
            return "Sorry! Student already exists";
        }

        return "Successfully added student";
    }

    @PutMapping("/students/{regNo}")
    public String updateStudent(@PathVariable int regNo, @RequestBody Student s) {
        if (s.getRegNo() != regNo) {
            return "Registration Number doesn't exist";
        }

        if (!repo.existsById(regNo)) {
            return "Sorry! Student with given Registration number doesn't exist";
        }

        repo.save(s);
        return "Successfully updated entire student record";
    }

    @PatchMapping("/students/{regNo}")
    public String updateStudentPartial(@PathVariable int regNo, @RequestBody Student s) {
        Student student = repo.findById(regNo)
            .orElseThrow(() -> new RuntimeException("Student not found"));

        if (s.getRollNo() != null) {
            student.setRollNo(s.getRollNo());
        }

        if (s.getName() != null) {
            student.setName(s.getName());
        }

        if (s.getStandard() != null) {
            student.setStandard(s.getStandard());
        }

        if (s.getSchool() != null) {
            student.setSchool(s.getSchool());
        }

        if (s.getGender() != null) {
            student.setGender(s.getGender());
        }

        if (s.getPercentage() != null) {
            student.setPercentage(s.getPercentage());
        }

        repo.save(student);

        return "Successfully updated partial student record";
    }

    @DeleteMapping("/students/{regNo}")
    public String removeStudent(@PathVariable int regNo) {
        if (!repo.existsById(regNo)) return "No student available with given registration number";
        else {
            repo.deleteById(regNo);
            return "Successfully removed student";
        }

    }

    @GetMapping("/students/school")
    public List<Student> retrieveBasedOnSchool(@RequestParam("name") String school) {
        return repo.getBySchool(school);
    }

    @GetMapping("/students/school/count")
    public Integer getCountBySchool(@RequestParam("name") String school) {
        return repo.countBySchool(school);
    }

    @GetMapping("/students/standard/count")
    public Integer getCountByStandard(@RequestParam("class") Integer standard) {
        return repo.countByStandard(standard);
    }

    @GetMapping("/students/result")
    public List<Student> passedStudents(@RequestParam("pass") boolean status) {
        return repo.getPassedStudents(status);
    }

    @GetMapping("/students/strength")
    public Integer getStrength(@RequestParam Gender gender, @RequestParam Integer standard) {
        return (int) repo.getStudentStrength(gender, standard);
    }
}