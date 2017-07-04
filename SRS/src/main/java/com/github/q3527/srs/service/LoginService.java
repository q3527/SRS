package com.github.q3527.srs.service;

import com.github.q3527.srs.domain.Student;

public interface LoginService {
	/**
	 * 学生登陆
	 * @param ssn
	 * @param password
	 * @return
	 */
	Student studentLogin(String ssn, String password);
}
