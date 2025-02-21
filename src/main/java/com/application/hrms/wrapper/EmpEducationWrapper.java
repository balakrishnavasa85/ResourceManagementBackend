package com.application.hrms.wrapper;

import com.application.hrms.POJO.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmpEducationWrapper {
	private Integer id;
	private String study;
	private String joiningdate;
	private String releavedate;
	private String duration;
	private Integer user;

	public EmpEducationWrapper(Integer id, String study, String startingdate, String completeddate, String duration,
			Integer user) {
		this.id = id;
		this.study = study;
		this.joiningdate = joiningdate;
		this.releavedate = releavedate;
		this.duration = duration;
		this.user = user;
	}

	public void setStudy(String name) {
		this.study = name;
	}

	public void setJoiningDate(String string) {
		this.joiningdate = string;
	}

	public void setReleaveDate(String string) {
		this.releavedate = string;
	}

	public void setId(Integer inte) {
		this.id = inte;
	}

	public void setDuration(String str) {
		this.duration = str;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public String getStudy() {
		return study;
	}

	public String getJoiningDate() {
		return joiningdate;
	}

	public String getReleaveDate() {
		return releavedate;
	}

	public Integer getId() {
		return id;
	}

	public String getDuration() {
		return duration;
	}

	public Integer getUser() {
		return user;
	}
}