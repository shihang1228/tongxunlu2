package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Ignore;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import com.baldurtech.config.WebSecurityConfigurationAware;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.mock.web.MockHttpSession;

public class ContactControllerIntergrationTest extends WebSecurityConfigurationAware {
    private Long CONTACT_ID = 1L;
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
        
        contactService.save(contact);
    }

    protected MockHttpSession userSession() {
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
        Authentication userAuthentication = 
            new UsernamePasswordAuthenticationToken("user","demo", authorities);
            
        SecurityContext securityContext = org.mockito.Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(userAuthentication);
        
        MockHttpSession userSession = new MockHttpSession();
        userSession.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, 
                securityContext);
                
        return userSession;
    }
    
    protected org.springframework.test.web.servlet.ResultActions userPerform(org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder request)
        throws Exception {
        return mockMvc.perform(request.session(userSession()));
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
    
    @Test @Ignore
    public void 当URL为contact_show时应该访问show页面() throws Exception {
        mockMvc.perform(get("/contact/show")
                        .param("id", String.valueOf(contact.getId())))
               .andExpect(model().attributeExists("contact"))
               .andExpect(view().name("contact/show"));
    }
    
    @Test @Ignore
    public void 当URL为contact_create时应该访问create页面() throws Exception {
        mockMvc.perform(get("/contact/create"))
               .andExpect(model().attributeExists("contact"))
               .andExpect(view().name("contact/create"));
    }
    
    @Test @Ignore
    public void 当URL为contact_save时应该重定向到list页面() throws Exception {
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
              .andExpect(redirectedUrl("show?id=" + (contact.getId()+1)));
    }
    
    @Test @Ignore
    public void 当URL为contact_update时应该访问update页面() throws Exception {
        mockMvc.perform(get("/contact/update")
                       .param("id", String.valueOf(contact.getId())))
               .andExpect(model().attributeExists("contact"))
               .andExpect(view().name("contact/update"));
    }
    
    @Test @Ignore
    public void 当点击update页面中的update按钮时应该重定向到show页面() throws Exception{
        mockMvc.perform(post("/contact/update")
                       .param("id", String.valueOf(contact.getId()))
                       .param("name", String.valueOf(contact.getName()))
                       .param("mobile", String.valueOf(contact.getMobile()))
                       .param("vpmn", String.valueOf(contact.getVpmn()))
                       .param("email", String.valueOf(contact.getEmail()))
                       .param("homeAddress", String.valueOf(contact.getHomeAddress()))
                       .param("officeAddress", String.valueOf(contact.getOfficeAddress()))
                       .param("job", String.valueOf(contact.getJob()))
                       .param("jobLevel", String.valueOf(contact.getJobLevel()))
                       .param("memo", String.valueOf(contact.getMemo())))
               .andExpect(model().attributeExists("id"))
               .andExpect(redirectedUrl("show?id=" + contact.getId()));
    }
    
    @Test @Ignore
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
        
        mockMvc.perform(post("/contact/delete")
                       .param("id", String.valueOf(contact.getId())))
               .andExpect(redirectedUrl("list"));
    }
}