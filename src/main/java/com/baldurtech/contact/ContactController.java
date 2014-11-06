package com.baldurtech.contact;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("contact")
public class ContactController {
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        return "contact/list";
    }
}