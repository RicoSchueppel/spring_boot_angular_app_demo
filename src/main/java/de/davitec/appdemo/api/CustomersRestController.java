package de.davitec.appdemo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.davitec.appdemo.entitites.Customer;
import de.davitec.appdemo.repositories.CustomerRepository;

@RestController
@RequestMapping("/api/customers")
public class CustomersRestController {
	
	@Autowired CustomerRepository customers;
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value= "/", method=RequestMethod.GET)
	public List<Customer> getCustomers() {
		return customers.findAll();
    }
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value= "/{id}", method=RequestMethod.GET)
	public Customer getCustomer(@PathVariable("id") long id) {
		return customers.findOne(id);
    }
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value= "/", method=RequestMethod.POST)
	public Customer createCustomer(Customer customer) {
		return customers.save(customer);
    }
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value= "/", method=RequestMethod.PUT)
	public Customer updateCustomer(Customer customer) {
		return customers.save(customer);
    }
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value= "/{id}", method=RequestMethod.DELETE)
	public String deleteCustomer(@PathVariable("id") long id) {
		customers.delete(id);
		return "";
    }

}
