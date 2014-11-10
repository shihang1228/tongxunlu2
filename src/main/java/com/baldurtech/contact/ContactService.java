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
        return contactRepository.findAll();
    }
}