package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("contact")
public class ContactController {
    @RequestMapping("list")
    public String list(Model model) {
        model.addAttribute("contactList", new ArrayList<Contact>());
        return "contact/list";
    }
}