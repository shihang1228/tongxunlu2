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

public class ContactServiceUnitTest extends CreateContactData {
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
        
        contact = createContact();
        contact.setId(null);
            
        contact_has_saved = createContact();
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
        when(contactRepository.getById(CONTACT_ID)).thenReturn(contact_has_saved);
        
        assertEquals(contact_has_saved, contactService.getById(CONTACT_ID));
        verify(contactRepository).getById(CONTACT_ID);
    }
    
    @Test
    public void 在save方法中应该调用ContactRepository的save方法() {
        assertEquals(contact, contactService.save(contact));
        verify(contactRepository).save(contact);
    }
    
    @Test
    public void 在update方法中应该调用ContactRepository的update方法并返回更新后的contact() {
        when(contactRepository.update(contact)).thenReturn(contact_has_saved);
        
        assertEquals(contact_has_saved, contactService.update(contact));
        verify(contactRepository).update(contact);
    }
    
    @Test
    public void 在delete方法中应该调用contactRepository的delete方法() {
        contactService.delete(CONTACT_ID);
        verify(contactRepository).delete(CONTACT_ID);
    }
}