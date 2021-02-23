/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eltonb.uni.beans;

import com.eltonb.uni.db.entities.Department;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author elton.ballhysa
 */
@Named("departmentBean")
@RequestScoped
public class DepartmentBean {
    
    @PersistenceUnit(unitName = "RegistryPU")
    private EntityManagerFactory emf;
    
    private String code;
    private String name;
    
    private EntityManager em;
    
    public DepartmentBean() {
        
    }
   
    @PostConstruct
    public void init() {
        em = emf.createEntityManager();
    }
    
    public List<Department> getDepartments() {
        return em.createNamedQuery("Department.findAll", Department.class).getResultList();
    }
    
    public String save() {
        try {
            Department department = new Department(code, name);
            em.getTransaction().begin();
            em.persist(department);
            em.getTransaction().commit();
            return "index";
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Save failed", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return "new";
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
