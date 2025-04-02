package com.application.hrms.POJO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

	public void setid(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	@Column(name = "name")
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Column(name = "email")
	private String email;

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	@Column(name = "dob")
	private String dob;

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getDob() {
		return dob;
	}

	@Column(name = "aadhar")
	private String aadhar;

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getAadhar() {
		return aadhar;
	}

	@Column(name = "pan")
	private String pan;

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getPan() {
		return pan;
	}

	@Column(name = "contact")
	private String contact;

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContact() {
		return contact;
	}

	@Column(name = "experianceinmonths")
	private String experianceinmonths;

	public void setExperianceinmonths(String experianceinmonths) {
		this.experianceinmonths = experianceinmonths;
	}

	public String getExperianceinmonths() {
		return experianceinmonths;
	}

	@Column(name = "expectedsalary")
	private String expectedsalary;

	public void setExpectedsalary(String expectedsalary) {
		this.expectedsalary = expectedsalary;
	}

	public String getExpectedsalary() {
		return expectedsalary;
	}

	@Column(name = "joiningon")
	private String joiningon;

	public void setJoiningon(String joiningon) {
		this.joiningon = joiningon;
	}

	public String getJoiningon() {
		return joiningon;
	}

	@Column(name = "filepath")
	private String filepath;

	public void setFilepath(String joiningon) {
		this.filepath = joiningon;
	}

	public String getFilepath() {
		return filepath;
	}

	  
    @ManyToOne
    @JoinColumn(name = "recrutmentassigners_id")
    private RecrutmentAssigners recrutmentassigners;
    
    public void setRecrutmentassigners(RecrutmentAssigners ras) {
    	this.recrutmentassigners = ras;
    }

	@OneToMany(mappedBy = "userprocess", cascade = CascadeType.ALL)
	private List<Schdules> schdules;

	public List<Schdules> getSchdules() {
		return schdules;
	}

	public void setSchdules(List<Schdules> rel) {
		this.schdules = rel;
	}
	
	@Column(name="opuserid")
	private Integer opuserid;
	public Integer getOpuserid() {
		return opuserid;
	}
	public void setOpuserid(Integer op) {
		this.opuserid = op;
	}
	
	@Column(name="opusername")
	private String opusername;
	public String getOpuserName() {
		return opusername;
	}
	public void setOpuserName(String op) {
		this.opusername = op;
	}
	
	@Column(name="opusercomment")
	private String opusercomment;
	public String getOpuserComment() {
		return opusercomment;
	}
	public void setOpuserComment(String op) {
		this.opusername = op;
	}
	
	@Column(name="preferedjoingdate")
	private String preferedjoingdate;
	public String getpreferedjoingdate() {
		return preferedjoingdate;
	}
	public void setpreferedjoingdate(String op) {
		this.preferedjoingdate = op;
	}
	
	@Column(name="userconformation")
	private String userconformation;
	public String getuserconformation() {
		return userconformation;
	}
	public void setuserconformation(String op) {
		this.userconformation = op;
	}
	
	@Column(name="operationdate")
	private String operationdate;
	public String getoperationdate() {
		return operationdate;
	}
	public void setoperationdate(String op) {
		this.operationdate = op;
	}

	@Column(name="userlink")
	private String userlink;
	public String getUserlink() {
		return userlink;
	}
	public void setUserlink(String op) {
		this.userlink = op;
	}
	@Column(name="offergenerateddate")
	private String offergenerateddate;
	public String getOffergenerateddate() {
		return offergenerateddate;
	}
	public void setOffergenerateddate(String op) {
		this.offergenerateddate= op;
	}
	@Column(name="address")
	private String address;
	public String getAddress() {
		return address;
	}
	public void setAddress(String op) {
		this.address= op;
	}
	@Column(name="password")
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String op) {
		this.password= op;
	}
	@Column(name="role")
	private String role= "processuser";
	public String getRole() {
		return role;
	}
	public void setRole(String op) {
		this.role= op;
	}

}
