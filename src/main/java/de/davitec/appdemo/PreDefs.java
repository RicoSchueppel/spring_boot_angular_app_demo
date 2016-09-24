package de.davitec.appdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import de.davitec.appdemo.entitites.Customer;
import de.davitec.appdemo.repositories.CustomerRepository;


@Configuration
public class PreDefs {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Bean
    public CommandLineRunner addCustomers(CustomerRepository customers){
    	return (args) -> {
    		Customer c1 = new Customer("Müller","Musterfirma");
    		customers.save(c1);
    		log.info("add " + c1.toString());
    		
    		Customer c2 = new Customer("Meier","Just a company");
    		customers.save(c2);
    		log.info("add " + c2.toString());
    	};
	}	
}
