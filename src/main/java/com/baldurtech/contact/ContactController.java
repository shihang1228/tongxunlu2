package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
            return "error/dataAccessException";
        } 
    }
}