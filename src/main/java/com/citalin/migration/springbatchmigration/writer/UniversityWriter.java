package com.citalin.migration.springbatchmigration.writer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.citalin.migration.springbatchmigration.mysql.entity.Student;
import com.citalin.migration.springbatchmigration.mysql.entity.Subject;


@Component
public class UniversityWriter implements ItemWriter<Student>{

	@Override
	public void write(List<? extends Student> items) throws Exception {
		System.out.println("Inside item writer");		
		items.stream()
		.forEach(System.out::println);		
	}
	
	public JpaItemWriter<Student> jpaItemWriter()
	{
		JpaItemWriter<Student> jpaItemWriter =
				new JpaItemWriter<Student>();
		
		jpaItemWriter.setEntityManagerFactory(entityManagerFactory);		
		
		return jpaItemWriter;
	}
	
	public JpaItemWriter<Subject> jpaSubjectItemWriter()
	{
		JpaItemWriter<Subject> jpaItemWriter =
				new JpaItemWriter<Subject>();
		
		jpaItemWriter.setEntityManagerFactory(entityManagerFactory);		
		
		return jpaItemWriter;
	}
	
	@Autowired
	@Qualifier("mySqlEntityManagerFactory")
	private EntityManagerFactory entityManagerFactory; 

}