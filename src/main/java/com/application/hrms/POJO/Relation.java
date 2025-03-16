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
@Table(name = "relation")
public class Relation implements Serializable {
       
    private static final long serialVersionUID = 1L;

    @Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
  @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="REL_SEQ")
  @SequenceGenerator(name="REL_SEQ",sequenceName="REL_SEQ",allocationSize=1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "relation")
    private String relation;

    @Column(name = "status")
    private String status;
    
    @Column (name = "user")
    private Integer user;
    
//    @ManyToOne
//    private User user;
   
    
    public void setName(String name) { this.name = name; } 
 	public void setStatus(String string) {this.status = string;			}	
 	public void setId(Integer inte) {this.id = inte;	}
 	public void setRelation(String str) {this.relation= str;}
    public void setUser(Integer user) {this.user = user;}
    
 	public String getName() { return name; } 
	public String getStatus() {return status;		}	
	public Integer getId() {		return id;			}	
	public String getRelation() {return relation;}
	public Integer getUser() { return user; }
	
}
