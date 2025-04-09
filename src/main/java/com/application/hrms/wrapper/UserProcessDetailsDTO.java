package com.application.hrms.wrapper;

import com.application.hrms.POJO.UserProcess;

public class UserProcessDetailsDTO {
    private UserProcess userProcess;
    private Integer recrutmentId;
    private String reqid;
    private Integer noofpositions;
    private Integer noofpositionsclosed;
    private Integer departmentId;
    private String departmentName;
    private Integer designationId;
    private String designationName;

    public UserProcessDetailsDTO(UserProcess userProcess, Integer id,String reqid,Integer noofpositions,Integer noofpositionsclosed, Integer depId, String departmentName, Integer desId, String designationName) {
        this.userProcess = userProcess;
        this.recrutmentId = id;
        this.reqid = reqid;
        this.noofpositions = noofpositions;
        this.noofpositionsclosed = noofpositionsclosed;
        this.departmentId = depId;
        this.departmentName = departmentName;
        this.designationId = desId;
        this.designationName = designationName;
    }

    // Getters and Setters
    public UserProcess getUserProcess() {
        return userProcess;
    }

    public void setUserProcess(UserProcess userProcess) {
        this.userProcess = userProcess;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDesignationName() {
        return designationName;
    }

    public void setDesignationName(String designationName) {
        this.designationName = designationName;
    }

    public void setRecrutmentId(Integer designationName) {
        this.recrutmentId = designationName;
    }
    
    public Integer getRecrutmentId() {
        return recrutmentId;
    }

    public void setDepartmentId(Integer designationName) {
        this.departmentId = designationName;
    }
    
    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDesignationId(Integer designationName) {
        this.designationId = designationName;
    }
    
    public Integer getDesignationId() {
        return designationId;
    }
    public void setNoofpositions(Integer name) {
		this.noofpositions = name;
	}

	public Integer getNoofpositions() {
		return noofpositions;
	}

	public void setNoofpositionsclosed(Integer name) {
		this.noofpositionsclosed = name;
	}

	public Integer getNoofpositionsclosed() {
		return noofpositionsclosed;
	}
	
	  public String getReqid() {
	        return reqid;
	    }

	    public void setReqid(String departmentName) {
	        this.reqid = departmentName;
	    }
}