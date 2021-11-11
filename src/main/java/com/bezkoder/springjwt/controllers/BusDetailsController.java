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

import com.bezkoder.springjwt.models.BusDetails;
import com.bezkoder.springjwt.repository.BusDetailsRepository;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/bus")
public class BusDetailsController {
	@Autowired
	private BusDetailsRepository busDeatailsRepository;
	
	//get
		@GetMapping("/addrou")
		@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
		public List<BusDetails> getAllRoute(){
			return busDeatailsRepository.findAll();
			
			}
		
		
		// create employee rest api
		@PostMapping("/addrou")
		@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
		public BusDetails createRoute(@RequestBody  BusDetails bus) {
			return busDeatailsRepository.save(bus);
			}
		
		
		
		@GetMapping("/addrou/{id}")
		@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
		public ResponseEntity<BusDetails>getRouteById(@PathVariable Long id) {
			BusDetails rou = busDeatailsRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("Id not exist with id :" + id));
			return ResponseEntity.ok(rou);	}
		// update rest api
		@PutMapping("/addrou/{id}")
		@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
		public ResponseEntity<BusDetails> updateRoute(@PathVariable Long id, @RequestBody BusDetails rouDetails){
			BusDetails rou = busDeatailsRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("ID not exist with id :" + id));
			
			rou.setBusno(rouDetails.getBusno());		
			rou.setRouteno(rouDetails.getRouteno());		
			rou.setOrigin(rouDetails.getOrigin());		
			rou.setDestination(rouDetails.getDestination());		

			BusDetails updatedrou  =  busDeatailsRepository.save(rou);
		        return ResponseEntity.ok(updatedrou);
			
		}
		// Delete rest api
		@DeleteMapping("/addrou/{id}")
		@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
		public ResponseEntity<Map<String, Boolean>> deleteRoute(@PathVariable Long id){
			BusDetails rou= busDeatailsRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("Id not exist with id :" + id));
			
			busDeatailsRepository.delete(rou);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}

}
