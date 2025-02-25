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
import javax.persistence.OneToOne;
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
@Table(name = "salarydetails")
public class SalaryDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SALARYDETAILS_SEQ")
	@SequenceGenerator(name = "SALARYDETAILS_SEQ", sequenceName = "SALARYDETAILS_SEQ", allocationSize = 1)
	@Column(name = "id")
	private Integer id;

	@Column(name = "uan")
	private String uan;

	@Column(name = "pf")
	private String pf;

	@Column(name = "bankname")
	private String bankname;
	
	@Column(name = "accountnumber")
	private String accountnumber;

	@Column(name = "ifsccode")
	private String ifsccode;	

	@OneToOne
	@JoinColumn(name = "user_id", unique = true, nullable = false)
	private User user;

	public void setId(Integer inte) {
		this.id = inte;
	}
	
	public void setUser(User inte) {
		this.user = inte;
	}
	
	public void setUan(String name) {
		this.uan= name;
	}

	public void setPf(String string) {
		this.pf = string;
	}
	
	public void setBankname(String name) {
		this.bankname= name;
	}
	
	public void setAccountnumber(String name) {
		this.accountnumber= name;
	}

	public void setIfsccode(String string) {
		this.ifsccode = string;
	}

	public Integer getId() {
		return id;
	}
	
//	public User getUser() {
//		return user;
//	}

	public String getUan() {
		return uan;
	}

	public String getPf() {
		return pf;
	}

	public String getBankname() {
		return bankname;
	}

	public String getAccountnumber() {
		return accountnumber;
	}

	public String getIfsccode() {
		return ifsccode;
	}
}
