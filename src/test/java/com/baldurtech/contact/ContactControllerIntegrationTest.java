package com.baldurtech.contact;

import org.junit.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.baldurtech.config.WebAppConfigurationAware;

public class ContactControllerIntegrationTest extends WebAppConfigurationAware {

    @Test 
    public void 当URL为contact_list时应该访问list页面() throws Exception {
        mockMvc.perform(get("/contact/list"))
            .andExpect(view().name("contact/list"))
            .andExpect(model().attributeExists("contactList"));
    }
}