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

	@Column(name = "score")
	private String score;
	
	@Column(name = "comments")
	private String comments;
	
	@Column(name= "data")
	private String data;
	
	public void setId(Integer inte) {
		this.appraisalid = inte;
	}

	public Integer getId() {
		return appraisalid;
	}

	public void setUser(User upi) {
		this.user = upi;
	}

	public User getUser()
	{
		return user;
	}
	
	public void setScore(String score)
	{
		this.score = score;
	}
	public String getScore()
	{
		return score;
	}
	public void setComments(String score)
	{
		this.comments = score;
	}
	public String getComments()
	{
		return comments;
	}
	public void setData(String score)
	{
		this.data = score;
	}
	public String getData()
	{
		return data;
	}
}
