/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eltonb.uni.rest;

import com.eltonb.uni.db.entities.Department;
import com.eltonb.uni.db.entities.Course;
import com.eltonb.uni.db.entities.StudentCourse;
import com.eltonb.uni.db.entities.StudentCoursePK;
import com.eltonb.uni.model.CourseData;
import com.eltonb.uni.model.GradeData;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.Path;
import static java.util.stream.Collectors.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author elton.ballhysa
 */
@Path("grades")
public class GradeService {
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    @POST
    @Path("/submit")
    @Consumes(MediaType.APPLICATION_JSON)
    public void submitGrade(GradeData gradeData) {
        EntityManager em = emf.createEntityManager();
        StudentCoursePK pk = new StudentCoursePK();
        pk.setStudentId(gradeData.getStudentId());
        pk.setCourseCode(gradeData.getCourseCode());
        pk.setSemesterCode(gradeData.getSemesterCode());
        StudentCourse sc = em.find(StudentCourse.class, pk);
        if (sc == null) {
            sc = new StudentCourse(pk);
        }
        sc.setInstructor(gradeData.getInstructor());
        sc.setFinalGrade(gradeData.getFinalGrade());
        sc.setLetterGrade(gradeData.letterGrade());
        em.getTransaction().begin();
        em.persist(sc);
        em.getTransaction().commit();
    }
    
    @GET
    @Path("/{courseCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public CourseData getCourseByCode(@PathParam("courseCode") String courseCode) {
        EntityManager em = emf.createEntityManager();
        Course model = em.createNamedQuery("Course.findByCode", Course.class)
                .setParameter("code", courseCode)
                .getSingleResult();
        return toData(model);
    }
    
    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateStudent(CourseData course) {
        Course model = toModel(course);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(model);
        em.getTransaction().commit();
    }
    

    public CourseData toData(Course course) {
        if (course == null)
            return null;
        
        CourseData data = new CourseData();
        data.setCode(course.getCode());
        data.setCredits(course.getCredits());
        data.setDepartmentCode(course.getDepartment().getCode());
        data.setDescription(course.getDescription());
        data.setTitle(course.getTitle());
        return data;
    }

    public Course toModel(CourseData course) {
        if (course == null)
            return null;
        
        Course model = new Course();

        model.setCode(course.getCode());
        model.setCredits(course.getCredits());
        model.setDescription(course.getDescription());
        model.setTitle(course.getTitle());
        
        EntityManager em = emf.createEntityManager();
        Department dept = em.find(Department.class, course.getDepartmentCode());
        model.setDepartment(dept);
        
        return model;
    }
    
}
