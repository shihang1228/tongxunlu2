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
    
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public List<Contact> findAll() {
            return entityManager.createNamedQuery(Contact.FIND_ALL, Contact.class).getResultList();
    }
    
    public Contact getById(Long id) {
        return entityManager.createNamedQuery(Contact.GET_BY_ID, Contact.class).setParameter("id", id).getSingleResult();
    }
    
    public void save(Contact contact) {
        entityManager.persist(contact);
    }
    
    public Contact update(Contact contact) {
        return entityManager.merge(contact);
    }
}