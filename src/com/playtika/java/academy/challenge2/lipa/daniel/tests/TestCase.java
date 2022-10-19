package com.playtika.java.academy.challenge2.lipa.daniel.tests;

import com.playtika.java.academy.challenge2.lipa.daniel.exceptions.PlayerProfileException;
import com.playtika.java.academy.challenge2.lipa.daniel.models.PlayerProfile;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.assertEquals;

public class TestCase {


    @Test
    public void getUserNameTest() throws PlayerProfileException {
        PlayerProfile Daniel = new PlayerProfile("Daniel", "dan.lipa99@yahoo.com");
        String result = Daniel.getUserName();
        assertEquals("Doesnt return the username", "Daniel", result);
    }

    @Test
    public void getEmailTest() throws PlayerProfileException {
        PlayerProfile Daniel = new PlayerProfile("Daniel", "dan.lipa99@yahoo.com");
        String result = Daniel.getEmail();
        assertEquals("dan.lipa99@yahoo.com", result);
    }

    @Test
    public void getTotalPlayedTimeExceptionThrowTest() throws PlayerProfileException {
        boolean thrown = false;
        PlayerProfile Daniel = new PlayerProfile("Daniel", "dan.lipa99@yahoo.com");
        try {
            Daniel.getMinutesPlayedPerSessionClone();
        } catch (PlayerProfileException playerProfileException) {
            thrown = true;
        }
        Assert.assertTrue(thrown);
    }


    @Test
    public void getTotalPlayedTimeTest() throws PlayerProfileException {
        PlayerProfile Daniel = new PlayerProfile("Daniel", "dan.lipa99@yahoo.com");
        Daniel.setMinutesPlayedPerSession(new int[]{1, 2, 3, 4});
        if (Daniel.getMinutesPlayedPerSession().length >= 1) {
            int result = Daniel.getTotalPlayedTime();
            assertEquals("Not the expected sum", 10, result);
        }
    }

    @Test
    public void setEmailExceptionTest() throws PlayerProfileException {
        boolean thrown = false;
        PlayerProfile Daniel = new PlayerProfile("Daniel", "dan.lipa99@yahoo.com");
        try {
            Daniel.setEmail("mirel");
        } catch (PlayerProfileException playerProfileException) {
            thrown = true;
        }
        Assert.assertTrue(thrown);
    }

    @Test
    public void emailInitialisationTest() throws PlayerProfileException {
        PlayerProfile Daniel = new PlayerProfile("Daniel", "dan.lipa99@yahoo.com");
        String result = Daniel.getEmail();
        assertEquals("Email is not initialised correctly", "dan.lipa99@yahoo.com", result);
    }

    @Test
    public void getNbOfSessionsTest() throws PlayerProfileException {
        PlayerProfile Daniel = new PlayerProfile("Daniel", "dan.lipa99@yahoo.com");
        PlayerProfile Daniel2 = new PlayerProfile("Daniel1", "dan1.lipa99@yahoo.com");
        PlayerProfile Daniel3 = new PlayerProfile("Daniel2", "dan2.lipa99@yahoo.com");
        assertEquals("Number of players is not good", 3, PlayerProfile.getNbOfProfiles());
    }

    @Test
    public void cloneTest() throws PlayerProfileException, CloneNotSupportedException {
        PlayerProfile Daniel = new PlayerProfile("Daniel", "dan.lipa99@yahoo.com");
        Daniel.setMinutesPlayedPerSession(new int[]{1, 2, 3, 4});
        PlayerProfile clone = (PlayerProfile) Daniel.clone();
        assertEquals("Not the same name: ", Daniel.getUserName(), clone.getUserName());
        assertEquals("Not the same email: ", Daniel.getEmail(), clone.getEmail());
        Assert.assertArrayEquals("Not the same minutes played", Daniel.getMinutesPlayedPerSessionClone(), clone.getMinutesPlayedPerSessionClone());
    }
    @Test
    public void classHasOneConstructorTest() throws ClassNotFoundException {
        Class<?> c = Class.forName("com.playtika.java.academy.challenge2.lipa.daniel.models.PlayerProfile");
        Constructor[] constructors = c.getDeclaredConstructors();
        assertEquals("Class has more than one constructor.", 1, constructors.length);
    }

    @Test
    public void constructorHasTwoStringArgsTest() throws NoSuchMethodException, ClassNotFoundException {

        Class<?> c = Class.forName("com.playtika.java.academy.challenge2.lipa.daniel.models.PlayerProfile");
        Constructor constructor = c.getDeclaredConstructor(String.class, String.class);
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        assertEquals("Class does not have 2 arguments of type String", 2, parameterTypes.length);
    }


}
