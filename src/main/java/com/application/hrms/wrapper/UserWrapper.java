package com.application.hrms.wrapper;

import com.application.hrms.POJO.Department;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class UserWrapper {
    private  Integer id;
    private String name;
    private String  email;
    private String contactNumber;
    private String status;
    private String role;
    private Department department;
    
    public UserWrapper(Integer id, String name, String email, String contactNumber, String status,String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.status = status;
        this.role = role;
    }
    

    public void setName(String name) { this.name = name; }
	public void setContactNumber(String string) {this.contactNumber = string;}	
	public void setEmail(String string) {this.email = string;	} 
	public void setStatus(String string) {this.status = string;			}	
	public void setRole(String string) {this.role = string;	}	
	public void setId(Integer inte) {this.id = inte;	}	
	
	 public String getName() { return name; }
	 public String getContactNumber() {		return contactNumber;	}	
	public String getEmail() {return email;	} 
	public String getStatus() {return status;		}	
	public String getRole() {return role;	}	
	public Integer getId() {		return id;			}
	 public Department getDepartment() {
	        return department;
	    }

	    public void setRole(Department dept) {
	        this.department = dept;
	    }
	}
