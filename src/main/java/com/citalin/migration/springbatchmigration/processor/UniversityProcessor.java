package com.citalin.migration.springbatchmigration.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.citalin.migration.springbatchmigration.mysql.entity.Student;
import com.citalin.migration.springbatchmigration.postgresql.entity.StudentPostgre;

@Component
public class UniversityProcessor implements ItemProcessor<StudentPostgre, Student>{

	@Override
	public Student process(StudentPostgre item) throws Exception {
		System.out.println("Inside postgres item : " + item.toString());
		Student studentMysql = new Student();
		studentMysql.setId(item.getId());
		studentMysql.setFirstName(item.getFirstName());
		studentMysql.setLastName(item.getLastName());
		studentMysql.setEmail(item.getEmail());
		studentMysql.setActive(Boolean.parseBoolean(item.getActive()));
		studentMysql.setDepartmentId(item.getDepartmentId());		
		return studentMysql;
	}

}
