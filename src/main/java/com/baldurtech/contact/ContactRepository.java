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
    
    public Contact getById(Long id) {
        Contact contact = new Contact();
        contact.setName("Shihang");
        contact.setMobile("15235432994");
        contact.setEmail("a@qq.com");
        contact.setVpmn("652994");
        contact.setHomeAddress("taiyuan");
        contact.setOfficeAddress("taiyuan");
        contact.setMemo("memo");
        contact.setJob("HR");
        contact.setJobLevel(4L);
         
        return contact;
    }
}