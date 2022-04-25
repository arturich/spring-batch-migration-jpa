package com.citalin.migration.springbatchmigration.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.citalin.migration.springbatchmigration.mysql.entity.Subject;
import com.citalin.migration.springbatchmigration.postgresql.entity.SubjectPostgre;

@Component
public class UniversitySubjectProcessor implements ItemProcessor<SubjectPostgre, Subject> {

	@Override
	public Subject process(SubjectPostgre item) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(item);
		Subject sub = new Subject();
		
		sub.setId(item.getId());
		sub.setMarksObtained(item.getMarksObtained());
		sub.setStudentId(item.getId());
		sub.setSubjectName(item.getSubjectName());
		
		return sub;
	}

}
