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
@Table(name = "appraisalcycles")
public class AppraisalCycles implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Appraisalcycles_SEQ")
	@SequenceGenerator(name = "Appraisalcycles_SEQ", sequenceName = "Appraisalcycles_SEQ", allocationSize = 1)
	@Column(name = "cycleid")
	private Integer cycleid;

	@Column(name = "cyclename")
	private String cyclename;

	@Column(name = "startdate")
	private String startdate;

	@Column(name = "enddate")
	private String enddate; 
	
	 @OneToMany(mappedBy = "appraisalcycle")
	    private List<Appraisals> appraisals;

	public void setCycleName(String name) {
		this.cyclename = name;
	}

	public void setStartdate(String string) {
		this.startdate = string;
	}

	public void setEnddate(String string) {
		this.enddate = string;
	}

	public void setCycleid(Integer inte) {
		this.cycleid = inte;
	}

	public String getCycleName() {
		return cyclename;
	}

	public String getStartdate() {
		return startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public Integer getCycleid() {
		return cycleid;
	} 
	public void setAppraisals(List<Appraisals> app)
	{
		this.appraisals = app;
	}
	public List<Appraisals> getAppraisals()
	{
		return appraisals;
	}
}
