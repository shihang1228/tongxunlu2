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
        
        Contact contact = new Contact();
        contact.setName("Shi Hang");
        contact.setMobile("18233333333");
        contact.setVpmn("6555");
        contact.setEmail("a@a.com");
        contact.setHomeAddress("TaiYuan");
        contact.setOfficeAddress("BeiZhang");
        contact.setJob("HR");
        contact.setJobLevel(9L);
        contact.setMemo("memo");
        
        return contact;
    }
}