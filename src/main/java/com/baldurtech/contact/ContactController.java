package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("contact")
public class ContactController {
    @RequestMapping(value = "list")
    public String list(Model model) {
        List<Contact> contactList = new ArrayList<Contact>();
        Contact contact = new Contact();
        contact.setName("ShiHang");
        contact.setMobile("15235432994");
        contact.setVpmn("652994");
        contact.setHomeAddress("TaiYuan");
        
        contactList.add(contact);
        model.addAttribute("contactList", contactList);
        return "contact/list";
    }
}