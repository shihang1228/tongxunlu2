package com.baldurtech.contact;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;


import org.junit.Test;
import org.junit.Before;

public class ContactServiceTest {
    private Long CONTACT_ID = 1L;
    private Contact contact;
    
    @Mock
    ContactRepository contactRepository;
    
    @InjectMocks
    ContactService contactService;
    
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
        contactService.show(CONTACT_ID);
        verify(contactRepository).getById(CONTACT_ID);
    }
    
    @Test
    public void 在ContactService中的save方法中调用ContactRepository中的save方法() {
        contactService.save(contact);
        verify(contactRepository).save(any(Contact.class));
    }
}