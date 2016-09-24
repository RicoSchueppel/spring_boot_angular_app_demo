package de.davitec.appdemo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.davitec.appdemo.entitites.Customer;
import de.davitec.appdemo.repositories.CustomerRepository;

@RestController
public class CustomersRestController {
	
	@Autowired CustomerRepository customers;
	
	//@PreAuthorize("hasRole('USER')")
	@RequestMapping(value= "/customers", method=RequestMethod.GET)
	public List<Customer> getCustomers() {
		return customers.findAll();
    }

}
