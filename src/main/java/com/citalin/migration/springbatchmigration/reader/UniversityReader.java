package com.citalin.migration.springbatchmigration.reader;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.citalin.migration.springbatchmigration.postgresql.entity.StudentPostgre;
import com.citalin.migration.springbatchmigration.postgresql.entity.SubjectPostgre;

@Component
public class UniversityReader {	
	
	
	public JpaCursorItemReader<StudentPostgre> jpaCursorItemReader()
	{
		JpaCursorItemReader<StudentPostgre> jpaCursorItemReader =
				new JpaCursorItemReader<StudentPostgre>();
		
		jpaCursorItemReader.setEntityManagerFactory(entityManagerFactory);
		
		jpaCursorItemReader.setQueryString("From StudentPostgre");
		
		// We can receive this parameter as arguments or  setting on
		// properties.
	//	jpaCursorItemReader.setCurrentItemCount(2000);
		
	//	jpaCursorItemReader.setMaxItemCount(3000);
		
		return jpaCursorItemReader;
			
	}
	
	public JpaCursorItemReader<SubjectPostgre> jpaSubjectCursorItemReader()
	{
		JpaCursorItemReader<SubjectPostgre> jpaCursorItemReader =
				new JpaCursorItemReader<SubjectPostgre>();
		
		jpaCursorItemReader.setEntityManagerFactory(entityManagerFactory);
		
		jpaCursorItemReader.setQueryString("From SubjectPostgre");
		
		// We can receive this parameter as arguments or  setting on
		// properties.
		//jpaCursorItemReader.setCurrentItemCount(2000);
		
		
		return jpaCursorItemReader;
			
	}
	
		
	
	@Autowired
	@Qualifier("postgresEntityManagerFactory")
	private EntityManagerFactory entityManagerFactory; 

}
