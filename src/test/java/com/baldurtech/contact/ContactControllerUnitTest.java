package com.baldurtech.contact;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.ArrayList;

import org.springframework.ui.Model;
import com.baldurtech.exception.*;


public class ContactControllerUnitTest { 
    private Long CONTACT_ID = 4L;
    Contact contact;
    
    @Mock
    ContactService contactService;
    
    @Mock
    Model model;
    
    @InjectMocks
    ContactController contactController;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        contact = new Contact();
        
        contact.setName("ShiHang");
        contact.setMobile("15235432994");
        contact.setEmail("shihang@qq.com");
        contact.setHomeAddress("TaiYuan");
        contact.setVpmn("652994");
    }
    
    @Test
    public void 在list方法中应该正确调用contactService中的findAll方法() throws Exception {
        List<Contact> contactList = new ArrayList<Contact>();
        contactList.add(contact);
        
        when(contactService.findAll()).thenReturn(contactList); 

        assertEquals("contact/list", contactController.list(model));        
        verify(contactService).findAll();
        verify(model).addAttribute("contactList", contactList); 
    }
    
    @Test
    public void 在list方法中如果service中的findAll方法抛异常应该访问error_general页面() throws DataAccessException{
        when(contactService.findAll()).thenThrow(new DataAccessException("Can not query any record!"));
        
        assertEquals("error/general", contactController.list(model));
        
    }
    
    @Test
    public void 在show方法中如果service中的getById方法返回值为null时应该重定向到list页面() {
        when(contactService.getById(CONTACT_ID)).thenReturn(null);
        
        assertEquals("redirect:list", contactController.show(CONTACT_ID, model));
    }
    
    @Test
    public void 在show方法中如果service中的getById方法返回值不为null时应该访问show页面() {
        when(contactService.getById(CONTACT_ID)).thenReturn(contact);
        
        assertEquals("contact/show", contactController.show(CONTACT_ID, model));
    }
    
}