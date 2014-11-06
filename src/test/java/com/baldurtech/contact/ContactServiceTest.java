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
        initMocks();
    }
    
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void 在ContactService中的list页面中调用ContactRepository中的findAll方法() {
        contactService.list();
        verify(contactRepository).findAll();
    }
    
    @Test
    public void 在ContactService中的show方法中调用ContactRepository中的getById方法() {
        Contact contact = new Contact();
        contact.setId(CONTACT_ID);
        
        when(contactRepository.getById(CONTACT_ID)).thenReturn(contact);
        
        assertEquals(contact, contactService.show(CONTACT_ID));
        verify(contactRepository, times(1)).getById(CONTACT_ID);
        assertEquals(CONTACT_ID, contactRepository.getById(CONTACT_ID).getId());
    }
    
    @Test
    public void 如果id对应的contact不存在时就返回null() {
        assertNull(contactService.show(-1L));
        verify(contactRepository,never()).getById(-1L);
    }
    
    @Test
    public void 在ContactService中的save方法中调用ContactRepository中的save方法() {
        contactService.save(contact);
        verify(contactRepository).save(any(Contact.class));
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