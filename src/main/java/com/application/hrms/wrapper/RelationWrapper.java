package com.application.hrms.wrapper;

import com.application.hrms.POJO.User;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class RelationWrapper {
    private  Integer id;
    private String name;
    private String status;
    private String relation;
    private Integer userid;
    public RelationWrapper(Integer id, String name, String status,String relation ,Integer user) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.relation = relation;
        this.userid = user;
    }
    
public void setName(String name) { this.name = name; }	
	public void setStatus(String string) {this.status = string;			}
	public void setId(Integer inte) {this.id = inte;	}	
	public void setRelation(String string) {this.relation = string;			}
	public void setUserId(Integer inte) {this.userid = inte;}
	 public String getName() { return name; }
	public String getStatus() {return status;		}	
	public String getRelation() {return relation;		}	
	public Integer getId() {		return id;			}
	public Integer getUserId() {return userid;}
	}