package com.baldurtech.department;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

import org.springframework.stereotype.Repository;

@Repository
public class DepartmentRepository {
    @PersistenceContext
    EntityManager entityManager;
    
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public List<Department> findAll() {
        return entityManager.createNamedQuery(Department.FIND_ALL, Department.class).getResultList();
    }
}