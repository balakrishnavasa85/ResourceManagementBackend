package com.application.hrms.wrapper;

import com.application.hrms.POJO.UserProcess;

public class UserProcessDetailsDTO {
    private UserProcess userProcess;
    private Integer recrutmentId;
    private Integer departmentId;
    private String departmentName;
    private Integer designationId;
    private String designationName;

    public UserProcessDetailsDTO(UserProcess userProcess, Integer id, Integer depId, String departmentName, Integer desId, String designationName) {
        this.userProcess = userProcess;
        this.recrutmentId = id;
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
}