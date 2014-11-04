package com.baldurtech.contact;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;


public class ContactControllerUnitTest {
    private Long CONTACT_ID = 1L;
    private Contact contact = new Contact();
    
    @Mock
    ContactService contactService;
    
    @Mock
    Model model;
    
    @Mock
    BindingResult result;
    
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
    public void 在contactController中的list方法中将会调用ContactService中的list方法并验证返回值() {
        assertEquals("contact/list", contactController.list(model));
        verify(contactService).list();
    }
    
    @Test
    public void 在contactController中的show方法中将会调用ContactService中的show方法并验证返回值() {
        assertEquals("contact/show", contactController.show(String.valueOf(CONTACT_ID), model));
        verify(contactService).show(CONTACT_ID);
    }
    
    @Test
    public void 在save方法中如果页面验证有错则返回create页面() {
        when(result.hasErrors()).thenReturn(true);
        assertEquals("contact/create", contactController.save(contact, result, model));
    }
    
    @Test
    public void 在save方法中当contact不为空时将会调用contactService中的save方法并验证返回值() {
        assertEquals("redirect:show", contactController.save(contact, result, model));
        verify(contactService).save(contact);
    }
    
    @Test
    public void 在save方法中当contact为空时将会返回到create页面() {
        assertEquals("contact/create", contactController.save(null, result, model));
    }
    
    @Test
    public void 在contactController中的update方法中将会调用ContactService中的show方法() {
        contactController.update(CONTACT_ID, model);
        verify(contactService).show(CONTACT_ID);
    }
    
    @Test
    public void 在contactController中的update方法中将会调用ContactService中的update方法() {
        contactController.update(contact, model);
        verify(contactService).update(any(Contact.class));
    }
    
    @Test
    public void 在contactController中的delete方法中将会调用ContactService中的delete方法() {
        contactController.delete(String.valueOf(CONTACT_ID));
        verify(contactService).delete(CONTACT_ID);
    }
}