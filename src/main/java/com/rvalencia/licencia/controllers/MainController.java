package com.rvalencia.licencia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.rvalencia.licencia.models.License;
import com.rvalencia.licencia.models.Person;
import com.rvalencia.licencia.services.LicenseService;
import com.rvalencia.licencia.services.MainService;

import jakarta.validation.Valid;

@Controller
public class MainController {
	
		@Autowired
		private MainService mainService;

	    @Autowired
	    private LicenseService licenseService;

	    @GetMapping("/")
	    public String index(Model model) {
	        model.addAttribute(
	                "personList",
	                mainService.findByLicenseIsNotNull()
	        );
	        return "index.jsp";
	    }

	    @GetMapping("/persons/new")
	    public String newPerson(@ModelAttribute("Person") Person person) {
	        return "person.jsp";
	    }

	    @PostMapping("/persons/new")
	    public String createNewPerson(
	            @Valid
	            @ModelAttribute("Person") Person person,
	            BindingResult result
	    ) {
	        if (result.hasErrors()) {
	            System.out.println("has errors");
	            return "person.jsp";
	        }
	        mainService.create(person);
	        return "redirect:/";
	    }

	    @GetMapping("/licenses/new")
	    public String newLicense(
	            Model model,
	            @ModelAttribute("License") License license
	    ) {
	        model.addAttribute(
	                "personList",
	                mainService.findByLicenseIsNull()
	        );
	        return "license.jsp";
	    }

	    @PostMapping("/licenses/new")
	    public String createNewLicence(
	            @Valid
	            @ModelAttribute("License") License license,
	            BindingResult result,
	            Model model
	    ) {
	        if (result.hasErrors()) {
	            model.addAttribute(
	                    "personList",
	                    mainService.findByLicenseIsNull()
	            );
	            return "license.jsp";
	        }
	        licenseService.create(license);
	        return "redirect:/";
	    }

	    @GetMapping("/persons/{id}")
	    public String showDetail(Model model, @PathVariable("id") Long id) {
	        model.addAttribute(
	                "person",
	                mainService.findById(id)
	        );
	        return "showDetails.jsp";
	    }

}
