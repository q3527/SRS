package com.github.q3527.srs.specification;

import com.github.q3527.srs.domain.Course;
import com.github.q3527.srs.domain.Section;
import com.github.q3527.srs.domain.Student;
import com.github.q3527.srs.domain.TranscriptEntity;
import org.springframework.stereotype.Service;

@Service("selectionSectionSpecification")
public class SelectionSectionSpecification implements Specification<Section> {
	@Override
	public String validate(Student student, Section section) {
		String result=null;
		boolean ifPlan=false;//鍩瑰吇璁″垝鏄惁鍖呭惈璇ラ棬璇剧▼
		boolean ifSelect=false;//鏄惁宸查�夎闂ㄨ绋�
		boolean ifPrevCan=false;//杩欓棬璇炬槸鍚︽湁鍏堜慨璇剧▼浠ュ強鏄惁閫夎繃鍏堜慨璇剧▼
		for(Course course:student.getPlanOfStudy()){
			if(course.getNumber().equals(section.getCourse().getNumber())){
				ifPlan=true;
				break;
			}
		}
		if(ifPlan==true){//褰撳煿鍏昏鍒掑寘鍚繖闂ㄨ鏃惰繘琛屾槸鍚﹂�夎繃杩欓棬璇惧垽鏂�
			if(student.getAttends()==null){
				
			}else{
				for(Section s:student.getAttends()){
					if(s.getCourse().getNumber().equals(section.getCourse().getNumber())){
						ifSelect=true;
						break;
					}
				}
			}
		}
		if(ifPlan==true&&ifSelect==false){//褰撳煿鍏昏鍒掑寘鍚繖闂ㄨ骞朵笖鏈�夎繃杩涜杩欓棬璇剧殑鍏堜慨璇剧▼鏄惁閫夎繃骞跺強鏍煎垽鏂�
			if(section.getCourse().getPrevCourse()!=null){
				for(Course c:section.getCourse().getPrevCourse()){
					ifPrevCan=false;
					if(student.getTranscript()==null){
						
					}else{
						for(TranscriptEntity t:student.getTranscript()){
							if(t.getSection().getCourse().getNumber().equals(c.getNumber())&&t.getGrade()>=60){
								ifPrevCan=true;
							}
						}
					}
					if(ifPrevCan==false){
						result="鍏堜慨璇剧▼涓嶈揪鏍囷紒";
						break;
					}
				}
			}
		}
		if(ifPlan==false){
			result="鎮ㄧ殑璇剧▼璁″垝涓笉鍖呭惈杩欓棬璇剧▼锛�";
			return result;
		}else if(section.getCapacity()<=section.getEnrolledStudents().size()){
			result="浜烘暟宸叉弧锛�";
			return result;
		}else if(ifSelect==true){
			result="鎮ㄥ凡缁忛�夎繃杩欓棬璇剧▼!";
			return result;
		}
		return result;
	}
}
