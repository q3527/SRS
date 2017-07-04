package com.github.q3527.srs.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 鍔ㄦ�佽烦杞帶鍒跺櫒
 * @author Administrator
 *
 */
@Controller
public class JspController {
	@RequestMapping(value="/{formName}")
	public String jspReturn(@PathVariable String formName){
		return formName;		
	}
}
