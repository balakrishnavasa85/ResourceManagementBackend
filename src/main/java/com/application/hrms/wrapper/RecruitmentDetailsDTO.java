package com.application.hrms.wrapper;

public class RecruitmentDetailsDTO {
	 private Integer scheduleId;
	    private Integer interviewerId;
	    private String interviewTime;
	    private String interviewStatus;
	    private String interviewerName;
	    private String interviewerTakentime;
	    private String interviewerComment;
	    private Integer userProcessId;
	    private String candidateName;
	    private String candidateEmail;
	    private String candidateContact;
	    private String aadhar;
	    private String pan;
	    private String expectedSalary;
	    private String experianceInMonths;
	    private String candidateDob;
	    private String joiningOn;
	    private String resumePath;
	    private Integer recruitmentId;
	    private String recruitmentReqId;
	    private String jobTitle;
	    private String jobDescription;
	    private String recruitmentStatus;
	    private Integer noOfPositions;
	    private Integer noOfPositionsClosed;
	    private String budget;
	    private Integer departmentId;
	    private String departmentName;
	    private Integer designationId;
	    private String designationName;
	    private Integer recruitmentAssignerId;
	    private Integer recruterAssigneruserId;
//	    private Integer assignerUserId;
//	    private String assignerName;
//	    private String assignerEmail;

	    // Constructor
	    public RecruitmentDetailsDTO(Integer scheduleId, Integer interviewerId, String interviewTime, 
	                                String interviewStatus, String interviewerName,String takentime,String comment, Integer userProcessId, 
	                                String candidateName, String candidateEmail, String candidateContact, 
	                                String aadhar, String pan, String expectedSalary, String experianceInMonths, 
	                                String candidateDob, String joiningOn, String resumePath, Integer recruitmentId, 
	                                String recruitmentReqId, String jobTitle, String jobDescription, 
	                                String recruitmentStatus, Integer noOfPositions, Integer noOfPositionsClosed, 
	                                String budget, Integer departmentId, String departmentName, Integer designationId, String designationName, 
	                                Integer recruitmentAssignerId,Integer recruterAssigneruserId
//	                                , Integer assignerUserId, String assignerName, String assignerEmail
	                                ) {
	        this.scheduleId = scheduleId;
	        this.interviewerId = interviewerId;
	        this.interviewTime = interviewTime;
	        this.interviewStatus = interviewStatus;
	        this.interviewerName = interviewerName;
	        this.interviewerTakentime = takentime;
	        this.interviewerComment = comment;
	        this.userProcessId = userProcessId;
	        this.candidateName = candidateName;
	        this.candidateEmail = candidateEmail;
	        this.candidateContact = candidateContact;
	        this.aadhar = aadhar;
	        this.pan = pan;
	        this.expectedSalary = expectedSalary;
	        this.experianceInMonths = experianceInMonths;
	        this.candidateDob = candidateDob;
	        this.joiningOn = joiningOn;
	        this.resumePath = resumePath;
	        this.recruitmentId = recruitmentId;
	        this.recruitmentReqId = recruitmentReqId;
	        this.jobTitle = jobTitle;
	        this.jobDescription = jobDescription;
	        this.recruitmentStatus = recruitmentStatus;
	        this.noOfPositions = noOfPositions;
	        this.noOfPositionsClosed = noOfPositionsClosed;
	        this.budget = budget;
	        this.departmentId = departmentId;
	        this.departmentName = departmentName;
	        this.designationId = departmentId;
	        this.designationName = designationName;
	        this.recruitmentAssignerId = recruitmentAssignerId;
	        this.recruterAssigneruserId = recruterAssigneruserId;
//	        this.assignerUserId = assignerUserId;
//	        this.assignerName = assignerName;
//	        this.assignerEmail = assignerEmail;
	    }

	    // Getters and Setters
	    public Integer getScheduleId() {
	        return scheduleId;
	    }

	    public void setScheduleId(Integer scheduleId) {
	        this.scheduleId = scheduleId;
	    }

	    public Integer getInterviewerId() {
	        return interviewerId;
	    }

	    public void setInterviewerId(Integer interviewerId) {
	        this.interviewerId = interviewerId;
	    }

	    public String getInterviewTime() {
	        return interviewTime;
	    }

	    public void setInterviewTime(String interviewTime) {
	        this.interviewTime = interviewTime;
	    }

	    public String getInterviewStatus() {
	        return interviewStatus;
	    }

	    public void setInterviewStatus(String interviewStatus) {
	        this.interviewStatus = interviewStatus;
	    }

	    public String getInterviewerName() {
	        return interviewerName;
	    }

	    public void setInterviewerName(String interviewerName) {
	        this.interviewerName = interviewerName;
	    }

	    public Integer getUserProcessId() {
	        return userProcessId;
	    }

	    public void setUserProcessId(Integer userProcessId) {
	        this.userProcessId = userProcessId;
	    }

	    public String getCandidateName() {
	        return candidateName;
	    }

	    public void setCandidateName(String candidateName) {
	        this.candidateName = candidateName;
	    }

	    public String getCandidateEmail() {
	        return candidateEmail;
	    }

	    public void setCandidateEmail(String candidateEmail) {
	        this.candidateEmail = candidateEmail;
	    }

