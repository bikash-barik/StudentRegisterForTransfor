package com.bezkoder.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.JobDrive;


@Repository
public interface JobDriveRepository extends JpaRepository<JobDrive, Integer> {

}
