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
@Table(name = "taxsubmission")
public class TaxSubmission implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAX_SEQ")
	@SequenceGenerator(name = "TAX_SEQ", sequenceName = "tax_SEQ", allocationSize = 1)
	@Column(name = "id")
	private Integer id;

	@Column(name = "status")
	private String status;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@Column(name = "section80C")
	private String section80C;

	@Column(name = "section80CCD1B")
	private String section80CCD1B;

	@Column(name = "section80D")
	private String section80D;

	@Column(name = "section80DD")
	private String section80DD;

	@Column(name = "section80E")
	private String section80E;

	@Column(name = "educationLoanProvider")
	private String educationLoanProvider;

	@Column(name = "section80EE")
	private String section80EE;

	@Column(name = "section80EEA")
	private String section80EEA;

	@Column(name = "landlordPAN")
	private String landlordPAN;

	@Column(name = "propertyValue")
	private String propertyValue;

	@Column(name = "section80EEB")
	private String section80EEB;

	@Column(name = "section80G")
	private String section80G;

	@Column(name = "donationMode")
	private String donationMode;

	@Column(name = "rentPaid")
	private String rentPaid;

	@Column(name = "section80TTA")
	private String section80TTA;

	@Column(name = "section80TTB")
	private String section80TTB;	

	@Column(name = "assesmentyear")
	private String assesmentyear;
	
	public void setassesmentyear(String val)
	{
		this.assesmentyear = val;
	}
	
	public String getassesmentyear()
	{
		return assesmentyear;
	}

	public void setsection80C(String val) {
		this.section80C = val;
	}

	public String getsection80C() {
		return section80C;
	}

	public void setsection80CCD1B(String val) {
		this.section80CCD1B = val;
	}

	public String getsection80CCD1B() {
		return section80CCD1B;
	}

	public void setsection80D(String val) {
		this.section80D = val;
	}

	public String getsection80D() {
		return section80D;
	}

	public void setsection80DD(String val) {
		this.section80DD = val;
	}

	public String getsection80DD() {
		return section80DD;
	}

	public void setsection80E(String val) {
		this.section80E = val;
	}

	public String getsection80E() {
		return section80E;
	}

	public void seteducationLoanProvider(String val) {
		this.educationLoanProvider = val;
	}

	public String geteducationLoanProvider() {
		return educationLoanProvider;
	}

	public void setsection80EE(String val) {
		this.section80EE = val;
	}

	public String getsection80EE() {
		return section80EE;
	}

	public void setsection80EEA(String val) {
		this.section80EEA = val;
	}

	public String getsection80EEA() {
		return section80EEA;
	}

	public void setpropertyValue(String val) {
		this.propertyValue = val;
	}

	public String getpropertyValue() {
		return propertyValue;
	}

	public void setsection80EEB(String val) {
		this.section80EEB = val;
	}

	public String getsection80EEB() {
		return section80EEB;
	}

	public void setsection80G(String val) {
		this.section80G = val;
	}

	public String getsection80G() {
		return section80G;
	}

	public void setdonationMode(String val) {
		this.donationMode = val;
	}

	public String getdonationMode() {
		return donationMode;
	}

	public void setrentPaid(String val) {
		this.rentPaid = val;
	}

	public String getrentPaid() {
		return rentPaid;
	}

	public void setlandlordPAN(String val) {
		this.landlordPAN = val;
	}

	public String getlandlordPAN() {
		return landlordPAN;
	}

	public void setsection80TTA(String val) {
		this.section80TTA = val;
	}

	public String getsection80TTA() {
		return section80TTA;
	}

	public void setsection80TTB(String val) {
		this.section80TTB = val;
	}

	public String getsection80TTB() {
		return section80TTB;
	}

	public void setId(Integer inte) {
		this.id = inte;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setStatus(String user) {
		this.status = user;
	}

	public Integer getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public String getStatus() {
		return status;
	}

}
