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
@Table(name = "process")
public class Process implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROCESS_SEQ")
	@SequenceGenerator(name = "PROCESS__SEQ", sequenceName = "PROCESS_SEQ", allocationSize = 1)
	@Column(name = "id")
	private Integer id;

	@Column(name = "username")
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setId(Integer inte) {
		this.id = inte;
	}

	public String getName() {
		return name;
	}

	public Integer getId() {
		return id;
	}
	
}
