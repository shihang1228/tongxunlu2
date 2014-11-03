package com.baldurtech.contact;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

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
        return contactRepository.getById(id);
    }
    
    public Contact getById(Long id) {
        Contact contact = new Contact();
        contact.setName("ShiHang");
        contact.setMobile("15235432994");
        contact.setVpmn("652994");
        contact.setHomeAddress("TaiYuan");
        
        return contact;
    }
    
    public void save(Contact contact) {
        contactRepository.save(contact);
    }
    
    public Contact update(Contact contact) {
        return contact;
    }
}