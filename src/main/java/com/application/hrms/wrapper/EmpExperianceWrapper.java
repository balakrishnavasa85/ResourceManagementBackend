package com.application.hrms.wrapper;

import com.application.hrms.POJO.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmpExperianceWrapper {
	private Integer id;
	private String companyname;
	private String joiningdate;
	private String releavedate;
	private String experiance;
	private Integer user;

	public EmpExperianceWrapper(Integer id, String companyname, String joiningdate, String releavedate,
			String experiance, Integer user) {
		this.id = id;
		this.companyname = companyname;
		this.joiningdate = joiningdate;
		this.releavedate = releavedate;
		this.experiance = experiance;
		this.user = user;
	}

	public void setCompanyName(String name) {
		this.companyname = name;
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

	public void setExperiance(String str) {
		this.experiance = str;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public String getCompanyName() {
		return companyname;
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

	public String getExperiance() {
		return experiance;
	}

	public Integer getUser() {
		return user;
	}
}