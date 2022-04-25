package com.citalin.migration.springbatchmigration.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;

import com.citalin.migration.springbatchmigration.mysql.entity.Student;
import com.citalin.migration.springbatchmigration.mysql.entity.Subject;
import com.citalin.migration.springbatchmigration.postgresql.entity.StudentPostgre;
import com.citalin.migration.springbatchmigration.postgresql.entity.SubjectPostgre;
import com.citalin.migration.springbatchmigration.processor.UniversityProcessor;
import com.citalin.migration.springbatchmigration.processor.UniversitySubjectProcessor;
import com.citalin.migration.springbatchmigration.reader.UniversityReader;
import com.citalin.migration.springbatchmigration.writer.UniversityWriter;

@Configuration
public class MigrationJob {
	
		
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private UniversityReader universityReader;
	
	@Autowired
	private UniversityProcessor universityProcessor;
	
	@Autowired
	private UniversityWriter universityWriter;
	
	@Autowired
	private JpaTransactionManager jpaTransactionManager;
	
	@Autowired
	private UniversitySubjectProcessor universitySubjectProcessor;
	
	
//	@Bean
	public Job universityMigrationJob()
	{
		return jobBuilderFactory.get("migrationJob")
				.incrementer(new RunIdIncrementer())
				.start(migrationStep())
				.build();
	}
	
	@Bean
	public Job universitySubjectMigrationJob()
	{
		return jobBuilderFactory.get("subjectMigrationJob")
				.incrementer(new RunIdIncrementer())
				.start(subjectMigrationStep())
				.build();
	}
	
	
	private Step migrationStep() 
	{
		return stepBuilderFactory.get("migrationStep")
				.<StudentPostgre,Student>chunk(10)
				.reader(universityReader.jpaCursorItemReader())				
				.processor(universityProcessor)
				.writer(universityWriter.jpaItemWriter())	
				.faultTolerant()
				.skip(Throwable.class)
				.skipLimit(100)
				.retryLimit(3)
				.retry(Throwable.class)				
				.transactionManager(jpaTransactionManager)
				.build();
			
	}
	
	private Step subjectMigrationStep() 
	{
		return stepBuilderFactory.get("subjectMigrationStep")
				.<SubjectPostgre,Subject>chunk(10)
				.reader(universityReader.jpaSubjectCursorItemReader())				
				.processor(universitySubjectProcessor)
				.writer(universityWriter.jpaSubjectItemWriter())	
				.faultTolerant()
				.skip(Throwable.class)
				.skipLimit(1000000)
				.retryLimit(3)
				.retry(Throwable.class)		
				.listener(skipListenerImpl)
				.transactionManager(jpaTransactionManager)
				.build();
			
	}
	
	

}
