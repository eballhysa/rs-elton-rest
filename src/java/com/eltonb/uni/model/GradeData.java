/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eltonb.uni.model;

/**
 *
 * @author elton.ballhysa
 */
public class GradeData {
    
    private int studentId;
    private String courseCode;
    private String semesterCode;
    private String instructor;
    private double finalGrade; 

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getSemesterCode() {
        return semesterCode;
    }

    public void setSemesterCode(String semesterCode) {
        this.semesterCode = semesterCode;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(double finalGrade) {
        this.finalGrade = finalGrade;
    }
    
    public String letterGrade() {
        if (finalGrade < 60) return "F";
        if (finalGrade < 63) return "D-";
        if (finalGrade < 67) return "D-";
        if (finalGrade < 70) return "D+";
        if (finalGrade < 73) return "C-";
        if (finalGrade < 77) return "C";
        if (finalGrade < 80) return "C+";
        if (finalGrade < 83) return "B-";
        if (finalGrade < 87) return "B";
        if (finalGrade < 90) return "B+";
        if (finalGrade < 95) return "A-";
        return "A";                    
    }
    
    
}
