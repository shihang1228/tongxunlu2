package com.baldurtech.department;

import org.junit.Test;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.baldurtech.config.WebAppConfigurationAware;

public class DepartmentControllerIntergrationTest extends WebAppConfigurationAware {
    @Test
    public void 当URL为department_list时应该访问list页面() throws Exception {
        mockMvc.perform(get("/department/list"))
               .andExpect(model().attributeExists("departmentList"))
               .andExpect(view().name("department/list"));
    }
}