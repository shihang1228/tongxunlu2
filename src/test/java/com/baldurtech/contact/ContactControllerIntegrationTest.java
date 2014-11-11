package com.baldurtech.contact;

import org.junit.Test;
import org.junit.Before;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.baldurtech.config.WebAppConfigurationAware;

public class ContactControllerIntegrationTest extends WebAppConfigurationAware{
    private Long CONTACT_ID = 4L;
    private Contact contact;
    
    @Before
    public void setup() {
        contact = new Contact();
        contact.setName("shihang");
        contact.setMobile("15235432994");
        contact.setVpmn("652994");
        contact.setEmail("a@qq.com");
        contact.setHomeAddress("taiyuan");
        contact.setOfficeAddress("beijing");
        contact.setJob("HT");
        contact.setJobLevel(5L);
        contact.setMemo("memo");
    }
    
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
    
    @Test
    public void 当URL为contact_save时应该访问重定向到show页面() throws Exception {
        mockMvc.perform(post("/contact/save")
                       .param("name", contact.getName())
                       .param("mobile", contact.getMobile())
                       .param("vpmn", contact.getVpmn())
                       .param("email", contact.getEmail())
                       .param("homeAddress", contact.getHomeAddress())
                       .param("officeAddress", contact.getOfficeAddress())
                       .param("job", contact.getJob())
                       .param("jobLevel", String.valueOf(contact.getJobLevel()))
                       .param("memo", contact.getMemo()))
               .andExpect(redirectedUrl("show?id=8"));
    }
}