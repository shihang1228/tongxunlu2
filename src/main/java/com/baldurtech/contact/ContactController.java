package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.ui.Model;

@Controller
@RequestMapping("contact")
public class ContactController {
    ContactService contactService;
    
    @Autowired
    public ContactController (ContactService contactService) {
        this.contactService = contactService;
    }
    
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        if(null != contactService.list()) {
            model.addAttribute("contactList", contactService.list());
            return "contact/list";
        } else {
            return "contact/create";
        }
    }
    
    @RequestMapping(value = "show", method = RequestMethod.GET) 
    public String show(@RequestParam(value = "id") String id, Model model) {
        Contact contact = new Contact();
        contact.setName("Shi Hang");
        contact.setMobile("18233333333");
        contact.setVpmn("6555");
        contact.setEmail("a@a.com");
        contact.setHomeAddress("TaiYuan");
        contact.setOfficeAddress("BeiZhang");
        contact.setJob("HR");
        contact.setJobLevel(9L);
        contact.setMemo("memo");
        
        model.addAttribute("contact", contact);
        return "contact/show";
    }
}