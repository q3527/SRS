package com.github.q3527.srs.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.github.q3527.srs.dao.SectionDao;
import com.github.q3527.srs.dao.TranscriptDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
public class SectionCatalog{
	private ArrayList<Section> sectionCatalog;
	private HashMap<String,Section> map;
	public SectionCatalog(ArrayList<Section> sectionCatalog,HashMap<String,Section> map) {
		super();
		sectionCatalog =new ArrayList<Section>();
		map=new HashMap<String,Section>();
	}
	
	public SectionCatalog() {
		super();
	}
	@Autowired
	private SectionDao sectionDao;
	@Autowired
	private TranscriptDao transcriptDao;
	@PostConstruct
	public void init(){
		ArrayList<Section> list=load();
		this.sectionCatalog=list;
		int size=list.size();
		HashMap<String,Section> hashmap=new HashMap<String,Section>();
		//褰撶洿鎺ap.put...鏃跺嚭閿欙紝鍔犱竴涓猦ashmap褰撲粙璐ㄦ病闂锛燂紵锛燂紵锛燂紵锛燂紵锛燂紵锛燂紵锛燂紵锛燂紵锛燂紵锛燂紵锛燂紵
		for(int i=0;i<size;i++){
			hashmap.put(String.valueOf(list.get(i).getSectionNo()), list.get(i));
		}
		this.map=hashmap;
	}
	private ArrayList<Section> load() {
		ArrayList<Section> list=sectionDao.load();
		return list;
	}

	@PreDestroy
	public void preDestroy(){
		//鍦ㄦ鎵ц鎵归噺鏇存柊鏁版嵁搴�		
	}
	public void sectionAddEnrolledStudent(Student student, Section section) {
		map.get(String.valueOf(section.getSectionNo())).getEnrolledStudents().add(student);
		/*try{*/
			String ssn=student.getSsn();
			int sectionNo=section.getSectionNo();
			transcriptDao.add(ssn, sectionNo);
//		}catch(Exception e){
//			System.out.println("鍚憇ection_student鏁版嵁搴撻噷闈㈡彃鍏ュ嚭閿�");
//		}
		
	}
	public ArrayList<Section> getSectionCatalog() {
		return sectionCatalog;
	}

	public void setSectionCatalog(ArrayList<Section> sectionCatalog) {
		this.sectionCatalog = sectionCatalog;
	}

	public HashMap<String, Section> getMap() {
		return map;
	}

	public void setMap(HashMap<String, Section> map) {
		this.map = map;
	}

	

	

	
	
}
