package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import org.springframework.ui.Model;

public class ContactControllerUnitTest {
    private Long CONTACT_ID = 4L;
    Contact contact;
    Contact contact_has_saved;
    Contact contact_has_updated;
    
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
        
        contact_has_updated = new Contact();
        contact_has_updated.setId(9L);
        contact_has_updated.setName("XiaoBai");
        contact_has_updated.setMobile("15235432994");
        contact_has_updated.setVpmn("652994");
        contact_has_updated.setHomeAddress("taiyuan");
        contact_has_updated.setOfficeAddress("taiyuan");
        contact_has_updated.setMemo("memo");
        contact_has_updated.setJob("HR");
        contact_has_updated.setJobLevel(4L);
        
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
    
    @Test @Ignore
    public void 在create方法中当URL为contact_create时应该调用model() {
        contactController.create(model);
        verify(model).addAttribute("contact", contact);
    }
    
    @Test
    public void 在save方法中调用contactService中的save方法() {
        when(contactService.save(contact)).thenReturn(contact_has_saved);

        assertEquals("redirect:show", contactController.save(contact, model));
        verify(contactService).save(contact);
    }
    
    @Test
    public void 在edit方法中调用contactService中的getById方法() {
        when(contactService.getById(contact_has_saved.getId())).thenReturn(contact_has_saved);
        
        assertEquals("contact/update", contactController.edit(contact_has_saved.getId(), model));
        verify(contactService).getById(contact_has_saved.getId());
        verify(model).addAttribute("contact", contact_has_saved);
    }
    
    @Test
    public void 在update方法中调用contactService中的update方法() {
        when(contactService.update(contact_has_saved)).thenReturn(contact_has_updated);
        
        assertEquals("redirect:show", contactController.update(contact_has_saved, model));
        verify(contactService).update(contact_has_saved);
        verify(model).addAttribute("id", contact_has_updated.getId());
    }
}