package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;

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
        
        model.addAttribute("contactList", contactService.list());
        return "contact/list";
    }
    
    @RequestMapping(value = "show")
    public String show(@RequestParam(value = "id", required = true) String id, Model model) {
             
        model.addAttribute("contact", contactService.show(Long.valueOf(id)));
        return "contact/show";
    }
    
    @RequestMapping(value = "create")
    public String create(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact/create";
    }
    
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("contact") Contact contact) {
        contactService.save(contact);
        return "redirect:list";
        
    }    
}