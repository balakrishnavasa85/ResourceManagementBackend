package com.application.hrms.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeductionGroupWrapper {
	private Integer id;
	private String name;
	private String value;
	private String status;

	public DeductionGroupWrapper(Integer id, String name, String value, String status) {
		this.id = id;
		this.name = name;
		this.value = value;
		this.status = status;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String string) {
		this.value = string;
	}

	public void setId(Integer inte) {
		this.id = inte;
	}

	public void setStatus(String string) {
		this.status = string;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public Integer getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}
}