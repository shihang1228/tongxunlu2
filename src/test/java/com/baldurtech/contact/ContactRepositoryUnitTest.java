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

import org.springframework.beans.factory.annotation.Autowired;

public class ContactRepositoryUnitTest {
    @Mock
    EntityManager entityManager;
    
    @Mock
    TypedQuery typedQuery; 
    
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
}