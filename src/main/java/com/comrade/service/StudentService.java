package com.comrade.service;

import com.comrade.model.Student;

import java.util.List;

public interface StudentService {
	void saveStudent(Student student);
	List<Student> getAllStudents();
	List<String> getAllName();
	List<Student> getAllStudentsByID(int id);
	Long getMaxID();
	void Update(int id);
	void remove(int id);
}
