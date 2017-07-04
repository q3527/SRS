package com.github.q3527.srs.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.q3527.srs.domain.Professor;
import com.github.q3527.srs.service.ProfessorService;
/**
 * 瀵规暀甯堣繘琛屽鍒犳煡鏀�,闂锛氭暀甯堢紪鍙锋棤娉曚互0寮�澶达紝easyUI numberbox鐨刡ug
 * @author Administrator
 *
 */
@Controller
public class ProfessorController {
	@Autowired
	@Qualifier("professorService")
	private ProfessorService professorService;
	@RequestMapping(value="addProfessor")
	@ResponseBody
	public boolean addProfessor(@ModelAttribute Professor professor){
		boolean result=professorService.addProfessor(professor);		
		return result;
	}
	@RequestMapping(value="queryProfessor")
	@ResponseBody
	public  Object queryProfessor(){
		List<Professor> result=professorService.queryProfessor();
		return result;
	}
	@RequestMapping(value="deleteProfessor")
	@ResponseBody
	public ModelAndView deleteProfessor(String ssn,ModelAndView mv){
		boolean result=professorService.deleteProfessor(ssn);
		mv.setViewName("professorManager");
		mv.addObject("result", result);
		return mv;
	}
	@RequestMapping(value="updateProfessor")
	public ModelAndView updateProfessor(ModelAndView mv,
			@ModelAttribute Professor professor){
		boolean result=professorService.updateProfessor(professor);
		mv.setViewName("professorManager");
		mv.addObject("result", result);
		return mv;
	}
}
