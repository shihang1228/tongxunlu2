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
    private Contact contact = new Contact();
    private Long CONTACT_ID = 5L;
    
    @Mock
    ContactRepository contactRepository;
    
    @InjectMocks
    ContactService contactService;
    
    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        contact.setName("XiaoBai");
        contact.setMobile("18234105437");
        contact.setVpmn("62222");
        contact.setEmail("a@a.com");
        contact.setHomeAddress("ShanXi");
        contact.setOfficeAddress("TaiYuan");
        contact.setJob("HR");
        contact.setJobLevel(1L);
        contact.setMemo("memo");
    }
    public List<Contact> 当Contact存在时返回contactList() {
        List<Contact> contactList = new ArrayList<Contact>();
        
        contactList.add(contact);   
        when(contactRepository.findAll()).thenReturn(contactList);
        
        return contactList;
    }
    
    public void 当Contact不存在时返回null() {
        when(contactRepository.findAll()).thenReturn(NON_CONTACT_EXISTS);
    }
    
    @Test
    public void 在list方法中验证ContactService可以正确的使用ContactRepository的findAll方法() {
        List<Contact> contactList = 当Contact存在时返回contactList();
        
        assertEquals(contactList, contactService.list());
        verify(contactRepository, times(1)).findAll();
    }
    
    @Test
    public void 在list方法中验证contactRepository的返回值为null时返回null() {
        当Contact不存在时返回null();
    
        assertNull(contactService.list());
        verify(contactRepository, times(1)).findAll();
    }
    
    @Test
    public void 在show方法中当contactRepository的getById方法的返回值为null时应该返回null() {
        when(contactRepository.getById(CONTACT_ID)).thenReturn(null);
        
        assertNull(contactService.show(CONTACT_ID));
        verify(contactRepository, times(1)).getById(CONTACT_ID);
    }
    
    @Test
    public void 在show方法中当当contactRepository的getById方法的返回值不为null时应该放回contact() {
        when(contactRepository.getById(CONTACT_ID)).thenReturn(contact);
        
        assertEquals(contact, contactService.show(CONTACT_ID));
        verify(contactRepository, times(2)).getById(CONTACT_ID);
    }
}