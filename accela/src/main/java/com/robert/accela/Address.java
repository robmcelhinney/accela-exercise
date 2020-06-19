package com.robert.accela;

import java.util.Objects;
import java.util.stream.Stream;

public class Address {
    
    private String street = null;
    private String city = null;
	private String state = null;
    private String postalCode = null;
    private Integer id = null;
    private Integer personId = null;
    
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
    }
    
    public Integer getPersonId() {
        return personId;
    }
    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

	public String toString() {
        return street + " " + city + " " + state 
                + " " + postalCode +  ". Id: " + id 
                +  ". personId: " + personId;
	}

	public boolean checkNull() {
		return Stream.of(id, personId, street, city, state, postalCode)
        .anyMatch(Objects::isNull);        
	}
}