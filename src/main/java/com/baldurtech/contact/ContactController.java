package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("contact")
public class ContactController {
    @RequestMapping("list")
    public String list(Model model) {
        model.addAttribute("contactList", findAll());
        return "contact/list";
    }
    
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