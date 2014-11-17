package com.baldurtech.contact;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.Validation;
import javax.validation.ConstraintViolation;

public class ContactValidationUnitTest {
    private static Validator validator;
    private Set<ConstraintViolation<Contact>> constraintViolations;
    private Contact contact;
  
    @BeforeClass
    public static void setup() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    
    @Test
    public void name_cannot_be_null() {
        contact = new Contact();
        
        contact.setName(null);
        contact.setMobile("18222222222");
        contact.setVpmn("62222");
        contact.setEmail("xiaobai@qq.com");
        contact.setHomeAddress("TaiYuan");
        contact.setOfficeAddress("BeiZhang");
        contact.setJob("HR");
        contact.setJobLevel(16L);
        contact.setMemo("memo");
        
        assertConstraintValidation("不能为空");
    }
    public void assertConstraintValidation(String errorMessage) {
        constraintViolations = validator.validate(contact); 
        
        assertEquals(1, constraintViolations.size());
        assertEquals(errorMessage, constraintViolations.iterator().next().getMessage());
    }
    
    @Test
    public void name_cannot_be_blank(){
        Contact contact = new Contact();
        contact.setName("         ");
        contact.setMobile("18222222222");
        contact.setVpmn("62222");
        contact.setEmail("xiaobai@qq.com");
        contact.setHomeAddress("TaiYuan");
        contact.setOfficeAddress("BeiZhang");
        contact.setJob("HR");
        contact.setJobLevel(16L);
        contact.setMemo("memo");
        
        Set<ConstraintViolation<Contact>> constraintViolations = validator.validate(contact);
        
        assertEquals(1, constraintViolations.size());
        assertEquals("不能为空", constraintViolations.iterator().next().getMessage());
    }
}