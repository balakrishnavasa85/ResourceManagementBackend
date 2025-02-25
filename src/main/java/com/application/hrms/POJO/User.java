package com.application.hrms.POJO;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
	@SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "contactNumber")
	private String contactNumber;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "status")
	private String status;

	@Column(name = "role")
	private String role;

	@Column(name = "dob")
	private String dob;

	@Column(name = "doj")
	private String doj;

	@Column(name = "gender")
	private String gender;

	@Column(name = "maritalstatus")
	private String maritalstatus;

	@Column(name = "address")
	private String address;

	@Column(name = "salarypa")
	private String salarypa;

	@Column(name = "basicpa")
	private String basicpa;

	@Column(name = "dor")
	private String dor;

	@Column(name = "login")
	private String login;

	@Column(name = "manager")
	private String manager;

	@Column(name = "reporting")
	private String reporting;

	@Column(name = "reportername")
	private String reportername;

	@Column(name = "mode")
	private String mode;

	@Column(name = "profilepic")
	private String profilepic;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private SalaryDetails salaryDetails;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private IdentityDetails identityDetails;

	@ManyToOne(fetch = FetchType.EAGER) // EAGER fetch so department is loaded with user
	@JoinColumn(name = "deductiongroup_id")
	private DeductionGroup deductiongroup;

	@ManyToOne(fetch = FetchType.EAGER) // EAGER fetch so department is loaded with user
	@JoinColumn(name = "department_id")
	private Department department;

	@ManyToOne(fetch = FetchType.EAGER) // EAGER fetch so department is loaded with user
	@JoinColumn(name = "designation_id")
	private Designation designation;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<EmpTimeSheet> empTimeSheets;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<LeaveRequest> leaveRequests;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Leaves> leaves;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<UserWorkingDays> userWorkingDays;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<UserWorkingHours> userWorkingHours;

	public void setName(String name) {
		this.name = name;
	}

	public void setContactNumber(String string) {
		this.contactNumber = string;
	}

	public void setEmail(String string) {
		this.email = string;
	}

	public void setStatus(String string) {
		this.status = string;
	}

	public void setRole(String string) {
		this.role = string;
	}

	public void setId(Integer inte) {
		this.id = inte;
	}

	public void setPassword(String string) {
		this.password = string;
	}

	public void setDob(String str) {
		this.dob = str;
	}

	public void setDoj(String str) {
		this.doj = str;
	}

	public void setGender(String str) {
		this.gender = str;
	}

	public void setMaritalstatus(String str) {
		this.maritalstatus = str;
	}

	public void setAddress(String str) {
		this.address = str;
	}

	public void setSalarypa(String str) {
		this.salarypa = str;
	}

	public void setBasicpa(String str) {
		this.basicpa = str;
	}

	public void setDor(String str) {
		this.dor = str;
	}

	public void setDepartment(Department dept) {
		this.department = dept;
	}

	public void setDesignation(Designation desi) {
		this.designation = desi;
	}

	public void setDeductionGroup(DeductionGroup desi) {
		this.deductiongroup = desi;
	}

	public void setLogin(String logi) {
		this.login = logi;
	}

	public void setManager(String logi) {
		this.manager = logi;
	}

	public void setReporting(String logi) {
		this.reporting = logi;
	}

	public void setMode(String logi) {
		this.mode = logi;
	}

	public void setProfilepic(String logi) {
		this.profilepic = logi;
	}

	public void setReportername(String name) {
		this.reportername = name;
	}

	public String getName() {
		return name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getStatus() {
		return status;
	}

	public String getRole() {
		return role;
	}

	public Integer getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getDob() {
		return dob;
	}

	public String getDoj() {
		return doj;
	}

	public String getGender() {
		return gender;
	}

	public String getMaritalstatus() {
		return maritalstatus;
	}

	public String getAddress() {
		return address;
	}

	public String getSalarypa() {
		return salarypa;
	}

	public String getBasicpa() {
		return basicpa;
	}

	public String getDor() {
		return dor;
	}

	public Department getDepartment() {
		return department;
	}

	public Designation getDesignation() {
		return designation;
	}

	public DeductionGroup getDeductionGroup() {
		return deductiongroup;
	}

	public String getLogin() {
		return login;
	}

	public String getManager() {
		return manager;
	}

	public String getReporting() {
		return reporting;
	}

	public String getMode() {
		return mode;
	}

	public String getProfilepic() {
		return profilepic;
	}

	public String getReportername() {
		return reportername;
	}

	public SalaryDetails getSalaryDetails() {
		return salaryDetails;
	}

	public void setSalaryDetails(SalaryDetails salaryDetails) {
		this.salaryDetails = salaryDetails;
	}
	public IdentityDetails getIdentityDetails() {
        return identityDetails;
    }

    public void setIdentityDetails(IdentityDetails identityDetails) {
        this.identityDetails = identityDetails;
    }
}