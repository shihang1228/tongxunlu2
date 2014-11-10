package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class ContactService {
    public List<Contact> findAll() {
        Contact contact = new Contact();
        List<Contact> contactList = new ArrayList<Contact>();
        
        contact.setName("ShiHang");
        contact.setMobile("15235432994");
        contact.setEmail("shihang@qq.com");
        contact.setHomeAddress("TaiYuan");
        contact.setVpmn("652994");
        
        contactList.add(contact);
        return contactList;
    }
}