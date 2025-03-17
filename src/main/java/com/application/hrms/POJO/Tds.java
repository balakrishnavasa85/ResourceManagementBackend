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
@Table(name = "tds")
public class Tds implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TDS_SEQ")
	@SequenceGenerator(name = "TDS_SEQ", sequenceName = "TDS_SEQ", allocationSize = 1)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "fromamount")
	private Integer fromamount;
	
	@Column(name = "toamount")
	private Integer toamount;
	
	@Column(name = "percentage")
	private Integer percentage;
	
	

//    private User user;

	public void setName(String name) {
		this.name = name;
	}

	public void setFromamount(Integer string) {
		this.fromamount = string;
	}

	public void setToamount(Integer name) {
		this.toamount = name;
	}

	public void setPercentage(Integer string) {
		this.percentage = string;
	}
	
	public void setId(Integer inte) {
		this.id = inte;
	}

	public String getName() {
		return name;
	}

	public Integer getFromamount() {
		return fromamount;
	}
	
	public Integer getToamount() {
		return toamount;
	}

	public Integer getPercentage() {
		return percentage;
	}

	public Integer getId() {
		return id;
	} 

}
