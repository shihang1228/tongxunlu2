package com.baldurtech.contact;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;

import org.springframework.ui.Model;

public class ContactControllerUnitTest {
    @InjectMocks
    ContactController contactController;
    
    @Mock
    Model model;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void 在list方法中当URL为contact_list时返回contact_list() {
        assertEquals("contact/list", contactController.list(model));
        verify(model).addAttribute("contactList", new ArrayList<Contact>());
    }
}