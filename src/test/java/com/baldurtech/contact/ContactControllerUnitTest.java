package com.baldurtech.contact;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;

import org.junit.Before;
import org.junit.Test;

import org.springframework.ui.Model;


public class ContactControllerUnitTest {
    private Long CONTACT_ID = 1L;
    private Contact contact = new Contact();
    
    @Mock
    ContactService contactService;
    
    @Mock
    Model model;
    
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
    public void 在contactController中的list方法中将会调用ContactService中的list方法() {
        contactController.list(model);
        verify(contactService).list();
    }
    
    @Test
    public void 在contactController中的show方法中将会调用ContactService中的show方法() {
        contactController.show(String.valueOf(CONTACT_ID), model);
        verify(contactService).show(CONTACT_ID);
    }
    
    @Test
    public void 在contactController中的save方法中将会调用ContactService中的save方法() {
        contactController.save(contact);
        verify(contactService).save(any(Contact.class));
    }
    
    @Test
    public void 在contactController中的update方法中将会调用ContactService中的update方法() {
        contactController.update(contact, model);
        verify(contactService).update(any(Contact.class));
    }
}