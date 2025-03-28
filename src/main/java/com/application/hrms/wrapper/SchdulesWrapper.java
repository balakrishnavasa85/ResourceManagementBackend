

package com.application.hrms.wrapper;

import com.application.hrms.POJO.Department;
import com.application.hrms.POJO.UserProcess;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
public class SchdulesWrapper {
	private Integer id;

	private String interviewtime;

	private Integer interviewerid;

	private Integer recrutmentid;

	private UserProcess userprocess;

	private String interviewername;

	private Integer recuterid;

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

	public UserProcess getUserProcess() {
	return userprocess;
	}

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
