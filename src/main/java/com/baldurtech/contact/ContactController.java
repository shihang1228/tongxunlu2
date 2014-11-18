package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;
import java.security.Principal;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.validation.BindingResult;
import javax.validation.Valid;

import com.baldurtech.account.*;

@Controller
@RequestMapping("contact")
public class ContactController {
    ContactService contactService;
    AccountRepository accountRepository;
    
    @Autowired
    public ContactController(ContactService contactService, AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
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
    public String create(Model model, Principal principal) {
        if(assertRole("ROLE_USER", principal.getName())) {
            return "contact/forbidden";
        }
        model.addAttribute("contact", new Contact());
        return "contact/create";
    }
    
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("contact") Contact contact, BindingResult bingdingResult, Model model) {
        if(bingdingResult.hasErrors()) {
            return "contact/create";
        }else {
            contactService.save(contact);
            model.addAttribute("id", contact.getId());
            return "redirect:show";
        }
    }
    
    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long id, Model model, Principal principal) {  
        if(assertRole("ROLE_USER", principal.getName())) {
            return "contact/forbidden";
        }
        
        model.addAttribute("contact", contactService.getById(id));  
        return "contact/update";
    }
    
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("contact") Contact contact, BindingResult bingdingResult, Model model) {
        if(bingdingResult.hasErrors()) {
            return "contact/update";
        }else {
            contactService.update(contact);
            model.addAttribute("id", contact.getId());
            return "redirect:show";
        }
    }
    
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Long id) {
        contactService.delete(id);
        return "redirect:list";
    }
    
     public Boolean assertRole( String role, String accountName) {
        Account account = accountRepository.findByEmail(accountName);
        return role.equals(account.getRole());
    }
    
}