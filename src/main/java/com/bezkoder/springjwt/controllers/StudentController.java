package com.bezkoder.springjwt.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.Student;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.StudentRepository;




@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	

	
	
	// get all company
	@GetMapping("/students")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	// create rest for add company/
		
		@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
		@PostMapping("/students")
		public Student createStudent(@RequestBody Student student) {
			return studentRepository.save(student);
		}
		
		
		// get company by id rest api
		
		@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
		@GetMapping("/students/{id}")
		public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
			Student student = studentRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("Student not exist with id :" + id));
			return ResponseEntity.ok(student);
		}
		
		
		// update company rest api
		
	
		@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
		@PutMapping("/students/{id}")
		public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails){
			Student student = studentRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("Student not exist with id :" + id));
			
			student.setName(studentDetails.getName());
			student.setAddress(studentDetails.getAddress());
			student.setEmailId(studentDetails.getEmailId());
			student.setMobileNo(studentDetails.getMobileNo());
			student.setParance_mobileNo(studentDetails.getParance_mobileNo());
			student.setRegdNo(studentDetails.getRegdNo());
			student.setPickUpAddress(studentDetails.getPickUpAddress());
		
			
			Student updatedStudent = studentRepository.save(student);
			return ResponseEntity.ok(updatedStudent);
		}
	
		
		// delete company rest api
	
		@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
		@DeleteMapping("/students/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Long id){
			Student student = studentRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("Student not exist with id :" + id));
			
			studentRepository.delete(student);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}

}
