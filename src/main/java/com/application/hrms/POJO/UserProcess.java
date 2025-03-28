package com.application.hrms.POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "userprocess")
public class UserProcess implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERPROCESS_SEQ")
	@SequenceGenerator(name = "USERPROCESS_SEQ", sequenceName = "USERPROCESS_SEQ", allocationSize = 1)
	@Column(name = "id")
	private Integer id;
	
	public void setid(Integer id)
	{
	    this.id= id;
	}

	public Integer getId()
	{
	    return id;
	}
	
	@Column(name = "name")
	private String name;

	public void setName(String name)
	{
	    this.name = name;
	}

	public String getName()
	{
	    return name;
	}

	@Column(name = "email")
	private String email;

	public void setEmail(String email)
	{
	    this.email = email;
	}

	public String getEmail()
	{
	    return email;
	}

	@Column(name = "dob")
	private String dob;

	public void setDob(String dob)
	{
	    this.dob = dob;
	}

	public String getDob()
	{
	    return dob;
	}

	@Column(name = "aadhar")
	private String aadhar;

	public void setAadhar(String aadhar)
	{
	    this.aadhar = aadhar;
	}

	public String getAadhar()
	{
	    return aadhar;
	}

	@Column(name = "pan")
	private String pan;

	public void setPan(String pan)
	{
	    this.pan = pan;
	}

	public String getPan()
	{
	    return pan;
	}

	@Column(name = "contact")
	private String contact;

	public void setContact(String contact)
	{
	    this.contact = contact;
	}

	public String getContact()
	{
	    return contact;
	}

	@Column(name = "experianceinmonths")
	private String experianceinmonths;

	public void setExperianceinmonths(String experianceinmonths)
	{
	    this.experianceinmonths = experianceinmonths;
	}

	public String getExperianceinmonths()
	{
	    return experianceinmonths;
	}

	@Column(name = "expectedsalary")
	private String expectedsalary;

	public void setExpectedsalary(String expectedsalary)
	{
	    this.expectedsalary = expectedsalary;
	}

	public String getExpectedsalary()
	{
	    return expectedsalary;
	}

	@Column(name = "joiningon")
	private String joiningon;

	public void setJoiningon(String joiningon)
	{
	    this.joiningon = joiningon;
	}

	public String getJoiningon()
	{
	    return joiningon;
	}
	
	@ManyToOne
    @JoinColumn(name = "recrutment_id", nullable = false)
    private Recrutment recrutment;
	
	public void setRecrutment(Recrutment user) {
		this.recrutment = user;
	}

	@Column(name = "filepath")
	private String filepath;

	public void setFilepath(String joiningon)
	{
	    this.filepath = joiningon;
	}

	public String getFilepath()
	{
	    return filepath;
	}  
	
	@Column(name = "recruter")
	private String recruter;

	public void setRecruter(String joiningon)
	{
	    this.recruter= joiningon;
	}

	public String getRecruter()
	{
	    return recruter;
	} 
	
	@OneToMany(mappedBy = "userprocess", cascade = CascadeType.ALL)
	private List<Schdules> schdules;
	
	   public List<Schdules> getSchdules() {
	        return schdules;
	    }

	    public void setSchdules(List<Schdules> rel) {
	        this.schdules = rel;
	    }


}
