package com.baldurtech.contact;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ContactControllerUnitTest {
    ContactController contactController = new ContactController();
    @Test
    public void 在list方法中当URL为contact_list时返回contact_list() {
        assertEquals("contact/list", contactController.list());
    }
}