package com.baldurtech.contact;

import org.junit.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.baldurtech.config.WebAppConfigurationAware;

public class ContactControllerIntegrationTest extends WebAppConfigurationAware{
    private Long CONTACT_ID = 4L;
    
    @Test
    public void 当URL为Contact_list时应该访问list页面() throws Exception{
        mockMvc.perform(get("/contact/list"))
               .andExpect(model().attributeExists("contactList"))
               .andExpect(view().name("contact/list"));
    }
    
    @Test
    public void 当URL为Contact_show时应该访问show页面() throws Exception {
        mockMvc.perform(get("/contact/show")
                        .param("id", String.valueOf(CONTACT_ID)))
               .andExpect(model().attributeExists("contact"))
               .andExpect(view().name("contact/show"));
    }
    
    @Test
    public void 当URL为Contact_create时应该访问create页面() throws Exception {
        mockMvc.perform(get("/contact/create"))
               .andExpect(view().name("contact/create"));
    }
}