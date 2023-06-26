package com.rvalencia.licencia.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rvalencia.licencia.models.License;


public interface LicenseRepo extends CrudRepository<License, Long> {
	
	public List<License> findAll();
	
	public License findTopByOrderByNumeroDesc();
	

}
