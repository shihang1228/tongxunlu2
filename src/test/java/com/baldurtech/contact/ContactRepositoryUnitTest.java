package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ContactRepositoryUnitTest {
    private Long CONTACT_ID = 5L;
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
    
    @Test
    public void 在get_by_id方法中如果数据中存在指定联系人信息应该返回指定联系人的信息() {
        Contact contact = new Contact();
        when(entityManager.createNamedQuery(Contact.GET_BY_ID, Contact.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter("id", CONTACT_ID)).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenReturn(contact);
        
        assertEquals(contact, contactRepository.getById(CONTACT_ID));
    }
    
    @Test
    public void 调用save方法保存联系人信息() {
        Contact contact = new Contact();
        
        contactRepository.save(contact);
        
        verify(entityManager).persist(contact);
    }
    
    @Test
    public void 当联系人存在时更新数据库中的联系人() {
        Contact contact = new Contact();
        Contact contact_has_saved = new Contact();
        
        when(entityManager.merge(contact)).thenReturn(contact_has_saved);
        
        assertEquals(contact_has_saved, contactRepository.update(contact));
        verify(entityManager).merge(contact);
    }
}