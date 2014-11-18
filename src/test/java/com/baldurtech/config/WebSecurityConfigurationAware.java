package com.baldurtech.config;

import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.springframework.security.web.FilterChainProxy;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

public abstract class WebSecurityConfigurationAware extends WebAppConfigurationAware {

    @Inject
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void before() {
        this.mockMvc = webAppContextSetup(this.wac)
                .addFilters(this.springSecurityFilterChain).build();
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
    
    protected MockHttpSession adminSession() {
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
        Authentication userAuthentication = 
            new UsernamePasswordAuthenticationToken("admin","admin", authorities);
            
        SecurityContext securityContext = org.mockito.Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(userAuthentication);
        
        MockHttpSession userSession = new MockHttpSession();
        userSession.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, 
                securityContext);
                
        return userSession;
    }
    
    protected ResultActions userPerform(MockHttpServletRequestBuilder request)
        throws Exception {
        return mockMvc.perform(request.session(userSession()));
    }
    
    protected ResultActions adminPerform(MockHttpServletRequestBuilder request)
        throws Exception {
        return mockMvc.perform(request.session(adminSession()));
    }
}
