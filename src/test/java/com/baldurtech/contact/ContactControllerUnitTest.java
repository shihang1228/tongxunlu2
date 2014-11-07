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
    private Contact contact = new Contact();
    private Long CONTACT_ID = 9L;
    
    @Mock
    Model model;
    
    @Mock
    ContactService contactService;
    
    @InjectMocks
    ContactController contactController;
    
    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        contact.setName("XiaoBai");
        contact.setMobile("18234105437");
        contact.setVpmn("62222");
        contact.setEmail("a@a.com");
        contact.setHomeAddress("ShanXi");
        contact.setOfficeAddress("TaiYuan");
        contact.setJob("HR");
        contact.setJobLevel(1L);
        contact.setMemo("memo");
    }
    public void 当Contact存在时返回contactList() {
        List<Contact> contactList = new ArrayList<Contact>();
        
        contactList.add(contact);
        when(contactService.list()).thenReturn(contactList);
    }
    
    public void 当Contact不存在时返回null() {
        when(contactService.list()).thenReturn(NON_CONTACT_EXISTS);
    }
    
    @Test
    public void 验证contactController的list方法能否正确使用ContactService的list方法() {
        当Contact存在时返回contactList();
        
        assertEquals("contact/list", contactController.list(model));
        verify(contactService, times(2)).list();
    }
    
    @Test
    public void 验证ContactService的list方法的返回值为null时contactController的list方法应该重定向到create页面() {
        当Contact不存在时返回null();
        
        assertEquals("contact/create", contactController.list(model));
        verify(contactService, times(1)).list();
    }
    
    @Test
    public void 验证contactController的show方法能否正确使用contactService的show方法() {
        when(contactService.show(CONTACT_ID)).thenReturn(contact);
        
        assertEquals("contact/show", contactController.show(String.valueOf(CONTACT_ID), model));
        verify(contactService, times(2)).show(CONTACT_ID);
    }
}