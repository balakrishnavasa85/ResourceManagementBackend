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
@Table(name = "appraisalscore")
public class AppraisalScores implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Appraisalscore_SEQ")
	@SequenceGenerator(name = "Appraisalscore_SEQ", sequenceName = "Appraisalscore_SEQ", allocationSize = 1)
	@Column(name = "scoreid")
	private Integer scoreid;

	@Column(name = "score")
	private Integer score;

	@Column(name = "comments")
	private String comments;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "appraisal_id", nullable = false)
	private Appraisals appraisals;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "criteria_id", nullable = false)
	private AppraisalCriteria criteria;

	public void setId(Integer inte) {
		this.scoreid = inte;
	}

	public Integer getId() {
		return scoreid;
	}

	public void setScore(Integer inte) {
		this.score = inte;
	}

	public Integer getScore() {
		return score;
	}

	public void setComments(String str) {
		this.comments = str;
	}

	public String getComments() {
		return comments;
	}

	public void setAppraisal(Appraisals upi) {
		this.appraisals = upi;
	}

	public void setAppraisalCriteria(AppraisalCriteria upi) {
		this.criteria = upi;
	}

}
