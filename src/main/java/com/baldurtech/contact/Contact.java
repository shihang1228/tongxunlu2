package com.baldurtech.contact;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contact")
@NamedQueries({
    @NamedQuery(name = Contact.FIND_ALL, query = "FROM Contact"),
    @NamedQuery(name = Contact.GET_BY_ID, query = "FROM Contact c WHERE c.id = :id")
})
public class Contact
{
    public static final String FIND_ALL = "Contact.findAll";
    public static final String GET_BY_ID = "Contact.getById";
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotBlank
    private String name;
    
    @NotNull(message = "not a valid mobile format!")
    @Pattern(regexp = "\\b1\\d{10}", message = "not a valid mobile format!")
    private String mobile;
    
    @NotNull(message = "not a valid vpmn format!")
    @Pattern(regexp = "\\d{4,6}", message = "not a valid vpmn format!")
    private String vpmn;
    
    @NotBlank(message = "not a valid email format!")
    @Email(message = "not a valid email format!")
    private String email;
    private String homeAddress;
    private String officeAddress;
    private String memo;
    private String job;
    private Long jobLevel;

    public void setId(Long id)
    {
        this.id = id;
    }
    public Long getId()
    {
        return this.id;
    }
     
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    public String getMobile()
    {
        return this.mobile;
    }

    public void setVpmn(String vpmn)
    {
        this.vpmn = vpmn;
    }
    public String getVpmn()
    {
        return this.vpmn;
    }
     
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getEmail()
    {
        return this.email;
    }
          
    public void setHomeAddress(String homeAddress)
    {
        this.homeAddress = homeAddress;
    }
    public String getHomeAddress()
    {
        return this.homeAddress;
    }
    public void setOfficeAddress(String officeAddress)
    {
        this.officeAddress = officeAddress;
    }
    public String getOfficeAddress()
    {
        return this.officeAddress;
    }
    public void setMemo(String memo)
    {
        this.memo = memo;
    }
    public String getMemo()
    {
        return this.memo;
    }
    public void setJob(String job)
    {
        this.job = job;
    }
    public String getJob()
    {
        return this.job;
    }
    public void setJobLevel(Long jobLevel)
    {
        this.jobLevel = jobLevel;
    }
    public Long getJobLevel()
    {
        return this.jobLevel;
    }
   

}

