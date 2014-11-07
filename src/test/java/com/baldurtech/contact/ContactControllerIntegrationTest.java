package com.baldurtech.contact;

import org.junit.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.baldurtech.config.WebAppConfigurationAware;

public class ContactControllerIntegrationTest extends WebAppConfigurationAware {
    private Long CONTACT_ID = 9L;
    
    @Test 
    public void 当URL为contact_list时应该访问list页面() throws Exception {
        mockMvc.perform(get("/contact/list"))
            .andExpect(view().name("contact/list"))
            .andExpect(model().attributeExists("contactList"));
    } 

    @Test
    public void 当URL为contact_show时应该访问show页面() throws Exception {
        mockMvc.perform(get("/contact/show")
                        .param("id", String.valueOf(CONTACT_ID)))
            .andExpect(view().name("contact/show"));
    }
}