package com.application.hrms.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class DepartmentWrapper {
    private  Integer id;
    private String name;
    private String status;

    public DepartmentWrapper(Integer id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
    
public void setName(String name) { this.name = name; }	
	public void setStatus(String string) {this.status = string;			}
	public void setId(Integer inte) {this.id = inte;	}	
	
	 public String getName() { return name; }
	public String getStatus() {return status;		}	
	public Integer getId() {		return id;			}
	}