package com.baldurtech.contact;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="contact")
@NamedQueries({
    @NamedQuery(name = Contact.FIND_ALL, query = "from Contact"),
    @NamedQuery(name = Contact.GET_BY_ID, query = "from Contact c where c.id = :id")
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
    
    @NotNull
    @Pattern(regexp="\\b1\\d{10}", message="not a valid mobile format")
    private String mobile;
    
    @NotNull
    @Pattern(regexp="\\d{4,6}", message="elements must be digits and must between 4 to 6")
    private String vpmn;
    
    @NotBlank(message = "not a valid email format")
    @Email(message = "not a valid email format")
    private String email;
    
    @NotBlank
    private String homeAddress;
    
    @NotBlank
    private String officeAddress;
    private String memo;
    
    @NotBlank
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

