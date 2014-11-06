package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
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
        if(null != contactService.findAll()) {
            model.addAttribute("contactList", contactService.findAll());
            return "contact/list";
        } else {
            return "contact/create";
        }
    }
}