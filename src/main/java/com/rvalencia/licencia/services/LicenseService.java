package com.rvalencia.licencia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rvalencia.licencia.models.License;
import com.rvalencia.licencia.repositories.LicenseRepo;

public class LicenseService {
	
	 	@Autowired
	    private LicenseRepo licenseRepo;

	    public List<License> findAll() {
	        return licenseRepo.findAll();
	    }

	    public void create(License license) {
	        licenseRepo.save(license);
	    }
}
