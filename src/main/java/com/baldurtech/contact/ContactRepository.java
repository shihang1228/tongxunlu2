package com.baldurtech.contact;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Repository
public class ContactRepository {
    @PersistenceContext
    EntityManager entityManager;
    
    public List<Contact> findAll() {
        try {
            return entityManager.createNamedQuery(Contact.FIND_ALL, Contact.class).getResultList();
        } catch(PersistenceException pe) {
            return null;
        }
    }
    
    public Contact getById(Long id) {
        try {
            return entityManager.createNamedQuery(Contact.GET_BY_ID, Contact.class).setParameter("id", id).getSingleResult();
        } catch(PersistenceException pe) {
            return null;
        }
    }
    
    public void save(Contact contact) {
        System.out.println(contact.getName());
    }
}