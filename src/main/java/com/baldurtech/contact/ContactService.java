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
        if(id > 0) {
            return contactRepository.getById(3L);
        } else {
            return null;
        }
        
    }
    
    public void save(Contact contact) {
        contactRepository.save(contact);
    }
    
    public Contact update(Contact contact) {
        return contactRepository.update(contact);
    }
    
    public void delete(Long id) {
        contactRepository.delete(id);
    }
}