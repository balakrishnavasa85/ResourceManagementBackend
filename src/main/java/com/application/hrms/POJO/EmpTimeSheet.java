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
@Table(name = "emptimesheet")
public class EmpTimeSheet implements Serializable {
       
    private static final long serialVersionUID = 1L;

    @Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
  @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="EMPTIMESHEET_SEQ")
  @SequenceGenerator(name="EMPTIMESHEET_SEQ",sequenceName="EMPTIMESHEET_SEQ",allocationSize=1)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "logintime")
    private String logintime;

    @Column(name = "logouttime")
    private String logouttime;

    @Column(name = "workinghours")
    private String workinghours; 
    
    @Column (name = "status")
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;   
    
    public void setLogintime(String name) { this.logintime = name; } 
 	public void setLogouttime(String string) {this.logouttime = string;			}	
 	public void setId(Integer inte) {this.id = inte;	}
 	public void setWorkinghours(String str) {this.workinghours = str;}
    public void setUser(User user) {this.user = user;}
    public void setStatus(String user) {this.status = user;}
    
 	public String getLogintime() { return logintime; } 
	public String getLogouttime() {return logouttime;		}	
	public Integer getId() {		return id;			}	
	public String getWorkinghours() {return workinghours;}
	public User getUser() { return user; }
	public String getStatus() {return status;		}	
	
}
