package com.application.hrms.POJO;

import java.io.Serializable;
import java.util.ArrayList;
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
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "appraisalcriteria")
public class AppraisalCriteria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AppraisalCategories_SEQ")
	@SequenceGenerator(name = "AppraisalCategories_SEQ", sequenceName = "AppraisalCategories_SEQ", allocationSize = 1)
	@Column(name = "id")
	private Integer id;

	@Column(name = "title")
	private String title;
	
	@Column(name = "name")
	private String name;

	@Column(name = "maxmarks")
	private String maxmarks;

	@Column(name = "status")
	private String status = "y";

	@ManyToOne
	@JoinColumn(name = "appraisalcategories_id", referencedColumnName = "category_id")
	private AppraisalCategories category;

//	@OneToMany(mappedBy = "criteria", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<AppraisalScores> scores;

	public void setName(String name) {
		this.name = name;
	}

	public void setTitle(String name) {
		this.title = name;
	}
	
	public void setMaxmarks(String string) {
		this.maxmarks = string;
	}

	public void setId(Integer inte) {
		this.id = inte;
	}

	public String getName() {
		return name;
	}
	
	public String getTitle() {
		return title;
	}

	public String getMaxmarks() {
		return maxmarks;
	}

	public Integer getId() {
		return id;
	}
	public AppraisalCategories getCategory()
	{
		return category;
	}

	public void setCategory(AppraisalCategories ac) {
		this.category = ac;
	}

//	public void setScores(List<AppraisalScores> lis) {
//		this.scores = lis;
//	}

//	public List<AppraisalScores> getScores() {
//		return scores;
//	}
	
	public void setStatus(String st) {
		this.status = st;
	} 

	public String getStatus() {
		return status;
	}
}
