package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.*;

import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository {
    @PersistenceContext
    EntityManager entityManager;
    
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public List<Contact> findAll() {
            return entityManager.createNamedQuery(Contact.FIND_ALL, Contact.class).getResultList();
    }   
}