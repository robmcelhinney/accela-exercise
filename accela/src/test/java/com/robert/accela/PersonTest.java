package com.robert.accela;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PersonTest {

    @Test
    public void person() {
        Person person = new Person();
        assertEquals(true, person.checkNull());

        Person donald = new Person();
        donald.setFirstName("DONALD");
        assertEquals("DONALD", donald.getFirstName());
        donald.setLastName("DUCK");
        assertEquals(true, donald.checkNull());
        donald.setId(1);
        assertEquals(false, donald.checkNull());

        assertEquals(1, donald.getId());
    }
}
