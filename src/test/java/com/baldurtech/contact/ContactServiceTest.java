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

public class ContactServiceTest {
    Contact contact;
    
    @Mock
    ContactRepository contactRepository;
    
    @InjectMocks
    ContactService contactService;
    
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
    public void 在findAll方法中应该正确调用contactRepository中的findAll方法() {
        List<Contact> contactList = new ArrayList<Contact>();
        contactList.add(contact);
        when(contactRepository.findAll()).thenReturn(contactList);
        
        assertEquals(contactList, contactService.findAll());
        verify(contactRepository).findAll();
    } 
}