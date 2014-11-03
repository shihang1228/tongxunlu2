package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Transactional
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
        entityManager.persist(contact);
    }
}