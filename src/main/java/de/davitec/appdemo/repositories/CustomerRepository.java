package de.davitec.appdemo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import de.davitec.appdemo.entitites.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{
	
	List<Customer> findByName(@Param("name") String name);
}
