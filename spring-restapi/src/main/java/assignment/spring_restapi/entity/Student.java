package assignment.spring_restapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer regNo;
    private Integer rollNo;
    private String name;
    private Integer standard;
    private String school;
    private Gender gender;
    private Float percentage;

    public Integer getRegNo() {
        return regNo;
    }
    public void setRegNo(Integer regNo) {
        this.regNo = regNo;
    }
    public Integer getRollNo() {
        return rollNo;
    }
    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getStandard() {
        return standard;
    }
    public void setStandard(Integer standard) {
        this.standard = standard;
    }
    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public Float getPercentage() {
        return percentage;
    }
    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }
}
