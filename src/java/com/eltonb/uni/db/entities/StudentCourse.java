/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eltonb.uni.db.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author elton.ballhysa
 */
@Entity
@Table(name = "student_courses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentCourses.findAll", query = "SELECT s FROM StudentCourse s"),
    @NamedQuery(name = "StudentCourses.findByStudentId", query = "SELECT s FROM StudentCourse s WHERE s.studentCoursePK.studentId = :studentId"),
    @NamedQuery(name = "StudentCourses.findByCourseCode", query = "SELECT s FROM StudentCourse s WHERE s.studentCoursePK.courseCode = :courseCode"),
    @NamedQuery(name = "StudentCourses.findBySemesterCode", query = "SELECT s FROM StudentCourse s WHERE s.studentCoursePK.semesterCode = :semesterCode"),
    @NamedQuery(name = "StudentCourses.findByInstructor", query = "SELECT s FROM StudentCourse s WHERE s.instructor = :instructor"),
    @NamedQuery(name = "StudentCourses.findByLetterGrade", query = "SELECT s FROM StudentCourse s WHERE s.letterGrade = :letterGrade"),
    @NamedQuery(name = "StudentCourses.findByFinalGrade", query = "SELECT s FROM StudentCourse s WHERE s.finalGrade = :finalGrade")})
public class StudentCourse implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StudentCoursePK studentCoursePK;
    @Basic(optional = false)
    @Column(name = "instructor")
    private String instructor;
    @Column(name = "letter_grade")
    private String letterGrade;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "final_grade")
    private Double finalGrade;
    @JoinColumn(name = "course_code", referencedColumnName = "code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Course course;
    @JoinColumn(name = "semester_code", referencedColumnName = "code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Semester semester;
    @JoinColumn(name = "student_id", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Student student;

    public StudentCourse() {
    }

    public StudentCourse(StudentCoursePK studentCoursePK) {
        this.studentCoursePK = studentCoursePK;
    }

    public StudentCourse(StudentCoursePK studentCoursePK, String instructor) {
        this.studentCoursePK = studentCoursePK;
        this.instructor = instructor;
    }

    public StudentCourse(int studentId, String courseCode, String semesterCode) {
        this.studentCoursePK = new StudentCoursePK(studentId, courseCode, semesterCode);
    }

    public StudentCoursePK getStudentCoursePK() {
        return studentCoursePK;
    }

    public void setStudentCoursePK(StudentCoursePK studentCoursePK) {
        this.studentCoursePK = studentCoursePK;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

    public Double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Double finalGrade) {
        this.finalGrade = finalGrade;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentCoursePK != null ? studentCoursePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentCourse)) {
            return false;
        }
        StudentCourse other = (StudentCourse) object;
        if ((this.studentCoursePK == null && other.studentCoursePK != null) || (this.studentCoursePK != null && !this.studentCoursePK.equals(other.studentCoursePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "al.edu.unyt.advjava.webapps.model.StudentCourse[ studentCoursePK=" + studentCoursePK + " ]";
    }
    
}
