package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.baldurtech.exception.*;

@Service
public class ContactService {
    ContactRepository contactRepository;
    
    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> findAll() throws DataAccessException {
        try {
            return contactRepository.findAll();
        } catch(PersistenceException pe) {
            throw new DataAccessException("Can not query any record!");
        }
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