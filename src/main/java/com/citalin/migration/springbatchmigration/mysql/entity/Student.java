package com.citalin.migration.springbatchmigration.mysql.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="email")
	private String email;
	@Column(name="dept_id")
	private int departmentId;
	@Column(name="is_active")
	private boolean active;
	
	
	public Student()
	{
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}


	public boolean getActive() {
		return active;
	}


	public void setActive(boolean isActive) {
		this.active = isActive;
	}


	@Override
	public String toString() {
		return "StudentPostgre [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", departmentId=" + departmentId + ", isActive=" + active + "]";
	}

}
