package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baldurtech.exception.*;

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
        try{
            model.addAttribute("contactList", contactService.findAll());
            return "contact/list";
        } catch(DataAccessException dae) {
            return "error/general";
        } 
    }
    
    @RequestMapping("show")
    public String show(@RequestParam("id") Long id, Model model) {
        try {
            Contact contact = contactService.getById(id);
            if(contact != null) {
                model.addAttribute("contact", contact);
                return "contact/show";
            } else {
                return "redirect:list";
            }
        } catch(DataAccessException dae) {
            return "error/general";
        } 
    }

    @RequestMapping("create")
    public String create() {
        return "contact/create";
    }
    
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("contact") Contact contact, Model model) {
        contact = contactService.save(contact);
        if(contact.getId() != null) {
            model.addAttribute("id", contact.getId());
            return "redirect:show";
        } 
        return "redirect:create";
    }
}