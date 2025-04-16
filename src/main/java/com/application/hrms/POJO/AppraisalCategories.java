package com.application.hrms.POJO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "appraisalcategories")
public class AppraisalCategories implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AppraisalCategories_SEQ")
	@SequenceGenerator(name = "AppraisalCategories_SEQ", sequenceName = "AppraisalCategories_SEQ", allocationSize = 1)
	@Column(name = "category_id")
	private Integer category_id;

	@Column(name = "name")
	private String name;

	@Column(name = "maxmarks")
	private String maxmarks;

	@Column(name = "status")
	private String status = "y";

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<AppraisalCriteria> criteriaList;

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
//	public List<AppraisalCriteria> getCriteriaList() {
//		return criteriaList;
//	}

}
