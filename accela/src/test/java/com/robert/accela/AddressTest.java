package com.robert.accela;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    public void address() {
        Address address = new Address();
        assertEquals(true, address.checkNull());

        Address cork = new Address();
        cork.setStreet("Panna");
        assertEquals("Panna", cork.getStreet());
        cork.setCity("CORK");
        assertEquals("CORK", cork.getCity());
        cork.setState("CORK");
        assertEquals("CORK", cork.getState());
        cork.setPostalCode("1");
        assertEquals("1", cork.getPostalCode());
        cork.setId(1);
        assertEquals(1, cork.getId());
        cork.setPersonId(1);
        assertEquals(1, cork.getPersonId());
        assertEquals(false, cork.checkNull());
    }
}