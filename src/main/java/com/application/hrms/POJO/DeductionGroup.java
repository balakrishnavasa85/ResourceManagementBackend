package com.application.hrms.POJO;

import java.io.Serializable;
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
@Table(name = "deduction_group")
public class DeductionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEDUCTION_GROUP_SEQ")
    @SequenceGenerator(name = "DEDUCTION_GROUP_SEQ", sequenceName = "DEDUCTION_GROUP_SEQ", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

	@Column(name = "status")
	private String status;

	@OneToMany(mappedBy = "deductiongroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;


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
