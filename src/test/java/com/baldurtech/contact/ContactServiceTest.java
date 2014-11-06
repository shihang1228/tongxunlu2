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

public class ContactServiceTest {
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
}