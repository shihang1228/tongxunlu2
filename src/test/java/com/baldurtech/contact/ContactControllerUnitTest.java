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
    Contact contact_has_saved;
    
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
        
        contact_has_saved = new Contact();
        
        contact_has_saved.setId(1L);
        contact_has_saved.setName("ShiHang");
        contact_has_saved.setMobile("15235432994");
        contact_has_saved.setEmail("shihang@qq.com");
        contact_has_saved.setHomeAddress("TaiYuan");
        contact_has_saved.setVpmn("652994");
    }
    
    @Test
    public void 在list方法中应该正确调用contactService中的findAll方法() throws DataAccessException {
        List<Contact> contactList = new ArrayList<Contact>();
        contactList.add(contact);
        
        when(contactService.findAll()).thenReturn(contactList); 

        assertEquals("contact/list", contactController.list(model));        
        verify(contactService).findAll();
        verify(model).addAttribute("contactList", contactList); 
    }
    
    @Test
    public void 在list方法中如果service中的findAll方法抛异常应该访问error_general页面() throws DataAccessException {
        when(contactService.findAll()).thenThrow(new DataAccessException("Can not query any record!"));
        
        assertEquals("error/general", contactController.list(model));
        
    }
    
    @Test
    public void 在show方法中如果能够正确调用service方法中的getById应该返回到show页面() throws DataAccessException {
        when(contactService.getById(CONTACT_ID)).thenReturn(contact);
        
        assertEquals("contact/show", contactController.show(CONTACT_ID, model));
        verify(contactService).getById(CONTACT_ID);
        verify(model).addAttribute("contact", contact);
    }
    
    @Test
    public void 在show方法中如果service中的getById方法抛异常应该访问error_general页面() throws DataAccessException {
        when(contactService.getById(CONTACT_ID)).thenThrow(new DataAccessException("Can not query any record!"));
        assertEquals("error/general", contactController.show(CONTACT_ID, model));
    }
    
    @Test
    public void 当调用create方法应该访问create页面() {
        assertEquals("contact/create", contactController.create());
    }
    
    @Test
    public void 当保存成功后应该重定向到show页面() {
        when(contactService.save(contact)).thenReturn(contact_has_saved);
        
        String save_has_successd = contactController.save(contact, model);
        
        verify(contactService).save(contact);
        assertEquals("redirect:show", save_has_successd);
    }
    
     @Test
     public void 当保存失败后应该重定向到create页面() {
        when(contactService.save(contact)).thenReturn(contact);
        
        String save_has_failed = contactController.save(contact, model);
        
        verify(contactService).save(contact);
        assertEquals("redirect:create", save_has_failed);
    }
    
    @Test
    public void 在edit方法中如果能够正确调用service中的getById方法应该访问update页面() throws DataAccessException{
        when(contactService.getById(CONTACT_ID)).thenReturn(contact);
        
        assertEquals("contact/update", contactController.edit(CONTACT_ID, model));
        verify(model).addAttribute("contact", contact);
        verify(contactService).getById(CONTACT_ID);
    }
    
    @Test
    public void 在edit方法中如果调用service中的getById方法抛异常应该访问error_general页面() throws DataAccessException{
        when(contactService.getById(CONTACT_ID)).thenThrow(new DataAccessException("Can not query any record!"));
        assertEquals("error/general", contactController.edit(CONTACT_ID, model));
    }
}