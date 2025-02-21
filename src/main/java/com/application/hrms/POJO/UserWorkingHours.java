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
@Table(name = "userworkinghours")
public class UserWorkingHours implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERWORKINGHOURS_SEQ")
	@SequenceGenerator(name = "USERWORKINGHOURS_SEQ", sequenceName = "USERWORKINGHOURS_SEQ", allocationSize = 1)
	@Column(name = "id")
	private Integer id;

	@Column(name = "user")
	private Integer user;

	@Column(name = "workingdate")
	private String workingdate;

	@Column(name = "workinghours")
	private String workinghours;

	@Column(name = "totalworkinghours")
	private String totalworkinghours;

	@Column(name = "mode")
	private String mode;

	public void setId(Integer inte) {
		this.id = inte;
	}

	public void setUser(Integer str) {
		this.user = str;
	}

	public void setWorkingdate(String user) {
		this.workingdate = user;
	}

	public void setWorkinghours(String hours) {
		this.workinghours = hours;
	}

	public void setTotalworkinghours(String twh) {
		this.totalworkinghours = twh;
	}

	public void setMode(String twh) {
		this.mode = twh;
	}

	public Integer getId() {
		return id;
	}

	public Integer getUser() {
		return user;
	}

	public String getWorkingdate() {
		return workingdate;
	}

	public String getWorkinghours() {
		return workinghours;
	}

	public String getTotalworkinghours() {
		return totalworkinghours;
	}

	public String getMode() {
		return mode;
	}

}
