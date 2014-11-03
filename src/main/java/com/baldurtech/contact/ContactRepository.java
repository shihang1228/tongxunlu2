package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository {
    public List<Contact> findAll() {
        List<Contact> contactList = new ArrayList<Contact>();
        Contact contact = new Contact();
        contact.setName("ShiHang");
        contact.setMobile("15235432994");
        contact.setVpmn("652994");
        contact.setHomeAddress("TaiYuan");
        
        contactList.add(contact);
        return contactList;
    }
}