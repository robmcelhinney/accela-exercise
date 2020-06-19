package com.robert.accela;

import java.util.Objects;
import java.util.stream.Stream;

public class Person {
	private String firstName = null;
	private String lastName = null;
	private Integer id = null;
	private Address address = null;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	public String toString() {
		return firstName + " " + lastName + ". Id: " + id;
	}

	public boolean checkNull() {
		return Stream.of(id, firstName, lastName)
        .anyMatch(Objects::isNull);        
	}
}