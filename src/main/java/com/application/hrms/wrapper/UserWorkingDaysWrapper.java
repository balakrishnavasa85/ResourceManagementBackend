package com.application.hrms.wrapper;

import javax.persistence.Column;

import com.application.hrms.POJO.Department;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class UserWorkingDaysWrapper {
	private Integer id;

    @Column(name = "user")
    private Integer user;
    
    @Column(name = "name")
    private String name;

    @Column(name = "dayscount")
    private Integer dayscount;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private Integer year;

    @Column(name = "lastmonthnumberofdays")
    private Integer lastmonthnumberofdays;

    @Column(name = "deductiongroup")
    private String deductiongroup;

    @Column(name = "basicamount")
    private Double basicamount;

    @Column(name = "hraamount")
    private Double hraamount;

    @Column(name = "specialallowanceamount")
    private Double specialallowanceamount;

    @Column(name = "childreneducationallowanceamount")
    private Integer childreneducationallowanceamount;

    @Column(name = "carmaintenanceamount")
    private Integer carmaintenanceamount;

    @Column(name = "leavetravelallowanceamount")
    private Integer leavetravelallowanceamount;

    @Column(name = "telephoneinternetamount")
    private Integer telephoneinternetamount;

    @Column(name = "pfamount")
    private Integer pfamount;

    @Column(name = "professionaltax")
    private Integer professionaltax;

    @Column(name = "tds")
    private Double tds;

    @Column(name = "salarycredited")
    private Double salarycredited;
    
    @Column(name = "uan")
	private String uan;

	@Column(name = "pf")
	private String pf;
	
	@Column(name = "accountnumber")
	private String accountnumber;

	@Column(name = "ifsccode")
	private String ifsccode;	

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }
    
    public void setUser(Integer user) {
        this.user = user;
    }

    public String getName() {
    	return name;
    }

    public void setName(String user) {
        this.name = user;
    }

    public Integer getDayscount() {
        return dayscount;
    }

    public void setDayscount(Integer dayscount) {
        this.dayscount = dayscount;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getLastmonthnumberofdays() {
        return lastmonthnumberofdays;
    }

    public void setLastmonthnumberofdays(Integer lastmonthnumberofdays) {
        this.lastmonthnumberofdays = lastmonthnumberofdays;
    }

    public String getDeductiongroup() {
        return deductiongroup;
    }

    public void setDeductiongroup(String deductiongroup) {
        this.deductiongroup = deductiongroup;
    }

    public Double getBasicamount() {
        return basicamount;
    }

    public void setBasicamount(Double basicamount) {
        this.basicamount = basicamount;
    }

    public Double getHraamount() {
        return hraamount;
    }

    public void setHraamount(Double hramount) {
        this.hraamount = hramount;
    }

    public Double getSpecialallowanceamount() {
        return specialallowanceamount;
    }

    public void setSpecialallowanceamount(Double specialallowanceamount) {
        this.specialallowanceamount = specialallowanceamount;
    }

    public Integer getChildreneducationallowanceamount() {
        return childreneducationallowanceamount;
    }

    public void setChildreneducationallowanceamount(Integer childreneducationallowanceamount) {
        this.childreneducationallowanceamount = childreneducationallowanceamount;
    }

    public Integer getCarmaintenanceamount() {
        return carmaintenanceamount;
    }

    public void setCarmaintenanceamount(Integer carmaintenanceamount) {
        this.carmaintenanceamount = carmaintenanceamount;
    }

    public Integer getLeavetravelallowanceamount() {
        return leavetravelallowanceamount;
    }

    public void setLeavetravelallowanceamount(Integer leavetravelallowanceamount) {
        this.leavetravelallowanceamount = leavetravelallowanceamount;
    }

    public Integer getTelephoneinternetamount() {
        return telephoneinternetamount;
    }

    public void setTelephoneinternetamount(Integer telephoneinternetamount) {
        this.telephoneinternetamount = telephoneinternetamount;
    }

    public Integer getPfamount() {
        return pfamount;
    }

    public void setPfamount(Integer pfamount) {
        this.pfamount = pfamount;
    }

    public Integer getProfessionaltax() {
        return professionaltax;
    }

    public void setProfessionaltax(Integer professionaltax) {
        this.professionaltax = professionaltax;
    }

    public Double getTds() {
        return tds;
    }

    public void setTds(Double tds) {
        this.tds = tds;
    }

    public Double getSalarycredited() {
        return salarycredited;
    }

    public void setSalarycredited(Double salarycreadited) {
        this.salarycredited = salarycreadited;
    }
    
    public void setUan(String name) {
		this.uan= name;
	}

	public void setPf(String string) {
		this.pf = string;
	}
	
	public void setAccountnumber(String name) {
		this.accountnumber= name;
	}

	public void setIfsccode(String string) {
		this.ifsccode = string;
	}
	
	public String getUan() {
		return uan;
	}

	public String getPf() {
		return pf;
	}

	public String getAccountnumber() {
		return accountnumber;
	}

	public String getIfsccode() {
		return ifsccode;
	}
	}
