package com.baldurtech.department;
 
import java.util.List;
import java.util.ArrayList;
 
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("department")
public class DepartmentController {
    DepartmentService departmentService;
    
    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    
    @RequestMapping("list")
    public String list(Model model) {
        model.addAttribute("departmentList", departmentService.findAll());
        return "department/list";
    }
} 