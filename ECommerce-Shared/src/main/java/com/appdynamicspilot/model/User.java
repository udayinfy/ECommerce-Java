package com.appdynamicspilot.model;

import org.apache.log4j.Logger;
import javax.persistence.*;

/**
 * @author Vikash

 */
@Entity
@Table(name="User")
public class User implements java.io.Serializable {

	private static Logger log = Logger.getLogger(User.class.getName());
	private static final long serialVersionUID = 1L;
	private Long id;
	private String email;
	private String password;
	

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="email",nullable=false,length=100)
	public String getEmail() {
		return email;
	}


    @Column(name="password",nullable=false,length=32)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}

