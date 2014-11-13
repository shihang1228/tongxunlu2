package com.baldurtech.contact;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

import org.springframework.beans.factory.annotation.Autowired;

public class ContactRepositoryUnitTest {
    private Long CONTACT_ID = 9L;
    @Mock
    EntityManager entityManager;
    
    @Mock
    TypedQuery typedQuery; 
    
    @Rule
    public ExpectedException thrown= ExpectedException.none();
    
    @InjectMocks
    ContactRepository contactRepository;
    
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.contactRepository = new ContactRepository();
        contactRepository.setEntityManager(entityManager);
    }
    
    @Test
    public void 如果数据库中存在联系人调用contactRepository中的findAll方法应该查询到联系人列表() {
        List<Contact> contactList = new ArrayList<Contact>();
        when(entityManager.createNamedQuery(Contact.FIND_ALL, Contact.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(contactList);
        
        assertEquals(contactList, contactRepository.findAll());
    }
    
    @Test
    public void 如果contactRepository调用findAll方法时数据库出现异常应该抛出异常() {
        when(entityManager.createNamedQuery(Contact.FIND_ALL, Contact.class)).thenThrow(new PersistenceException());
        
        thrown.expect(PersistenceException.class);
        contactRepository.findAll();
    }
    
    @Test
    public void 如果数据库中存在联系人调用contactRepository中的getById方法应该查询到联系人() {
        Contact contact = new Contact();
        when(entityManager.createNamedQuery(Contact.GET_BY_ID, Contact.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter("id", CONTACT_ID)).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenReturn(contact);
        
        assertEquals(contact, contactRepository.getById(CONTACT_ID));
    }
}