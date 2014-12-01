package com.appdynamicspilot.model;

import org.apache.log4j.Logger;
import javax.persistence.*;

/**
 * @author Vikash

 */
@Entity
@Table(name="user")
@SecondaryTable(name="CITY", foreignKey = @ForeignKey(name = "city_id") )
public class User implements java.io.Serializable {
	public enum CUSTOMER_TYPE  {DIAMOND,PLATINUM,GOLD,SILVER,BRONZE};
	private static Logger log = Logger.getLogger(User.class.getName());
	private static final long serialVersionUID = 1L;
	private Long id  = null;
	private String email = null;
	private String password= null;
	private String customerName = null;
	private CUSTOMER_TYPE customerType = null;
	private String cityName = null;

	@Column(name="customer_name")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

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

	@Column(name="customer_type")
	@Enumerated(EnumType.STRING)
	public CUSTOMER_TYPE getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(CUSTOMER_TYPE customerType) {
		this.customerType = customerType;
	}

	@Column(name="Name")
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}

