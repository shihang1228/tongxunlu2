package com.baldurtech.department;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentRepository {
    public List<Department> findAll() {
        List<Department> departmentList = new ArrayList<Department>();
        Department department1 = new Department();
        department1.setName("technologyDepartment");
        department1.setAddress("chenyu");
        department1.setParent("HRDepartment");
        department1.setMemo("memo");
        
        Department department2 = new Department();
        department2.setName("technologyDepartment");
        department2.setAddress("chenyu");
        department2.setParent("HRDepartment");
        department2.setMemo("memo");
        
        Department department3 = new Department();
        department3.setName("technologyDepartment");
        department3.setAddress("chenyu");
        department3.setParent("HRDepartment");
        department3.setMemo("memo");
        
        departmentList.add(department1);
        departmentList.add(department2);
        departmentList.add(department3);
        
        return departmentList;
    }
}