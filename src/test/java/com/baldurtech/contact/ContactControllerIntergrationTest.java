package com.baldurtech.contact;

import org.junit.Test;
import org.junit.Before;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.beans.factory.annotation.Autowired;

import com.baldurtech.config.WebAppConfigurationAware;

public class ContactControllerIntergrationTest extends WebAppConfigurationAware {
    private Long CONTACT_ID = 4L;
    private Contact contact;
    
    private ContactRepository contactRepository;
    
    @Autowired
    ContactService contactService = new ContactService(contactRepository);
    
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
               .andExpect(model().attributeExists("contact"))
               .andExpect(view().name("contact/show"));
    }
    
    @Test
    public void 当URL为contact_create时应该访问create页面() throws Exception {
        mockMvc.perform(get("/contact/create"))
            .andExpect(model().attributeExists("contact"))
            .andExpect(view().name("contact/create"));
    }
    
    @Test
    public void 当URL为contact_save时应该post到save方法() throws Exception {
        contactService.save(contact);
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
            .andExpect(redirectedUrl("show?id=" + (contactService.findAll().get(contactService.findAll().size() - 1)).getId()));
    }
    
    @Test
    public void 当URL为contact_update时应该post到update方法() throws Exception {
        mockMvc.perform(get("/contact/update")
                        .param("id", String.valueOf(CONTACT_ID)))
            .andExpect(view().name("contact/update"))
            .andExpect(model().attributeExists("contact"));
    }
}