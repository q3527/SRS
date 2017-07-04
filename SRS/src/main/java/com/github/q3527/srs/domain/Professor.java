package com.github.q3527.srs.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Professor extends Person{
	private String title;//鑱岀О
	private String department;//鍝釜瀛﹂櫌
	private ArrayList<Section> teaches;//鏁欎簡閭ｄ簺璇�
	
	public Professor() {
		super();
	}
	public Professor(String ssn, String name) {
		super(ssn, name);
	}
	public Professor(String name,String ssn,String title, String department) {
		super(name,ssn);
		this.title = title;
		this.department = department;
		teaches=new ArrayList<Section>();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public ArrayList<Section> getTeaches() {
		return teaches;
	}
	public void setTeaches(ArrayList<Section> teaches) {
		this.teaches = teaches;
	}
	
}



