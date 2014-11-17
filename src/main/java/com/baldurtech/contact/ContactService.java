package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ContactService {
    ContactRepository contactRepository;
    
    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }
    
    public Contact getById(Long id) {
        return contactRepository.getById(id);
    }
    
    public Contact save(Contact contact) {
        contactRepository.save(contact);
        return contact;
    }
    
    public Contact update(Contact contact) {
        return contactRepository.update(contact);
    }
    
    public void delete(Long id) {
        System.out.println(id);
    }
}