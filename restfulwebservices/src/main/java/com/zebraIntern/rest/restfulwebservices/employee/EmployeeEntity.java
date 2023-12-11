package com.zebraIntern.rest.restfulwebservices.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class EmployeeEntity {

	@Id
	private String employeeId;

	@Size(min = 2, message = "Frist name can't be empty")
	private String employeeFirstName;
	@Size(min = 2, message = "Last name can't be empty")
	private String lastName;
	@Size(min = 2, message = "Email address can't be empty")
	private String employeeEmail;
	@Size(min = 2, message = "Address can't be empty")
	private String employeeAddress;

	protected EmployeeEntity() {

	}

	public EmployeeEntity(String employeeId, String employeeFirstName, String lastName, String employeeEmail,
			String employeeAddress) {
		super();
		this.employeeId = employeeId;
		this.employeeFirstName = employeeFirstName;
		this.lastName = lastName;
		this.employeeEmail = employeeEmail;
		this.employeeAddress = employeeAddress;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	@Override
	public String toString() {
		return "EmployeeEntity [employeeId=" + employeeId + ", employeeFirstName=" + employeeFirstName + ", lastName="
				+ lastName + ", employeeEmail=" + employeeEmail + ", employeeAddress=" + employeeAddress + "]";
	}

}
