package com.application.hrms.POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@AllArgsConstructor
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "fromsixteen")
public class FormSixteen implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FORM16_SEQ")
	@SequenceGenerator(name = "FORM16_SEQ", sequenceName = "FORM16_SEQ", allocationSize = 1)
	@Column(name = "id")
	private Integer id;

	@Column(name = "assesmentyear")
	private String assesmentyear;
	

	@Column(name = "filepath")
	private String filepath;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

//    @ManyToOne
//    private User user;

	public void setId(Integer inte) {
		this.id = inte;
	}

	public void setAssesmentyear(String str) {
		this.assesmentyear = str;
	}

	public void setFilepath(String str) {
		this.filepath = str;
	}
	
	public Integer getId() {
		return id;
	}

	public String getAssesmentyear() {
		return assesmentyear;
	}

	public String getFilepath() {
		return filepath;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

}
