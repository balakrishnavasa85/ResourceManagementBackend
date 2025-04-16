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
@Table(name = "appraisals")
public class Appraisals implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Appraisals_SEQ")
	@SequenceGenerator(name = "Appraisals_SEQ", sequenceName = "Appraisals_SEQ", allocationSize = 1)
	@Column(name = "appraisalid")
	private Integer appraisalid;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "cycle_id", nullable = false)
	private AppraisalCycles appraisalcycle;

	@Column(name = "reviewerid")
	private Integer reviewerid;

	@Column(name = "totalscore")
	private Integer totalscore;

	@Column(name = "createdat")
	private String createdat;

	@OneToMany(mappedBy = "appraisals", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AppraisalScores> scores;

	public void setId(Integer inte) {
		this.appraisalid = inte;
	}

	public Integer getId() {
		return appraisalid;
	}

	public void setUser(User upi) {
		this.user = upi;
	}

	public void setAppraisalCycle(AppraisalCycles upi) {
		this.appraisalcycle = upi;
	}

	public void setReviewerid(Integer inte) {
		this.reviewerid = inte;
	}

	public Integer getReviewerid() {
		return reviewerid;
	}

	public void setTotalscore(Integer inte) {
		this.totalscore = inte;
	}

	public Integer getTotalscore() {
		return totalscore;
	}

	public void setCreatedat(String inte) {
		this.createdat = inte;
	}

	public String getCreatedat() {
		return createdat;
	}
	
	public void setScores(List<AppraisalScores> lis) {
		this.scores = lis;
	}
	public List<AppraisalScores> getScores()
	{
		return scores;
	}

}
