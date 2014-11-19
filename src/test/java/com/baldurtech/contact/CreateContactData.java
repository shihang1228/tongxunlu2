package com.baldurtech.contact;

public class CreateContactData {
    public static Contact createContact() {
        Contact contact = new Contact();
        contact.setId(9L);
        contact.setName("Shihang");
        contact.setMobile("15235432994");
        contact.setVpmn("652994");
        contact.setHomeAddress("taiyuan");
        contact.setOfficeAddress("taiyuan");
        contact.setMemo("memo");
        contact.setJob("HR");
        contact.setJobLevel(4L);
        
        return contact;
    }
}