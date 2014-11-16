package com.baldurtech.contact;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class ContactServiceUnitTest {
    private Long CONTACT_ID = 4L;
    Contact contact;
    Contact contact_has_saved;
    
    @Mock
    ContactRepository contactRepository;
    
    @InjectMocks
    ContactService contactService;
    
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
        contact_has_saved.setId(16L);
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
    public void 在findAll方法中应该返回集合contactList() {
        List<Contact> contactList = new ArrayList<Contact>();
        when(contactRepository.findAll()).thenReturn(contactList);
        
        assertEquals(contactList, contactService.findAll());
        verify(contactRepository).findAll();
        
    }
    
    @Test
    public void 在getById方法中应该返回指定id的contact() {
        when(contactRepository.getById(CONTACT_ID)).thenReturn(contact);
        
        assertEquals(contact, contactService.getById(CONTACT_ID));
        verify(contactRepository).getById(CONTACT_ID);
    }
    
    @Test
    public void 在save方法中应该调用ContactService的save方法() {
        when(contactRepository.save(contact)).thenReturn(contact_has_saved);
        
        assertEquals(contact, contactService.save(contact));
        verify(contactRepository).save(contact);
    }
}