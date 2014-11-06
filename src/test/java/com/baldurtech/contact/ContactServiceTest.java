package com.baldurtech.contact;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ContactServiceTest {
    private final List<Contact> NON_CONTACT_EXISTS = null;
    
    @Mock
    ContactRepository contactRepository;
    
    @InjectMocks
    ContactService contactService;
    
    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    } 
    
    @Test
    public void 验证ContactService可以正确的使用ContactRepository的findAll方法() {
        List<Contact> contactList = new ArrayList<Contact>();
        Contact contact1 = new Contact();
        contact1.setName("Xiao Bai");
        Contact contact2 = new Contact();
        contact2.setName("Shi Hang");
        
        contactList.add(contact1);
        contactList.add(contact2);
        
        when(contactRepository.findAll()).thenReturn(contactList);
        
        assertEquals(contactList, contactService.list());
        verify(contactRepository, times(1)).findAll();
    }
    
    @Test
    public void 验证contactRepository的返回值为null时返回null() {
        when(contactRepository.findAll()).thenReturn(NON_CONTACT_EXISTS);
        
        assertNull(contactService.list());
        verify(contactRepository, times(1)).findAll();
    }
}