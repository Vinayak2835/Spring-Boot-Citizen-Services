package com.citizenservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citizenservice.entity.Citizen;
import com.citizenservice.repository.CitizenRepository;

@RestController
@RequestMapping("/citizen")
public class CitizenController {

	@Autowired
	private CitizenRepository citizenRepository;
    
	//To get citizens based on vaccinationCenterId
	@RequestMapping("/id/{id}")
	public ResponseEntity<List<Citizen>> getAllCitizensBasedOnId(@PathVariable("id") int id) {

		List<Citizen> listOfCitizens = citizenRepository.findByVaccinationCenterId(id);

		   if(listOfCitizens.size() <=0 ) {
			   return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	       }  
	      else{
			   return ResponseEntity.of(Optional.of(listOfCitizens));
		   }

}
    
	//To add citizens
	@PostMapping("/add")
	public ResponseEntity<Citizen> addCitizen(@RequestBody Citizen citizen) {
        
		System.out.println("Citizens" +citizen);
		try {
            citizenRepository.save(citizen);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
