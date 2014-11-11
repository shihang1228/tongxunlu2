package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("contact", getById(id));
        return "contact/show";
    }
    
    public Contact getById(Long id) {
        Contact contact = new Contact();
        contact.setId(id);
        contact.setName("Xiao Bai");
        contact.setMobile("15235432994");
        contact.setVpmn("652994");
        contact.setEmail("a@q.com");
        contact.setHomeAddress("taiyuan");
        contact.setOfficeAddress("beizhang");
        contact.setJob("HR");
        contact.setJobLevel(4L);
        contact.setMemo("Xiao Bai");
        
        return contact;
    }
}