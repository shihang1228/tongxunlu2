package com.baldurtech.department;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DepartmentRepositoryUnitTest {
    @Mock
    EntityManager entityManager;
    
    @Mock
    TypedQuery typedQuery;
    
    @InjectMocks
    DepartmentRepository departmentRepository;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        departmentRepository = new DepartmentRepository();
        departmentRepository.setEntityManager(entityManager);
    }
    
    @Test
    public void 在findAll方法中如果数据库中存在部门列表应该返回部门列表() {
        List<Department> departmentList = new ArrayList<Department>();
        when(entityManager.createNamedQuery(Department.FIND_ALL, Department.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(departmentList);
        
        assertEquals(departmentList, departmentRepository.findAll());
    }
    
}