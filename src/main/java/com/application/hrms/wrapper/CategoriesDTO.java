package com.application.hrms.wrapper;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;

import com.application.hrms.POJO.AppraisalCriteria;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class CategoriesDTO {
	private Integer category_id;
	private String name;
	private String maxmarks;
	private String status;
	private List<AppraisalCriteria> criteriaList;

    public CategoriesDTO(Integer id, String name, String status,String marks,List<AppraisalCriteria> list) {
        this.category_id = id;
        this.name = name;
        this.status = status;
        this.maxmarks = marks;
        this.criteriaList = list;
    }
    public CategoriesDTO() {
		// TODO Auto-generated constructor stub
	}
	public void setName(String name) {
		this.name = name;
	}

	public void setMaxmarks(String string) {
		this.maxmarks = string;
	}

	public void setId(Integer inte) {
		this.category_id = inte;
	}

	public void setStatus(String st) {
		this.status = st;
	}

	public String getName() {
		return name;
	}

	public String getMaxmarks() {
		return maxmarks;
	}

	public Integer getId() {
		return category_id;
	}

	public String getStatus() {
		return status;
	}

	public void setCriteriaList(List<AppraisalCriteria> lac) {
		this.criteriaList =  lac;
	}
	public List<AppraisalCriteria> getCriteriaList() {
		return criteriaList;
	}
	}