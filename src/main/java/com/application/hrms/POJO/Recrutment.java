package com.application.hrms.POJO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
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
@Table(name = "recrutment")
public class Recrutment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECRUTMENT_SEQ")
	@SequenceGenerator(name = "RECRUTMENT_SEQ", sequenceName = "RECRUTMENT_SEQ", allocationSize = 1)
	@Column(name = "id")
	private Integer id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "budget")
	private String budget;

	@Column(name = "reqid")
	private String reqid;

	@Column(name = "status")
	private String status;

	@Column(name = "noofpositions")
	private Integer noofpositions;

	@Column(name = "noofpositionsclosed")
	private Integer noofpositionsclosed;

	@ManyToOne(fetch = FetchType.EAGER) // EAGER fetch so department is loaded with user
	@JoinColumn(name = "department_id")
	private Department department;
	
	@ManyToOne(fetch = FetchType.EAGER) // EAGER fetch so department is loaded with user
	@JoinColumn(name = "designation_id")
	private Designation designation;

	public void setTitle(String name) {
		this.title = name;
	}

	public String getTitle() {
		return title;
	}

	public void setDescription(String name) {
		this.description = name;
	}

	public String getDescription() {
		return description;
	}

	public void setBudget(String name) {
		this.budget = name;
	}

	public String getBudget() {
		return budget;
	}

	public void setReqid(String name) {
		this.reqid = name;
	}

	public void setStatus(String name) {
		this.status = name;
	}

	public String getStatus() {
		return status;
	}

	public void setNoofpositions(Integer name) {
		this.noofpositions = name;
	}

	public Integer getNoofpositions() {
		return noofpositions;
	}

	public void setNoofpositionsclosed(Integer name) {
		this.noofpositionsclosed = name;
	}

	public Integer getNoofpositionsclosed() {
		return noofpositionsclosed;
	}

	public String getReqid() {
		return reqid;
	}

	public void setId(Integer inte) {
		this.id = inte;
	}

	public Integer getId() {
		return id;
	}

	public void setDepartment(Department dept) {
		this.department = dept;
	}

	public Department getDepartment() {
		return department;
	}
	
	public void setDesignation(Designation dept) {
		this.designation = dept;
	}

	public Designation getDesignation() {
		return designation;
	}

	@PrePersist
	private void generateReqId() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String timestamp = LocalDateTime.now().format(formatter);
		this.reqid = "REQ" + timestamp;
	}

	@OneToMany(mappedBy = "recrutment", cascade = CascadeType.ALL)
	private List<RecrutmentAssigners> assigners;

	public List<RecrutmentAssigners> getAssigners() {
		return assigners;
	}

	public void setAssigners(List<RecrutmentAssigners> empEducation) {
		this.assigners = empEducation;
	}
//	
//	@OneToMany(mappedBy = "recrutment", cascade = CascadeType.ALL)
//	private List<UserProcess> usersProcess;
//
//	public List<UserProcess> getUsersProcess() {
//		return usersProcess;
//	}
//
//	public void setUsersProcess(List<UserProcess> empEducation) {
//		this.usersProcess = empEducation;
//	}
	
	 

}
