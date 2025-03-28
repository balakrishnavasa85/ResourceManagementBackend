package com.application.hrms.wrapper;

import com.application.hrms.POJO.Department;
import com.application.hrms.POJO.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserProcessWrapper {
	private Integer reqid;
	private String recrutmentId;
	private String title;
	private String description;
	private String budget;
	private Integer noofpositions;
	private Integer noofpositionsclosed;
	private Department department;
	private User assigner;
	private Integer user;
	private String name;
	private String email;
	private String contact;
	private String aadhar;
	private String dob;
	private String expectedSalary;
	private String experienceInMonths;
	private String joiningOn;
	private String pan;
	private String filepath;

	public Integer getReqid() {
		return reqid;
	}

	public void setReqid(Integer reqid) {
		this.reqid = reqid;
	}

	public String getRecrutmentId() {
		return recrutmentId;
	}

	public void setRecrutmentId(String recrutmentId) {
		this.recrutmentId = recrutmentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public Integer getNoofpositions() {
		return noofpositions;
	}

	public void setNoofpositions(Integer noofpositions) {
		this.noofpositions = noofpositions;
	}

	public Integer getNoofpositionsclosed() {
		return noofpositionsclosed;
	}

	public void setNoofpositionsclosed(Integer noofpositionsclosed) {
		this.noofpositionsclosed = noofpositionsclosed;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public User getAssigner() {
		return assigner;
	}

	public void setAssigner(User assigner) {
		this.assigner = assigner;
	}

	public Integer getUserId() {
		return user;
	}

	public void setUserId(Integer userId) {
		this.user = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getExpectedSalary() {
		return expectedSalary;
	}

	public void setExpectedSalary(String expectedSalary) {
		this.expectedSalary = expectedSalary;
	}

	public String getExperienceInMonths() {
		return experienceInMonths;
	}

	public void setExperienceInMonths(String experienceInMonths) {
		this.experienceInMonths = experienceInMonths;
	}

	public String getJoiningOn() {
		return joiningOn;
	}

	public void setJoiningOn(String joiningOn) {
		this.joiningOn = joiningOn;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public UserProcessWrapper() {
	}

	public UserProcessWrapper(Integer reqid, String recrutmentId, String title, String description, String budget,
			Integer noofpositions, Integer noofpositionsclosed, Department department, User assigner, Integer user,
			String name, String email, String contact, String aadhar, String dob, String expectedSalary,
			String experienceInMonths, String joiningOn, String pan, String filepath) {
		this.reqid = reqid;
		this.recrutmentId = recrutmentId;
		this.title = title;
		this.description = description;
		this.budget = budget;
		this.noofpositions = noofpositions;
		this.noofpositionsclosed = noofpositionsclosed;
		this.department = department;
		this.assigner = assigner;
		this.user = user;
		this.name = name;
		this.email = email;
		this.contact = contact;
		this.aadhar = aadhar;
		this.dob = dob;
		this.expectedSalary = expectedSalary;
		this.experienceInMonths = experienceInMonths;
		this.joiningOn = joiningOn;
		this.pan = pan;
		this.filepath = filepath;
	}
}
