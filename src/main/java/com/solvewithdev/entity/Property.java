package com.solvewithdev.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROPERTY")
public class Property {

	@Id
	@GeneratedValue
	@Column(name = "PROPERTY_ID")
	private Long propertyId;

	@Column(name = "PROPERTY_NAME")
	private String propertyName;

	@Column(name = "PROPERTY_ADD1")
	private String propertyAdd1;

	@Column(name = "PROPERTY_ADD2")
	private String propertyAdd2;

	@Column(name = "PROPERTY_PRICE")
	private String propertyPrice;

	@Column(name = "PROPERTY_AREA_MEASURE")
	private String propertyareaMeasurement;

	@Column(name = "PROPERTY_TYPE")
	private String propertyType;

	public Long getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyAdd1() {
		return propertyAdd1;
	}

	public void setPropertyAdd1(String propertyAdd1) {
		this.propertyAdd1 = propertyAdd1;
	}

	public String getPropertyAdd2() {
		return propertyAdd2;
	}

	public void setPropertyAdd2(String propertyAdd2) {
		this.propertyAdd2 = propertyAdd2;
	}

	public String getPropertyPrice() {
		return propertyPrice;
	}

	public void setPropertyPrice(String propertyPrice) {
		this.propertyPrice = propertyPrice;
	}

	public String getPropertyareaMeasurement() {
		return propertyareaMeasurement;
	}

	public void setPropertyareaMeasurement(String propertyareaMeasurement) {
		this.propertyareaMeasurement = propertyareaMeasurement;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

}
