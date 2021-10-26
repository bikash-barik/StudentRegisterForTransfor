package com.bezkoder.springjwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.Company;
import com.bezkoder.springjwt.models.JobDrive;
import com.bezkoder.springjwt.repository.CompanyRepository;
import com.bezkoder.springjwt.repository.JobDriveRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/addjob")
public class JobController {
	
	
	@Autowired
	private JobDriveRepository jobdriveRepository;
	
	// get all jobs
		@GetMapping("/jobs")
		@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
		public List<JobDrive> getAllCompanies(){
			return jobdriveRepository.findAll();	
		}	
		

}
