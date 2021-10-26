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

import com.bezkoder.springjwt.models.Company;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.CompanyRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/addcompany")
public class CompanyController {
	
	@Autowired
	private CompanyRepository companyRepository;

	
	
	// get all company
	@GetMapping("/companys")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<Company> getAllCompanies(){
		return companyRepository.findAll();	
	}	
	
	// create rest for add company/
		@PostMapping("/companys")
		@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
		public Company createCompany(@RequestBody Company addCompany) {
			return companyRepository.save(addCompany);
		}
		
		
		// get company by id rest api
		@GetMapping("/companys/{id}")
		@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
		public ResponseEntity<Company> getCompanyById(@PathVariable Integer id) {
			Company company = companyRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("Student not exist with id :" + id));
			return ResponseEntity.ok(company);
		}
		
		
		// update company rest api
		
		@PutMapping("/companys/{id}")
		@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
		public ResponseEntity<Company> updateCompany(@PathVariable Integer id, @RequestBody Company companyDetails){
			Company company = companyRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("Student not exist with id :" + id));
			
			company.setComName(companyDetails.getComName());
			company.setComAddress(companyDetails.getComAddress());
			company.setComEmail(companyDetails.getComEmail());
			company.setComDescription(companyDetails.getComDescription());
			
			
			Company updatedCompany = companyRepository.save(company);
			return ResponseEntity.ok(updatedCompany);
		}
	
		
		// delete company rest api
		@DeleteMapping("/companys/{id}")
		@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
		public ResponseEntity<Map<String, Boolean>> deleteCompany(@PathVariable Integer id){
			Company company = companyRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("Student not exist with id :" + id));
			
			companyRepository.delete(company);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
	

}
