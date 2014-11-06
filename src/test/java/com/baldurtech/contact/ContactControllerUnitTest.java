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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

import org.springframework.ui.Model;

public class ContactControllerUnitTest {
    private List<Contact> NON_CONTACT_EXISTS = null;
    
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
    public List<Contact> 当Contact存在时返回contactList() {
        List<Contact> contactList = new ArrayList<Contact>();
        Contact contact1 = new Contact();
        contact1.setName("Xiao Bai");
        Contact contact2 = new Contact();
        contact2.setName("Shi Hang");
        
        contactList.add(contact1);
        contactList.add(contact2);
        return contactList;
    }
    
    public List<Contact> 当Contact不存在是返回null() {
        return NON_CONTACT_EXISTS;
    }
    
    @Test
    public void 验证contactController的list方法能否正确使用ContactService的list方法() {
        List<Contact> contactList = 当Contact存在时返回contactList();
        
        when(contactService.list()).thenReturn(contactList);
        assertEquals("contact/list", contactController.list(model));
        verify(contactService, times(2)).list();
    }
    
    @Test
    public void 验证ContactService的list方法的返回值为null时contactController的list方法应该重定向到create页面() {
        when(contactService.list()).thenReturn(NON_CONTACT_EXISTS);
        assertEquals("contact/create", contactController.list(model));
        verify(contactService, times(1)).list();
    }
}