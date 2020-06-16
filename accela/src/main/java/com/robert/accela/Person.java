package com.robert.accela;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.stream.Stream;

public class Person {
	private String firstName = null;
	private String lastName = null;
	private Integer id = null;

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

	public String toString() {
		return firstName + " " + lastName + ". Id: " + id;
	}

	public boolean checkNull() {
		return Stream.of(id, firstName, lastName)
        .anyMatch(Objects::isNull);        
	}
}