package com.bezkoder.springjwt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "JobDrive")
public class JobDrive {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
     
	
	
	private String jobType;
	private String jobSalary;
	private String jobVacancy;
	private String jobDescription;
	
}
