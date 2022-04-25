package com.citalin.migration.springbatchmigration.mysql.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subjects_learning")
public class Subject {
	
	@Id
	@Column(name = "id")	
	private long id;
	
	@Column(name= "sub_name")
	private String subjectName;
	
	@Column(name="student_id")
	private long studentId;
	
	@Column(name = "marks_obtained")
	private int marksObtained;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public int getMarksObtained() {
		return marksObtained;
	}

	public void setMarksObtained(int marksObtained) {
		this.marksObtained = marksObtained;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", subjectName=" + subjectName + ", studentId=" + studentId + ", marksObtained="
				+ marksObtained + "]";
	}
	
	
	

}
