package com.baldurtech.contact;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;

@Service
public class ContactService {
    ContactRepository contactRepository;
    
    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    
    public List<Contact> list() {
        return contactRepository.findAll();
    }
    
    public Contact show(Long id) {
        return getById(id);
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