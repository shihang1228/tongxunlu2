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
    
    public Contact getById(Long id) throws DataAccessException {
        try {
            return contactRepository.getById(id);            
        } catch (PersistenceException pe) {
            throw new DataAccessException("Can not query any record!");
        }
    }
    
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }
}