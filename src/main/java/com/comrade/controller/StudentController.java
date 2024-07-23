package com.comrade.controller;

import java.util.List;


import com.comrade.model.Student;
import com.comrade.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class StudentController {
	Logger log=LoggerFactory.getLogger(StudentController.class);
	@Autowired
	StudentService service;
	@RequestMapping(value="/getAll",method=RequestMethod.GET)
	public List<Student> getAllStudents(){
		List<Student> all=service.getAllStudents();
		
		return all;
	}
	@RequestMapping(value="/getAllNames",method=RequestMethod.GET)
	public List<String> getAllNames(){
		List<String> s=service.getAllName();
		return s;
	}
	@RequestMapping(value="/getStudentById",method=RequestMethod.GET)
/*	http://localhost:8089/getStudentById/?id=2*/	
	public List<Student> getAllStudentsByID(@RequestParam("id")int id){
		List<Student> all=service.getAllStudentsByID(id);
		return all;
	}
	@RequestMapping(value="/getStudentById1/{id}",method=RequestMethod.GET)
	/*http://localhost:8089/getStudentById1/2*/	
	public List<Student> getAllStudentsByID1(@PathVariable("id")int id){
		List<Student> all=service.getAllStudentsByID(id);
		return all;
	}
	@RequestMapping(value="/getMaxid",method=RequestMethod.GET)
	/*http://localhost:8089/getStudentById1/2*/	
	public Long getMaXid(){
		Long all=service.getMaxID();
		return all;
	}
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public List<String> updateStudent(@PathVariable("id")int id){
		/*use @ModelAttribute for spring form or use @RequestBody for ajax or angular*/
		service.Update(id);
		List<String> s=service.getAllName();
		return s;
	}
	@RequestMapping(value="/remove/{id}",method=RequestMethod.GET)
	public List<String> removeStudent(@PathVariable("id")int id){
		service.remove(id);
		List<String> s=service.getAllName();
		return s;
	}
}
