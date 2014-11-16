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
        contact.setName("Shihang");
        contact.setMobile("15235432994");
        contact.setVpmn("652994");
        contact.setHomeAddress("taiyuan");
        contact.setOfficeAddress("taiyuan");
        contact.setMemo("memo");
        contact.setJob("HR");
        contact.setJobLevel(4L);
        
        contact_has_saved = new Contact();
        contact_has_saved.setId(9L);
        contact_has_saved.setName("Shihang");
        contact_has_saved.setMobile("15235432994");
        contact_has_saved.setVpmn("652994");
        contact_has_saved.setHomeAddress("taiyuan");
        contact_has_saved.setOfficeAddress("taiyuan");
        contact_has_saved.setMemo("memo");
        contact_has_saved.setJob("HR");
        contact_has_saved.setJobLevel(4L);
    }
    
    @Test
    public void 在list方法中当URL为contact_list时返回contact_list() {
        List<Contact> contactList = new ArrayList<Contact>();
        when(contactService.findAll()).thenReturn(contactList);
        
        assertEquals("contact/list", contactController.list(model));
        verify(contactService).findAll();
        verify(model).addAttribute("contactList", contactList);
        
    }
    
    @Test
    public void 在show方法中当URL为contact_show时返回contact_show() {
        when(contactService.getById(CONTACT_ID)).thenReturn(contact);
        assertEquals("contact/show", contactController.show(CONTACT_ID, model));
        verify(contactService).getById(CONTACT_ID);
        verify(model).addAttribute("contact", contact);
    }
    
    @Test
    public void 在save方法中调用contactService中的save方法() {
        when(contactService.save(contact)).thenReturn(contact);
        assertEquals("redirect:list", contactController.save(contact));
        verify(contactService).save(contact);
    }
}