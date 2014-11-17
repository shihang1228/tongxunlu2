package com.baldurtech.contact;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;
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
    
    @Before
    public void createValidateContact() {
        contact = new Contact();
        
        contact.setName("Xiao Bai");
        contact.setMobile("18222222222");
        contact.setVpmn("62222");
        contact.setEmail("xiaobai@qq.com");
        contact.setHomeAddress("TaiYuan");
        contact.setOfficeAddress("BeiZhang");
        contact.setJob("HR");
        contact.setJobLevel(16L);
        contact.setMemo("memo");
    }
    
    public void assertConstraintValidation(String errorMessage) {
        constraintViolations = validator.validate(contact); 
        
        assertEquals(1, constraintViolations.size());
        assertEquals(errorMessage, constraintViolations.iterator().next().getMessage());
    }
    
    @Test
    public void name_cannot_be_null() {
        contact.setName(null);
        assertConstraintValidation("不能为空");
    }
   
    @Test
    public void name_cannot_be_blank(){
        contact.setName("    ");
        assertConstraintValidation("不能为空");
    }
    
    @Test
    public void mobile_cannot_be_null() {
        contact.setMobile(null);
        assertConstraintValidation("Not a valid mobile format!");
    }
    
    @Test
    public void mobile_cannnot_be_blank() {
        contact.setMobile("    ");
        assertConstraintValidation("Not a valid mobile format!");
    }
    
    @Test
    public void mobile_cannot_too_long() {
        contact.setMobile("182123456789");
        assertConstraintValidation("Not a valid mobile format!");
    }
    
    @Test
    public void mobile_cannot_too_short() {
        contact.setMobile("182");
        assertConstraintValidation("Not a valid mobile format!");
    }
}