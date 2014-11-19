package com.baldurtech.contact;

import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.After;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.beans.factory.annotation.Autowired;

import com.baldurtech.config.WebSecurityConfigurationAware;

public class ContactControllerIntergrationTest extends WebSecurityConfigurationAware {
    private Long CONTACT_ID = 4L;
    private Contact contact;
    
    private ContactRepository contactRepository;
    
    @Autowired
    ContactService contactService = new ContactService(contactRepository);
    
    @Before
    public void setup() {
        contact = new Contact();
        contactService.save(createContact(contact));
    }
    
    @After
    public void teardown() {
        contactService.delete(contact.getId());
    }
    
    public Contact createContact(Contact contact) {
        contact.setName("ShiHang");
        contact.setMobile("15235432994");
        contact.setEmail("shihang@qq.com");
        contact.setHomeAddress("TaiYuan");
        contact.setVpmn("652994");
        contact.setOfficeAddress("BeiZhang");
        contact.setMemo("Memo");
        contact.setJob("HR");
        contact.setJobLevel(3L);
        
        return contact;
    }
    
    protected MockHttpServletRequestBuilder postContactTo(String url) {
        return post(url).param("name", contact.getName())
                   .param("mobile", contact.getMobile())
                   .param("vpmn", contact.getVpmn())
                   .param("email", contact.getEmail())
                   .param("homeAddress", contact.getHomeAddress())
                   .param("officeAddress", contact.getOfficeAddress())
                   .param("memo", contact.getMemo())
                   .param("job", contact.getJob())
                   .param("jobLevel", String.valueOf(contact.getJobLevel()));
    }
    
    @Test
    public void 当URL为contact_delete时应该执行delete方法() throws Exception{
        Contact contact = new Contact();;
        contactService.save(createContact(contact));
        
        userPerform(post("/contact/delete")
                       .param("id", String.valueOf(contact.getId())))
               .andExpect(redirectedUrl("list"));
    }
    
    @Test
    public void 当URL为contact_list时应该访问list页面() throws Exception {
        userPerform(get("/contact/list"))
               .andExpect(view().name("contact/list"))
               .andExpect(model().attributeExists("contactList"));
    }
    
    @Test
    public void 当URL为contact_show时应该访问show页面() throws Exception {
        userPerform(get("/contact/show")
                        .param("id", String.valueOf(CONTACT_ID)))
               .andExpect(model().attributeExists("contact"))
               .andExpect(view().name("contact/show"));
    }
    
    @Test 
    public void 当角色为USER时访问contact_create时将重定向到contact_forbidden页面() throws Exception {
        userPerform(get("/contact/create"))
            .andExpect(view().name("contact/forbidden"));
    }
    
    @Test 
    public void 当角色为ADMIN时访问contact_create时应该访问contact_create页面() throws Exception {
        adminPerform(get("/contact/create"))
            .andExpect(model().attributeExists("contact"))
            .andExpect(view().name("contact/create"));
    }
    
    
    @Test 
    public void 当角色为ADMIN时访问URL为contact_save时应该post到save方法() throws Exception {
        adminPerform(postContactTo("/contact/save"))
            .andExpect(redirectedUrl("show?id=" + (contactService.findAll().get(contactService.findAll().size() - 1)).getId()));
    }
    
    @Test 
    public void 当角色为USER时访问URL为contact_update时应该重定向到forbidden页面() throws Exception {
        userPerform(get("/contact/update")
                        .param("id", String.valueOf(CONTACT_ID)))
            .andExpect(view().name("contact/forbidden"));
    }
    
    @Test 
    public void 当角色为ADMIN时访问URL为contact_update时应该访问update页面() throws Exception {
        adminPerform(get("/contact/update")
                        .param("id", String.valueOf(CONTACT_ID)))
            .andExpect(model().attributeExists("contact"))
            .andExpect(view().name("contact/update"));
    }
    
    @Test
    public void 当URL为contact_update时应该post到update方法() throws Exception {
        userPerform(postContactTo("/contact/update")
                       .param("id", String.valueOf(contact.getId())))
            .andExpect(redirectedUrl("show?id=" + contact.getId()));
    }
}