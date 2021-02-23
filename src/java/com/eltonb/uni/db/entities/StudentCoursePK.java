/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eltonb.uni.db.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author elton.ballhysa
 */
@Embeddable
public class StudentCoursePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "student_id")
    private int studentId;
    @Basic(optional = false)
    @Column(name = "course_code")
    private String courseCode;
    @Basic(optional = false)
    @Column(name = "semester_code")
    private String semesterCode;

    public StudentCoursePK() {
    }

    public StudentCoursePK(int studentId, String courseCode, String semesterCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.semesterCode = semesterCode;
    }

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) studentId;
        hash += (courseCode != null ? courseCode.hashCode() : 0);
        hash += (semesterCode != null ? semesterCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentCoursePK)) {
            return false;
        }
        StudentCoursePK other = (StudentCoursePK) object;
        if (this.studentId != other.studentId) {
            return false;
        }
        if ((this.courseCode == null && other.courseCode != null) || (this.courseCode != null && !this.courseCode.equals(other.courseCode))) {
            return false;
        }
        if ((this.semesterCode == null && other.semesterCode != null) || (this.semesterCode != null && !this.semesterCode.equals(other.semesterCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "al.edu.unyt.advjava.webapps.model.StudentCoursesPK[ studentId=" + studentId + ", courseCode=" + courseCode + ", semesterCode=" + semesterCode + " ]";
    }
    
}
