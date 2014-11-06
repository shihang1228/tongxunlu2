package com.baldurtech.contact;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Before;

public class ContactServiceTest {
    private static Contact NON_EXIST_CONTACT = null;
    
    private Long CONTACT_ID = 3L;
    private Contact contact;
    
    @Mock
    ContactRepository contactRepository;
    
    @InjectMocks
    ContactService contactService;
    
    @InjectMocks
    ContactController contactController;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void 在ContactService中的list页面中调用ContactRepository中的findAll方法() {
        contactService.list();
        verify(contactRepository).findAll();
    }
    
    private Contact 假如id对应的contact已经存在(Long id) {
        Contact contact = new Contact();
        contact.setId(id);
        
        when(contactRepository.getById(id)).thenReturn(contact);
        
        return contact;
    }

    private Contact 假如id对应的contact不存在(Long id) {
        when(contactRepository.getById(id)).thenReturn(NON_EXIST_CONTACT);
        return NON_EXIST_CONTACT;
    }

    @Test
    public void 在ContactService中的show方法中调用ContactRepository中的getById方法() {
        Long contactId = 100L;
        
        Contact contact = 假如id对应的contact已经存在(contactId);
        
        assertEquals(contact, contactService.show(contactId));
        verify(contactRepository,times(1)).getById(contactId);
    }
    
    @Test
    public void 如果id对应的contact不存在时就返回null() {
        Long contactId = 8964L;
        
        假如id对应的contact不存在(contactId);
    
        assertNull(contactService.show(contactId));
        verify(contactRepository, times(1)).getById(contactId);
    }
    
    @Test
    public void 在ContactService中的save方法中调用ContactRepository中的save方法() {
        Contact contact = 假如id对应的contact已经存在(98374L);
    
        contactService.save(contact);
        verify(contactRepository).save(contact);
    }
    
    @Test
    public void 在ContactService中的update方法中调用ContactRepository中的update方法() {
        contactService.update(contact);
        verify(contactRepository).update(any(Contact.class));
    }
    
    @Test
    public void 在ContactService中的delete方法中调用ContactRepository中的delete方法() {
        contactService.delete(CONTACT_ID);
        verify(contactRepository).delete(CONTACT_ID);
    }
}