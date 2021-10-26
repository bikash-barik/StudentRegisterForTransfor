package com.bezkoder.springjwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Student;





@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	public java.util.List<Student> findByName(String name);


}
