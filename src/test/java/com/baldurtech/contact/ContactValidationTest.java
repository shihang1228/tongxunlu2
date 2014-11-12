package com.baldurtech.contact;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;
import javax.validation.Validation;

import static org.junit.Assert.assertEquals;

public class ContactValidationTest {
    private static Validator validator;
    private Contact contact;
    private Set<ConstraintViolation<Contact>> constraintViolations;
    
    @BeforeClass
    public static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();      
    }
    
      @Before
    public void createValidContact() {
        contact = new Contact();
        contact.setName("Shihang");
        contact.setMobile("18235100872");
        contact.setVpmn("62222");
        contact.setEmail("a@a.com");
        contact.setHomeAddress("TaiYuan");
        contact.setOfficeAddress("BeiZhang");
        contact.setMemo("memo");
        contact.setJob("HR");
        contact.setJobLevel(9L);
    }
    
    private void assertConstraintViolations(String errorMessage) {
        constraintViolations = validator.validate(contact);
        assertEquals( 1, constraintViolations.size());
        assertEquals(errorMessage, constraintViolations.iterator().next().getMessage());
    }
    
    @Test
    public void name_cannot_be_null() {     
        contact.setName(null);
        assertConstraintViolations("不能为空");       
    }
    
    @Test
    public void name_cannot_be_blank() {
        contact.setName("       ");
        assertConstraintViolations("不能为空");       
    }
    
    @Test
    public void Mobile_cannot_be_null() {
        contact.setMobile(null);
        assertConstraintViolations("不能为null");    
    }
    
    @Test
    public void Mobile_cannot_be_blank() {
        contact.setMobile("     ");
        assertConstraintViolations("not a valid mobile format");    
    }
    
    @Test
    public void Mobile_can_be_digits() {
        contact.setMobile("abcdefghijk");
        assertConstraintViolations("not a valid mobile format");    
    }
    
    @Test
    public void mobile_cannot_be_too_short() {
        contact.setMobile("123");
        assertConstraintViolations("not a valid mobile format");    
    }
    
    @Test
    public void mobile_cannot_be_too_long() {
       contact.setMobile("182351008722");
        assertConstraintViolations("not a valid mobile format");    
    }
    
    @Test
    public void mobile_can_be_start_with_one() {
        contact.setMobile("28235100872");
        assertConstraintViolations("not a valid mobile format");    
    }
    
    @Test
    public void vpmn_cannot_be_null() {
        contact.setVpmn(null);
        assertConstraintViolations("不能为null");           
    }
    
    @Test
    public void vpmn_cannot_be_too_short() {
        contact.setVpmn("5");
        assertConstraintViolations("elements must be digits and must between 4 to 6");  
    }
    
    @Test
    public void vpmn_cannot_be_too_long() {
        contact.setVpmn("1234567899");
        assertConstraintViolations("elements must be digits and must between 4 to 6");  
    }
    
    @Test
    public void vpmn_can_be_digits() {
        contact.setVpmn("abcd");
        assertConstraintViolations("elements must be digits and must between 4 to 6");  
    }
    
    @Test
    public void email_cannot_be_null() {
        contact.setEmail(null);
        assertConstraintViolations("not a valid email format");  
    }
    
    @Test
    public void email_cannot_be_blank() {
        contact.setEmail("    ");
        assertConstraintViolations("not a valid email format");  
    }
    
    @Test
    public void email_can_be_valid_format() {
        contact.setEmail("22dd");
        assertConstraintViolations("not a valid email format");  
    }
    
    @Test
    public void homeAddress_cannot_be_null() {
        contact.setHomeAddress(null);
        assertConstraintViolations("不能为空"); 
    }
    
    @Test
    public void homeAddress_cannot_be_blank() {
        contact.setHomeAddress("    ");
        assertConstraintViolations("不能为空");  
    }
    
    @Test
    public void officeAddress_cannot_be_null() {
        contact.setOfficeAddress(null);
        assertConstraintViolations("不能为空");  
    }
    
    @Test
    public void officeAddress_cannot_be_blank() {
        contact.setOfficeAddress("     ");
        assertConstraintViolations("不能为空");  
    }
        
    @Test
    public void job_cannot_be_null() {
        contact.setJob(null);
        assertConstraintViolations("不能为空");  
    }
        
    @Test
    public void job_cannot_be_blank() {
        contact.setJob("            ");
        assertConstraintViolations("不能为空");  
    }
        
    @Test
    public void jobLevel_cannot_be_null() {
        contact.setJobLevel(null);
        assertConstraintViolations("不能为null");  
    }
}