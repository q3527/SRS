package com.github.q3527.srs.service;

import java.util.ArrayList;

import com.github.q3527.srs.domain.Student;

public interface SelectSectionService {

	String selectSection(String ssn, int sectionNo);

	ArrayList<Student> queryEnrolledStudents(int sectionNo);

}
