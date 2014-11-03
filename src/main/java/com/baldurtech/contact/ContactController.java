package com.baldurtech.contact;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("contact")
public class ContactController {
    @RequestMapping(value = "list")
    public String list() {
        return "contact/list";
    }
}