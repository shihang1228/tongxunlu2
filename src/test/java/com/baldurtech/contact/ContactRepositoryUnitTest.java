package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ContactRepositoryUnitTest {
    
    @Mock
    EntityManager entityManager;
    
    @Mock
    TypedQuery typedQuery;
    
    @InjectMocks
    ContactRepository contactRepository;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        contactRepository = new ContactRepository();
        contactRepository.setEntityManager(entityManager);
    }
    
    @Test
    public void 在findAll方法中如果数据库中存在联系人列表应该返回联系人列表() {
        List<Contact> contactList = new ArrayList<Contact>();
        when(entityManager.createNamedQuery(Contact.FIND_ALL, Contact.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(contactList);
        
        assertEquals(contactList, contactRepository.findAll());
    }
}