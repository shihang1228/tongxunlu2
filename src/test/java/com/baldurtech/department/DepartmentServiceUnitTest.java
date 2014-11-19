package com.baldurtech.department;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;

import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


public class DepartmentServiceUnitTest {
    @Mock
    DepartmentRepository departmentRepository;
    
    @InjectMocks
    DepartmentService departmentService;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void 在findAll方法中应该返回集合departmentList() {
        List<Department> department = new ArrayList<Department>();
        when(departmentRepository.findAll()).thenReturn(department);
        
        assertEquals(department, departmentService.findAll());
        verify(departmentRepository).findAll();
    }
}