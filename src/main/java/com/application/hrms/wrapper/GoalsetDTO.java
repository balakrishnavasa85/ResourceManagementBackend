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
public class GoalsetDTO {
	private Integer category_id;
	private String category_name;
	private Integer criteria_id;
	private String criteria_title;
	private String criteria_name;
	private Integer criteria_maxmarks;
    public GoalsetDTO(Integer cid, String cname,Integer ca_id,  String ca_title,String ca_name,Integer marks) {
        this.category_id = cid;
        this.category_name = cname;        
        this.criteria_id = ca_id;
        this.criteria_title = ca_title;
        this.criteria_name = ca_name;
        this.criteria_maxmarks = marks;
    }
    public GoalsetDTO() {
		// TODO Auto-generated constructor stub
	}
    public Integer getCategoryId() {
        return category_id;
    }

    public void setCategoryId(Integer category_id) {
        this.category_id = category_id;
    }

    public String getCategoryName() {
        return category_name;
    }

    public void setCategoryName(String category_name) {
        this.category_name = category_name;
    }

    public Integer getCriteriaId() {
        return criteria_id;
    }

    public void setCriteriaId(Integer criteria_id) {
        this.criteria_id = criteria_id;
    }

    public String getCriteriaTitle() {
        return criteria_title;
    }

    public void setCriteriaTitle(String criteria_title) {
        this.criteria_title = criteria_title;
    }

    public String getCriteriaName() {
        return criteria_name;
    }

    public void setCriteriaName(String criteria_name) {
        this.criteria_name = criteria_name;
    }

    public Integer getCriteriaMaxmarks() {
        return criteria_maxmarks;
    }

    public void setCriteriaMaxmarks(Integer criteria_maxmarks) {
        this.criteria_maxmarks = criteria_maxmarks;
    }
 
	}