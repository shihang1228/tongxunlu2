package com.baldurtech.department;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("department")
public class DepartmentController {
    @RequestMapping("list")
    public String list() {
        return "department/list";
    }
} 