package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;
import java.security.Principal;

import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.any;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.baldurtech.account.*;

public class ContactControllerUnitTest {
    private Long CONTACT_ID = 4L;
    Contact contact;
    Contact contact_has_saved;
    Contact contact_has_updated;
    Account user_account;
    Account admin_account;
    
    @Mock
    AccountRepository accountRepository;
    
    @Mock
    ContactService contactService;
    
    @Mock
    Principal principal;
    
    @Mock
    Model model;
    
    @Mock
    BindingResult bindingResult;
    
    @InjectMocks
    ContactController contactController;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        
        user_account = new Account("a@a.com", "123", "ROLE_USER");
        admin_account = new Account("b@b.com", "admin", "ROLE_ADMIN");
        
        contact = createContact(contact);
        contact.setId(null);
            
        contact_has_saved = createContact(contact_has_saved);
        
        contact_has_updated = createContact(contact_has_updated);
        contact_has_updated.setName("Xiao Bai");
    } 
    
    public Contact createContact(Contact contact) {
        contact = new Contact();
        contact.setId(9L);
        contact.setName("Shihang");
        contact.setMobile("15235432994");
        contact.setVpmn("652994");
        contact.setHomeAddress("taiyuan");
        contact.setOfficeAddress("taiyuan");
        contact.setMemo("memo");
        contact.setJob("HR");
        contact.setJobLevel(4L);
        
        return contact;
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
    public void 当角色为user时调用create方法应该重定向到forbidden() {
        when(accountRepository.findByEmail(principal.getName())).thenReturn(user_account);
        
        assertEquals("contact/forbidden", contactController.create(model, principal));
    }
    
    @Test
    public void 当角色为admin时调用create方法应该访问create页面() {
        when(accountRepository.findByEmail(principal.getName())).thenReturn(admin_account);
        
        assertEquals("contact/create", contactController.create(model, principal));
    }
    
    @Test
    public void contact合法时在save方法中调用contactService中的save方法应该重定向到show页面() {
        when(contactService.save(contact)).thenReturn(contact_has_saved);
        when(bindingResult.hasErrors()).thenReturn(false);
        
        assertEquals("redirect:show", contactController.save(contact, bindingResult, model));
        verify(contactService).save(contact);
    }
    
    @Test
    public void 当contact不合法时在应该访问create页面() {
        when(bindingResult.hasErrors()).thenReturn(true);
        
        assertEquals("contact/create", contactController.save(contact, bindingResult, model));
        verify(contactService, never()).save(contact);
    }
    
    @Test
    public void 当角色为ADMIN时在edit方法中调用contactService中的getById方法() {
        when(accountRepository.findByEmail(principal.getName())).thenReturn(admin_account);
        when(contactService.getById(contact_has_saved.getId())).thenReturn(contact_has_saved);
        
        assertEquals("contact/update", contactController.edit(contact_has_saved.getId(), model, principal));
        verify(contactService).getById(contact_has_saved.getId());
        verify(model).addAttribute("contact", contact_has_saved);
    }
    
    @Test
    public void 当角色为USER应该访问forbidden页面() {
        when(accountRepository.findByEmail(principal.getName())).thenReturn(user_account);
        
        assertEquals("contact/forbidden", contactController.edit(contact_has_saved.getId(), model, principal));
    }
    
    @Test
    public void 当contact合法时在update方法中调用contactService中的update方法并重定向到show页面() {
        when(contactService.update(contact_has_saved)).thenReturn(contact_has_updated);
        when(bindingResult.hasErrors()).thenReturn(false);
        
        assertEquals("redirect:show", contactController.update(contact_has_saved, bindingResult, model));
        verify(contactService).update(contact_has_saved);
        verify(model).addAttribute("id", contact_has_updated.getId());
    }
    
    @Test 
    public void 当contact不合法时应该返回update页面() {
        when(bindingResult.hasErrors()).thenReturn(true);
        
        assertEquals("contact/update", contactController.update(contact_has_saved, bindingResult, model));
        verify(contactService, never()).update(contact_has_saved);
    }
    @Test
    public void 在delete方法中调用contactService中的delete方法() {
        assertEquals("redirect:list", contactController.delete(CONTACT_ID));
        verify(contactService).delete(CONTACT_ID);
    }
}