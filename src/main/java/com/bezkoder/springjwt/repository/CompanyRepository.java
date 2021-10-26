package com.bezkoder.springjwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Company;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{
	//public java.util.List<AddCompany> findByName(String name);
//	Optional<Company> findByComName(String username);


}
