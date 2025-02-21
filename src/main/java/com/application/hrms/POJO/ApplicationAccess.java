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
@Table(name = "applicationaccess")
public class ApplicationAccess implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APPLICATIONACCESS_SEQ")
	@SequenceGenerator(name = "APPLICATIONACCESS_SEQ", sequenceName = "APPLICATIONACCESS_SEQ", allocationSize = 1)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "uptoaccess")
	private String uptoaccess;

	public void setName(String name) {
		this.name = name;
	}

	public void setUptoaccess(String string) {
		this.uptoaccess = string;
	}

	public void setId(Integer inte) {
		this.id = inte;
	}

	public String getName() {
		return name;
	}

	public String getUptoaccess() {
		return uptoaccess;
	}

	public Integer getId() {
		return id;
	}
}
