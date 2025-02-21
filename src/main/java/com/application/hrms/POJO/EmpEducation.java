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
@Table(name = "empeducation")
public class EmpEducation implements Serializable {
       
    private static final long serialVersionUID = 1L;

    @Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
  @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="EMPEDUCATION_SEQ")
  @SequenceGenerator(name="EMPEDUCATION_SEQ",sequenceName="EMPEDUCATION_SEQ",allocationSize=1)
    @Column(name = "id")
    private Integer id;
    
    @Column (name = "user")
    private Integer user;

    @Column(name = "study")
    private String study;

    @Column(name = "duration")
    private String duration;

    @Column(name = "joiningdate")
    private String joiningdate;
    
    @Column(name = "releavedate")
    private String releavedate; 
    
    public void setStudy(String name) { this.study = name; } 
 	public void setJoiningDate(String string) {this.joiningdate = string;			}
 	public void setReleaveDate(String string) {this.releavedate = string;			}
 	public void setId(Integer inte) {this.id = inte;	}
 	public void setDuration(String str) {this.duration= str;}
    public void setUser(Integer user) {this.user = user;}
    
 	public String getStudy() { return study; } 
	public String getJoiningDate() {return joiningdate;		}	
	public String getReleaveDate() {return releavedate;		}	
	public Integer getId() {		return id;			}	
	public String getDuration() {return duration;}
	public Integer getUser() { return user; }
	
}
