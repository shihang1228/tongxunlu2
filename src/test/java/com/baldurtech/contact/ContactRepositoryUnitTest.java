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

public class ContactRepositoryUnitTest extends CreateContactData{
    private Long CONTACT_ID = 5L;
    private Contact contact;
    private Contact contact_has_saved;
    private Contact contact_has_updated;
    
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
        
        contact = createContact();
        contact.setId(null);
        
        contact_has_saved = contact;
       
        contact_has_updated = contact;
        contact.setName("Xiao Bai");
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
        when(entityManager.createNamedQuery(Contact.GET_BY_ID, Contact.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter("id", CONTACT_ID)).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenReturn(contact);
        
        assertEquals(contact, contactRepository.getById(CONTACT_ID));
    }
    
    @Test
    public void 调用save方法保存联系人信息() {
        
        contactRepository.save(contact);
        
        verify(entityManager).persist(contact);
    }
    
    @Test
    public void 当联系人存在时更新数据库中的联系人() {
        
        when(entityManager.merge(contact_has_saved)).thenReturn(contact_has_updated);
        
        assertEquals(contact_has_updated, contactRepository.update(contact_has_saved));
        verify(entityManager).merge(contact_has_saved);
    }
    
    @Test
    public void 当id存在时删除指定联系人信息() {
        when(entityManager.find(Contact.class, CONTACT_ID)).thenReturn(contact);
        contactRepository.delete(CONTACT_ID);
        verify(entityManager).remove(contact);
    }
}