package com.application.hrms.POJO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "deductiongroup")
public class DeductionGroup implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEDUCTIONGROUP_SEQ")
	@SequenceGenerator(name = "DEDUCTIONGROUP_SEQ", sequenceName = "DEDUCTIONGROUP_SEQ", allocationSize = 1)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "value")
	private String value;

	@Column(name = "status")
	private String status;

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String string) {
		this.value = string;
	}

	public void setId(Integer inte) {
		this.id = inte;
	}

	public void setStatus(String string) {
		this.status = string;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public Integer getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}
}
