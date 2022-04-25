package com.citalin.migration.springbatchmigration.listener;

import java.io.File;
import java.io.FileWriter;

import org.springframework.batch.core.SkipListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.citalin.migration.springbatchmigration.mysql.entity.Subject;
import com.citalin.migration.springbatchmigration.postgresql.entity.SubjectPostgre;

@Component
public class SkipListenerImpl implements SkipListener<SubjectPostgre, Subject> {
	
	@Value("${batch.error.migrate.subjects.reader}")
	String migrateSubjectsReaderPath;
	
	@Value("${batch.error.migrate.subjects.processor}")
	String migrateSubjectsProcessorPath;
	
	@Value("${batch.error.migrate.subjects.writer}")
	String migrateSubjectsWriterPath;
	

	@Override
	public void onSkipInRead(Throwable th) {
		
		
		//if(th instanceof )
		
	}

	@Override
	public void onSkipInWrite(Subject item, Throwable t) {
		createFile(migrateSubjectsWriterPath, item.toString());
		
	}

	@Override
	public void onSkipInProcess(SubjectPostgre item, Throwable t) {
		createFile(migrateSubjectsProcessorPath, item.toString());
		
	}
	
	private void createFile(String path, String data)
	{
		try (FileWriter fw = new FileWriter(new File(path),true)) {			
			fw.write(data+ "\n");			
		} catch (Exception e) {
			
		}
	}

}
