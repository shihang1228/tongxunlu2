package com.baldurtech.contact;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.assertEquals;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;


public class ContactValidationTest {
    private Contact contact;
    private Set<ConstraintViolation<Contact>> constraintViolations;
    private static Validator validator;
    
    @BeforeClass
    public static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @Before
    public void createValidContact() {
        contact = new Contact();
        contact.setName("Xiao Bai");
        contact.setMobile("15235432994");
        contact.setEmail("123@qq.com");
        contact.setVpmn("652994");
        contact.setHomeAddress("shanxi");
        contact.setOfficeAddress("beizhang");
        contact.setMemo("memo");
        contact.setJob("HR");
        contact.setJobLevel(1L);
    }
    
    public void assertConstraintViolation(String errorMessage) {
        constraintViolations = validator.validate(contact);
        
        assertEquals(1, constraintViolations.size());
        assertEquals(errorMessage, constraintViolations.iterator().next().getMessage());        
    }
    
    @Test
    public void name_cannot_be_null() {
        contact.setName(null);
        assertConstraintViolation("不能为空"); 
    }
   
    @Test
    public void name_cannot_be_blank() {
        contact.setName("         ");
        assertConstraintViolation("不能为空");      
    }
   
    @Test
    public void mobile_cannot_be_null() {
        contact.setMobile(null);
        assertConstraintViolation("not a valid mobile format!");      
    }
   
    @Test 
    public void mobile_cannot_be_blank() {
        contact.setMobile("        ");
        assertConstraintViolation("not a valid mobile format!");      
    }
   
    @Test
    public void mobile_cannot_be_too_long() {
        contact.setMobile("182123456789");
        assertConstraintViolation("not a valid mobile format!");      
    }
   
    @Test 
    public void mobile_cannot_be_too_short() {
        contact.setMobile("1821234567");
        assertConstraintViolation("not a valid mobile format!");      
    }
   
    @Test 
    public void mobile_can_be_digits() {
        contact.setMobile("abcdefjhijk");
        assertConstraintViolation("not a valid mobile format!");      
    }
   
    @Test 
    public void mobile_can_be_start_with_one() {
        contact.setMobile("22222222222");
        assertConstraintViolation("not a valid mobile format!");      
    }
}