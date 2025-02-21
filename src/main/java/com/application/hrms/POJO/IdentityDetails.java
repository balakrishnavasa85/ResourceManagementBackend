package com.application.hrms.POJO;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name = "identity")
public class IdentityDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IDENTITYDETAILS_SEQ")
	@SequenceGenerator(name = "IDENTITYDETAILS_SEQ", sequenceName = "IDENTITYDETAILS_SEQ", allocationSize = 1)
	@Column(name = "id")
	private Integer id;

	@Column(name = "aadhar")
	private String aadhar;

	@Column(name = "pan")
	private String pan;
	
	@Column(name = "passport")
	private String passport;

	@Column(name = "expiry")
	private String expiry;	

	@Column(name = "user")
	private Integer user;

	public void setId(Integer inte) {
		this.id = inte;
	}
	
	public void setUser(Integer inte) {
		this.user = inte;
	}
	
	public void setPan(String name) {
		this.pan= name;
	}

	public void setAadhar(String string) {
		this.aadhar = string;
	}
	
	public void setPassport(String name) {
		this.passport= name;
	}

	public void setExpiry(String string) {
		this.expiry = string;
	}

	public Integer getId() {
		return id;
	}
	
	public Integer getUser() {
		return user;
	}

	public String getPan() {
		return pan;
	}

	public String getAadhar() {
		return aadhar;
	}

	public String getPassport() {
		return passport;
	}

	public String getExpiry() {
		return expiry;
	}
}
