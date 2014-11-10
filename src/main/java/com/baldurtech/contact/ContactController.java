package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;

import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("contact")
public class ContactController {
    ContactService contactService;
    
    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }
    
    @RequestMapping("list")
    public String list(Model model) {
        
        model.addAttribute("contactList", contactService.findAll());
        return "contact/list";
    }
}