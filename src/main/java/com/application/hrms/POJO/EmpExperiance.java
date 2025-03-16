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
@Table(name = "empexperiance")
public class EmpExperiance implements Serializable {
       
    private static final long serialVersionUID = 1L;

    @Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
  @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="EMPEXP_SEQ")
  @SequenceGenerator(name="EMPEXP_SEQ",sequenceName="EMPEXP_SEQ",allocationSize=1)
    @Column(name = "id")
    private Integer id; 

    @Column(name = "companyname")
    private String companyname;

    @Column(name = "experiance")
    private String experiance;

    @Column(name = "joiningdate")
    private String joiningdate;
    
    @Column(name = "releavedate")
    private String releavedate;
    
    @Column(name = "mode")
    private String mode;
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    
    @Column(name = "filepath")
    private String filepath; 
   
    
    public void setCompanyName(String name) { this.companyname = name; } 
 	public void setJoiningDate(String string) {this.joiningdate = string;			}
 	public void setReleaveDate(String string) {this.releavedate = string;			}
 	public void setId(Integer inte) {this.id = inte;	}
 	public void setExperiance(String str) {this.experiance= str;}
    public void setFilepath(String filepath) {this.filepath= filepath;}
    public void setMode(String mode) {this.mode = mode;}
    
 	public String getCompanyName() { return companyname; } 
	public String getJoiningDate() {return joiningdate;		}	
	public String getReleaveDate() {return releavedate;		}	
	public Integer getId() {		return id;			}	
	public String getExperiance() {return experiance;}
	public String getFilepath() { return filepath; }
	public String getMode() { return mode;}


//	public User getUser() {
//		return user;
//	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
