package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    public List<Contact> findAll() {
        List<Contact> contactList = new ArrayList<Contact>();
        Contact contact = new Contact();
        contact.setName("Shihang");
        contact.setMobile("15235432994");
        contact.setVpmn("652994");
        contact.setHomeAddress("taiyuan");
        
        Contact contact2 = new Contact();
        contact2.setName("Shihang");
        contact2.setMobile("15235432994");
        contact2.setVpmn("652994");
        contact2.setHomeAddress("taiyuan");
        
        contactList.add(contact);
        contactList.add(contact2);
        
        return contactList;
    }
}