	    public String getCandidateContact() {
	        return candidateContact;
	    }

	    public void setCandidateContact(String candidateContact) {
	        this.candidateContact = candidateContact;
	    }

	    public String getAadhar() {
	        return aadhar;
	    }

	    public void setAadhar(String aadhar) {
	        this.aadhar = aadhar;
	    }

	    public String getPan() {
	        return pan;
	    }

	    public void setPan(String pan) {
	        this.pan = pan;
	    }

	    public String getExpectedSalary() {
	        return expectedSalary;
	    }

	    public void setExpectedSalary(String expectedSalary) {
	        this.expectedSalary = expectedSalary;
	    }

	    public String getExperianceInMonths() {
	        return experianceInMonths;
	    }

	    public void setExperianceInMonths(String experianceInMonths) {
	        this.experianceInMonths = experianceInMonths;
	    }

	    public String getCandidateDob() {
	        return candidateDob;
	    }

	    public void setCandidateDob(String candidateDob) {
	        this.candidateDob = candidateDob;
	    }

	    public String getJoiningOn() {
	        return joiningOn;
	    }

	    public void setJoiningOn(String joiningOn) {
	        this.joiningOn = joiningOn;
	    }

	    public String getResumePath() {
	        return resumePath;
	    }

	    public void setResumePath(String resumePath) {
	        this.resumePath = resumePath;
	    }

	    public Integer getRecruitmentId() {
	        return recruitmentId;
	    }

	    public void setRecruitmentId(Integer recruitmentId) {
	        this.recruitmentId = recruitmentId;
	    }

	    public String getRecruitmentReqId() {
	        return recruitmentReqId;
	    }

	    public void setRecruitmentReqId(String recruitmentReqId) {
	        this.recruitmentReqId = recruitmentReqId;
	    }

	    public String getJobTitle() {
	        return jobTitle;
	    }

	    public void setJobTitle(String jobTitle) {
	        this.jobTitle = jobTitle;
	    }

	    public String getJobDescription() {
	        return jobDescription;
	    }

	    public void setJobDescription(String jobDescription) {
	        this.jobDescription = jobDescription;
	    }

	    public String getRecruitmentStatus() {
	        return recruitmentStatus;
	    }

	    public void setRecruitmentStatus(String recruitmentStatus) {
	        this.recruitmentStatus = recruitmentStatus;
	    }

	    public Integer getNoOfPositions() {
	        return noOfPositions;
	    }

	    public void setNoOfPositions(Integer noOfPositions) {
	        this.noOfPositions = noOfPositions;
	    }

	    public Integer getNoOfPositionsClosed() {
	        return noOfPositionsClosed;
	    }

	    public void setNoOfPositionsClosed(Integer noOfPositionsClosed) {
	        this.noOfPositionsClosed = noOfPositionsClosed;
	    }

	    public String getBudget() {
	        return budget;
	    }

	    public void setBudget(String budget) {
	        this.budget = budget;
	    }

	    public Integer getDepartmentId() {
	        return departmentId;
	    }

	    public void setDepartmentId(Integer departmentId) {
	        this.departmentId = departmentId;
	    }

	    public String getDepartmentName() {
	        return departmentName;
	    }

	    public void setDepartmentName(String departmentName) {
	        this.departmentName = departmentName;
	    }
	    
	    public Integer getDesignationId() {
	        return designationId;
	    }

	    public void setDesignationId(Integer departmentId) {
	        this.designationId = departmentId;
	    }

	    public String getDesignationName() {
	        return designationName;
	    }

	    public void setDesignationName(String departmentName) {
	        this.designationName = departmentName;
	    }

	    public Integer getRecruitmentAssignerId() {
	        return recruitmentAssignerId;
	    }

	    public void setRecruitmentAssignerId(Integer recruitmentAssignerId) {
	        this.recruitmentAssignerId = recruitmentAssignerId;
	    }
	    public Integer getRecruterAssigneruserId()
	    {
	    	return recruterAssigneruserId;
	    }
	    public void setRecruterAssigneruserId(Integer raui)
	    {
	    	this.recruterAssigneruserId = raui;
	    }
	    public String getInterviewerTakentime()
	    {
	    	return interviewerTakentime;
	    }
	    
	    public void setInterviewerTakentime(String time)
	    {
	    	this.interviewerTakentime = time;
	    }
	    
	    public String getInterviewerComment()
	    {
	    	return interviewerComment;
	    }
	    
	    public void setInterviewerComment(String time)
	    {
	    	this.interviewerComment = time;
	    }

//	    public Integer getAssignerUserId() {
//	        return assignerUserId;
//	    }
//
//	    public void setAssignerUserId(Integer assignerUserId) {
//	        this.assignerUserId = assignerUserId;
//	    }
//
//	    public String getAssignerName() {
//	        return assignerName;
//	    }
//
//	    public void setAssignerName(String assignerName) {
//	        this.assignerName = assignerName;
//	    }
//
//	    public String getAssignerEmail() {
//	        return assignerEmail;
//	    }
//
//	    public void setAssignerEmail(String assignerEmail) {
//	        this.assignerEmail = assignerEmail;
//	    }

  
    
    // Getters & Setters (or use Lombok @Data for brevity)
}
