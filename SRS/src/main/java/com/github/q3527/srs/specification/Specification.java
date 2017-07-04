package com.github.q3527.srs.specification;

import com.github.q3527.srs.domain.Section;
import com.github.q3527.srs.domain.Student;

public interface Specification<T> {
	String validate(Student student, Section section);
}
