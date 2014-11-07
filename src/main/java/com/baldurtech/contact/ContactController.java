package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;

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
        if(null == id || id.trim().length() == 0) {
            return "redirect:list";
        }

        if(null != contactService.show(Long.valueOf(id))) {
            model.addAttribute("contact", contactService.show(Long.valueOf(id)));
            return "contact/show";
        } else {
            return "redirect:list";
        }
    }
    
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create() {
        return "contact/create";
    }
    
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("contact") Contact contact, Model model) {
        contactService.save(contact);
        model.addAttribute("id", contact.getId());
        return "redirect:show";
    } 
}