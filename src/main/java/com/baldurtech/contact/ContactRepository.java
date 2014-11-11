package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.*;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository {
    @PersistenceContext
    EntityManager entityManager;

    public List<Contact> findAll() throws PersistenceException {
            return entityManager.createNamedQuery(Contact.FIND_ALL, Contact.class).getResultList();       
    }
    
    public Contact getById(Long id) {
        Contact contact = new Contact();
        contact.setId(id);
        contact.setName("Xiao Bai");
        contact.setMobile("15235432994");
        contact.setVpmn("652994");
        contact.setEmail("a@q.com");
        contact.setHomeAddress("taiyuan");
        contact.setOfficeAddress("beizhang");
        contact.setJob("HR");
        contact.setJobLevel(4L);
        contact.setMemo("Xiao Bai");
        
        return contact;
    }
        
}