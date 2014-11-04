package com.baldurtech.contact;

import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.assertEquals;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;


public class ContactValidationTest {
    private static Validator validator;
    
    @BeforeClass
    public static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @Test
    public void name_cannot_be_null() {
        Contact contact = new Contact();
        contact.setName(null);
        contact.setMobile("15235432994");
        contact.setEmail("123@qq.com");
        contact.setVpmn("652994");
        contact.setHomeAddress("shanxi");
        contact.setOfficeAddress("beizhang");
        contact.setMemo("memo");
        contact.setJob("HR");
        contact.setJobLevel(1L);
        
        Set<ConstraintViolation<Contact>> constraintViolations = validator.validate(contact);
        
        assertEquals(1, constraintViolations.size());
        assertEquals("不能为空", constraintViolations.iterator().next().getMessage());        
    }
}