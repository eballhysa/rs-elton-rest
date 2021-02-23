/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eltonb.uni.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.eltonb.uni.db.entities.Department;
import com.eltonb.uni.model.DepartmentData;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author elton.ballhysa
 */
@Path("departments")
public class DepartmentService {

    @PersistenceUnit
    private EntityManagerFactory emf;
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/hello")
    public String hello() {
        return "Hello World!";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public List<DepartmentData> getAllDepartments() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            return em.createNamedQuery("Department.findAll", Department.class)
                    .getResultList()
                    .stream()
                    .map(this::toData)
                    .collect(Collectors.toList());
        } finally {
            if (em != null)
                em.close();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public void addDepartment(DepartmentData data) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            Department model = toModel(data);
            em.getTransaction().begin();
            em.persist(model);
            em.getTransaction().commit();
        } finally {
            if (em != null)
                em.close();
        }
    }

    @GET    
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{deptCode}")
    public DepartmentData getDepartment(@PathParam("deptCode") String departmentCode) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            Department model = em.createNamedQuery("Department.findByCode", Department.class)
                                    .setParameter("code", departmentCode)
                                    .getSingleResult();
            return toData(model);
        } finally {
            if (em != null)
                em.close();
        }
    }

    private Department toModel(DepartmentData data) {
        if (data == null)
            return null;
        Department model = new Department();
        model.setCode(data.getCode());
        model.setName(data.getName());
        return model;
    }
    
    private DepartmentData toData(Department model) {
        if (model == null)
            return null;
        
        DepartmentData data = new DepartmentData();
        data.setCode(model.getCode());
        data.setName(model.getName());
        return data;
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public void update(DepartmentData data) {
        Department department = toModel(data);
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(department);
            em.getTransaction().commit();
        } finally {
            if (em != null)
                em.close();
        }
    }
    
    @DELETE
    @Path("/{deptCode}")
    public void delete(@PathParam("deptCode") String departmentCode) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.createQuery("delete from Department d where d.code = :code")
                    .setParameter("code", departmentCode)
                    .executeUpdate();
            em.getTransaction().commit();
        } finally {
            if (em != null)
                em.close();
        }
    }
    

}
