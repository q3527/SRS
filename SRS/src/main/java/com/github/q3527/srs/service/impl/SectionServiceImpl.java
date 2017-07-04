package com.github.q3527.srs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.q3527.srs.domain.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.q3527.srs.domain.SectionCatalog;
import com.github.q3527.srs.service.SectionService;
@Service("sectionService")
public class SectionServiceImpl implements SectionService {
	@Autowired
	private SectionCatalog sectionCatalog;
	@Override
	public List<Map<String, String>> querySection() {
		// TODO Auto-generated method stub
		List<Section> list=sectionCatalog.getSectionCatalog();
		int size=list.size();
		List<Map<String, String>> result=new ArrayList<Map<String, String>>();
		for(int i=0;i<size;i++){
			Map<String, String> map=new HashMap<String, String>();
			 map.put("sectionNo", String.valueOf(list.get(i).getSectionNo()));
			 map.put("name", list.get(i).getCourse().getName());
			 String capacity=list.get(i).getEnrolledStudents().size()+"/"+String.valueOf(list.get(i).getCapacity());
			 map.put("capacity", capacity);
			 map.put("room", list.get(i).getRoom());
			 map.put("time", list.get(i).getTimeOfDay());
			 map.put("day", list.get(i).getDayOfWeek());
			 map.put("teacher", list.get(i).getProfessor().getName()+"("+list.get(i).getProfessor().getTitle()+")");
			 map.put("credits", String.valueOf(list.get(i).getCourse().getCredits()));
			 result.add(map);
		}
		return result;
	}

}
