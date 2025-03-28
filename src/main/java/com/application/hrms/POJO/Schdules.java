package com.application.hrms.POJO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@AllArgsConstructor
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "schdules")
public class Schdules implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCHDULES_SEQ")
	@SequenceGenerator(name = "SCHDULES_SEQ", sequenceName = "SCHDULES_SEQ", allocationSize = 1)
	@Column(name = "id")
	private Integer id;

	@Column(name = "interviewtime")
	private String interviewtime;

	@Column(name = "interviewerid")
	private Integer interviewerid;

	@Column(name = "recrutmentid")
	private Integer recrutmentid;
	
	@ManyToOne
	@JoinColumn(name = "userprocess_id", referencedColumnName = "id")
	private UserProcess userprocess;

	@Column(name = "interviewername")
	private String interviewername;

	@Column(name = "recuterid")
	private Integer recuterid;

	@Column(name = "status")
	private String status;

	public void setId(Integer inte) {
		this.id = inte;
	}

	public Integer getId() {
		return id;
	}

	public void setInterviewtime(String str) {
		this.interviewtime = str;
	}

	public String getInterviewtime() {
		return interviewtime;
	}

	public void setInterviewerid(Integer user) {
		this.interviewerid = user;
	}

	public Integer getInterviewerId() {
		return interviewerid;
	}

	public void setRecrutmentid(Integer week) {
		this.recrutmentid = week;
	}

	public Integer getRecrutmentid() {
		return recrutmentid;
	}
	
//	public UserProcess getUserProcess() {
//		return userprocess;
//	}

	public void setUserprocess(UserProcess upi) {
		this.userprocess = upi;
	}

	public void setRecurterid(Integer rpi) {
		this.recuterid = rpi;
	}

	public Integer getRecruterid() {
		return recuterid;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setInterviewername(String name) {
		this.interviewername = name;
	}

	public String getInterviewername() {
		return interviewername;
	}

}
