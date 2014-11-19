package com.baldurtech.department;

import java.util.List;
import java.util.ArrayList;
import org.springframework.ui.Model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
 
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class DepartmentControllerUnitTest {
    @Mock
    Model model;
    
    @Mock
    DepartmentService departmentService;
    
    @InjectMocks
    DepartmentController departmentController;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void 在list方法中当URL为department_list时应该访问list页面() {
        List<Department> departmentList = new ArrayList<Department>();
        
        when(departmentService.list()).thenReturn(departmentList);
        assertEquals("department/list", departmentController.list(model));
        verify(departmentService).list();
        verify(model).addAttribute("departmentList", departmentList);
    }
}