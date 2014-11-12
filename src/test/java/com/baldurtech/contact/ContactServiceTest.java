package com.baldurtech.contact;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.ArrayList;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import javax.persistence.*;

import com.baldurtech.exception.*;

public class ContactServiceTest {
    private Long CONTACT_ID = 4L;
    Contact contact;
    
    @Rule
    public ExpectedException thrown= ExpectedException.none();
    
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
    public void 在findAll方法中应该正确调用contactRepository中的findAll方法() throws Exception {
        List<Contact> contactList = new ArrayList<Contact>();
        contactList.add(contact);
        when(contactRepository.findAll()).thenReturn(contactList);
        
        assertEquals(contactList, contactService.findAll());
        verify(contactRepository).findAll();
    } 
    
    @Test
    public void 在findAll方法中查询失败后抛DataAccessException异常() throws DataAccessException {
        when(contactRepository.findAll()).thenThrow(new PersistenceException());
        
        thrown.expect(DataAccessException.class);
        thrown.expectMessage("Can not query any record!");
        contactService.findAll();
    }
    
    @Test
    public void 在getById方法中如果查询失败后抛出DataAccessException异常() throws DataAccessException {
        when(contactRepository.getById(CONTACT_ID)).thenThrow(new PersistenceException());
        
        thrown.expect(DataAccessException.class);
        thrown.expectMessage("Can not query any record!");
        contactService.getById(CONTACT_ID);
    }
    
    @Test
    public void 在getById方法中如果contactRepository中的getById方法返回null则应该返回null() throws DataAccessException {
        when(contactRepository.getById(CONTACT_ID)).thenReturn(null);
        assertNull(contactService.getById(CONTACT_ID));
    }
    
    @Test
    public void 在getById方法中如果contactRepository中的getById方法不返回null则应该返回contact() throws DataAccessException {
        when(contactRepository.getById(CONTACT_ID)).thenReturn(contact);
        assertEquals(contact, contactService.getById(CONTACT_ID));
    }
}