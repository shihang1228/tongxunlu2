package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.*;
import org.springframework.stereotype.Repository;

import com.baldurtech.exception.*;

@Repository
public class ContactRepository {
    @PersistenceContext
    EntityManager entityManager;

    public List<Contact> findAll() throws DataAccessException {
        try {
            return entityManager.createNamedQuery(Contact.FIND_ALL, Contact.class).getResultList();       
        }catch(PersistenceException pe) {
            throw new DataAccessException("Can not access the database!");
        }
    }
}