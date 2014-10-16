package com.appdynamicspilot.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;
import javax.persistence.*;
/**
 * @author Ravichandra
 */
@XmlRootElement(name="product")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="item")
public class Item implements java.io.Serializable {

	private static Logger log = Logger.getLogger(Item.class.getName());
	private static final long serialVersionUID = 1L;
	@XmlElement
	private Long id;
	@XmlElement
	private String title;
	@XmlElement
	private String imagePath;
	@XmlElement
	private double price;
	

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

    @Column(name="title", nullable=false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public void setId(Long id) {
		this.id = id;
	}

    @Column(name="imagePath", nullable=true)
	public String getImagePath() {
		return imagePath;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

    @Column(name="price")
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}

