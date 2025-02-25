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
@AllArgsConstructor
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "leaverequest")
public class LeaveRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LEAVEREQUEST_SEQ")
	@SequenceGenerator(name = "LEAVEREQUEST_SEQ", sequenceName = "LEAVEREQUEST_SEQ", allocationSize = 1)
	@Column(name = "id")
	private Integer id;

	@Column(name = "requestfromdate")
	private String requestfromdate;


	@Column(name = "requesttodate")
	private String requesttodate;
	
	@Column(name = "noofdays")
	private Integer noofdays;
	
	
	@Column(name = "reason")
	private String reason;
	
	@Column(name = "comment")
	private String comment; 
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "approvedby")
	private Integer approvedby;

	@Column(name = "status")
	private String status;

	@Column(name = "approver")
	private String approver;

	@ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

	public void setRequestFromDate(String name) {
		this.requestfromdate = name;
	}
	
	public void setRequestToDate(String name) {
		this.requesttodate = name;
	}

	public void setReason(String string) {
		this.reason = string;
	}
	public void setComment(String string) {
		this.comment = string;
	}

	public void setId(Integer inte) {
		this.id = inte;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setApprovedBy(Integer approved) {
		this.approvedby = approved;
	}

	public void setStatus(String user) {
		this.status = user;
	}
	
	public void setApprover(String approver) {
		this.approver = approver;
	}
	
	public void setNoofdays(Integer num) {
		this.noofdays = num;
	}	

	public String getRequestFromDate() {
		return requestfromdate;
	}

	public String getRequestToDate() {
		return requesttodate;
	}

	public String getReason() {
		return reason;
	}

	public String getComment() {
		return comment;
	}
	public Integer getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public String getUsername() {
		return username;
	}
	
	public Integer getApprovedBy() {
		return approvedby;
	}

	public String getStatus() {
		return status;
	}

	public String getApprover() {
		return approver;
	}
	
	public Integer getNoofdays()
	{
		return noofdays;
	}

	public Integer countApprovedLeaves(Integer user2) {
		// TODO Auto-generated method stub
		return null;
	}

}
