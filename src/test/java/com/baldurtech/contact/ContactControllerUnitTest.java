package com.baldurtech.contact;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import org.springframework.ui.Model;


public class ContactControllerUnitTest {
    @Mock
    ContactService contactService;
    
    @Mock
    Model model;
    
    @InjectMocks
    ContactController contactController;
    
    @Before
    public void setup() {
        initMocks();
    }
    
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void 在contactController中的list方法中将会调用ContactService中的list方法() {
        contactController.list(model);
        verify(contactService).list();
    }
}