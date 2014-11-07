package com.baldurtech.contact;

import org.junit.Before;
import org.junit.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.baldurtech.config.WebAppConfigurationAware;

public class ContactControllerIntegrationTest extends WebAppConfigurationAware {
    private Long CONTACT_ID = 3L;
    private Contact contact;
    
    @Before
    public void setup() {
        contact = new Contact();
        contact.setName("ShiHang");
        contact.setMobile("15235432994");
        contact.setEmail("shihang@qq.com");
        contact.setHomeAddress("TaiYuan");
        contact.setVpmn("652994");
        contact.setOfficeAddress("BeiZhang");
        contact.setMemo("Memo");
        contact.setJob("HR");
        contact.setJobLevel(3L);
    }
    
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
            .andExpect(view().name("contact/show"))
            .andExpect(model().attributeExists("contact"));
    }
    
    @Test
    public void 当URL为contact_create时应该访问create页面() throws Exception {
        mockMvc.perform(get("/contact/create"))
               .andExpect(view().name("contact/create"));
    }
    
    @Test
    public void 当URL为contact_save时应该重定向到show页面() throws Exception {
        mockMvc.perform(post("/contact/save")
                       .param("name", contact.getName())
                       .param("mobile", contact.getMobile())
                       .param("vpmn", contact.getVpmn())
                       .param("email", contact.getEmail())
                       .param("homeAddress", contact.getHomeAddress())
                       .param("officeAddress", contact.getOfficeAddress())
                       .param("memo", contact.getMemo())
                       .param("job", contact.getJob())
                       .param("jobLevel", String.valueOf(contact.getJobLevel())))
               .andExpect(model().attributeExists("id"))
               .andExpect(redirectedUrl("show?id=8"));
    }
}