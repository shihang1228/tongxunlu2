package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.ui.Model;

public class ContactControllerUnitTest {
    @Mock
    Model model;
    
    @Mock
    ContactService contactService;
    
    @InjectMocks
    ContactController contactController;
    
    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
    
    public void 验证contactController的list方法能否正确使用ContactService的list方法() {
        List<Contact> contactList = new ArrayList<Contact>();
        Contact contact1 = new Contact();
        contact1.setName("Xiao Bai");
        Contact contact2 = new Contact();
        contact2.setName("Shi Hang");
        
        contactList.add(contact1);
        contactList.add(contact2);
        
        when(contactService.list()).thenReturn(contactList);
        assertEquals("contact/list", contactController.list(model));
        verify(contactService).list();
    }
}