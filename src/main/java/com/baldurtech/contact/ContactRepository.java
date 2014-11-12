package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ContactRepository {
    @PersistenceContext
    EntityManager entityManager;

    public List<Contact> findAll() throws PersistenceException {
            return entityManager.createNamedQuery(Contact.FIND_ALL, Contact.class).getResultList();       
    }
    
    public Contact getById(Long id) throws PersistenceException {
        return entityManager.createNamedQuery(Contact.GET_BY_ID, Contact.class).setParameter("id", id).getSingleResult();
    }
    
    public Contact save(Contact contact) {
        entityManager.persist(contact);
        return contact;
    }
    
    public Contact update(Contact contact) {
        System.out.println(contact.getId());
        return entityManager.merge(contact);
    }
        
}