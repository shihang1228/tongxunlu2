package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("contact")
public class ContactController {
    ContactService contactService;
    
    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("contactList", contactService.findAll());
        return "contact/list";
    }
    
    @RequestMapping(value = "show")
    public String show(@RequestParam("id") Long id, Model model) {   
        model.addAttribute("contact", contactService.getById(id));
        return "contact/show";
    }
    
    @RequestMapping(value = "create")
    public String create() {
        return "contact/create";
    }
    
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("contact") Contact contact) {
        contactService.save(contact);
        return "redirect:list";
    }
}