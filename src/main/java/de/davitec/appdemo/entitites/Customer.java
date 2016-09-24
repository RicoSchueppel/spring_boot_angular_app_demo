package de.davitec.appdemo.entitites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
	String name;
    String company;
    
    //constructors
    public Customer(){}
    public Customer(String name, String company){
    	this.name = name;
    	this.company = company;
    }
    
    // GETTER & SETTERS
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	@Override 
	public String toString(){
		return String.format("Customer=[%s,%s]", this.name, this.company);
	}
}
