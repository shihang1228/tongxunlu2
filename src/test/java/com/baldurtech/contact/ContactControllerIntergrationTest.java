package com.baldurtech.contact;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Ignore;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import com.baldurtech.config.WebSecurityConfigurationAware;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

public class ContactControllerIntergrationTest extends WebSecurityConfigurationAware {
    private Long CONTACT_ID = 1L;
    private Contact contact;
    private ContactRepository contactRepository;
    
    @Autowired
    ContactService contactService = new ContactService(contactRepository);
    
    @Before
    public void setup() {
        contact = new Contact();
        contactService.save(createContact(contact));
    }
    private Contact createContact(Contact contact){
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
    
    protected ResultActions userPostPerform(MockHttpServletRequestBuilder request)
        throws Exception {
        return mockMvc.perform(request.session(userSession())    
                       .param("name", contact.getName())
                       .param("mobile", contact.getMobile())
                       .param("vpmn", contact.getVpmn())
                       .param("email", contact.getEmail())
                       .param("homeAddress", contact.getHomeAddress())
                       .param("officeAddress", contact.getOfficeAddress())
                       .param("memo", contact.getMemo())
                       .param("job", contact.getJob())
                       .param("jobLevel", String.valueOf(contact.getJobLevel())));
    }
    
    @After
    public void teardown() {
        contactService.delete(contact.getId());
    }
    
    @Test
    public void 当URL为contact_list时应该访问list页面() throws Exception{
        userPerform(get("/contact/list"))
               .andExpect(view().name("contact/list"))
               .andExpect(model().attributeExists("contactList"));
    }
    
    @Test 
    public void 当URL为contact_show时应该访问show页面() throws Exception {
        userPerform(get("/contact/show")
                        .param("id", String.valueOf(contact.getId())))
               .andExpect(model().attributeExists("contact"))
               .andExpect(view().name("contact/show"));
    }
    
    @Test
    public void 当用户角色为ROLE_USER时应该访问forbidden页面() throws Exception {
        userPerform(get("/contact/create"))
               .andExpect(view().name("contact/forbidden"));
    }
    
    @Test  
    public void 当URL为contact_save时应该重定向到list页面() throws Exception {
        userPostPerform(post("/contact/save"))
              .andExpect(model().attributeExists("id"))
              .andExpect(redirectedUrl("show?id=" + (contact.getId()+1)));
    }
    
    @Test  
    public void 当URL为contact_update时应该访问update页面() throws Exception {
        userPerform(get("/contact/update")
                       .param("id", String.valueOf(contact.getId())))
               .andExpect(model().attributeExists("contact"))
               .andExpect(view().name("contact/update"));
    }
    
    @Test 
    public void 当点击update页面中的update按钮时应该重定向到show页面() throws Exception{
        userPostPerform(post("/contact/update")
                       .param("id", String.valueOf(contact.getId())))
               .andExpect(model().attributeExists("id"))
               .andExpect(redirectedUrl("show?id=" + contact.getId()));
    }
    
    @Test 
    public void 当在update页面中点击delete时重定向到list页面() throws Exception{
        Contact contact = new Contact();
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
        
        contactService.save(contact);
        
        userPerform(post("/contact/delete")
                       .param("id", String.valueOf(contact.getId())))
               .andExpect(redirectedUrl("list"));
    }
}