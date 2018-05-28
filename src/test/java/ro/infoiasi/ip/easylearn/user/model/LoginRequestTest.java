package ro.infoiasi.ip.easylearn.user.model;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class LoginRequestTest {

    @Test
    public void testGetter_getEmail() throws NoSuchFieldException, IllegalAccessException {
        final LoginRequest loginRequest = new LoginRequest();
        final Field field = loginRequest.getClass().getDeclaredField("email");
        field.setAccessible(true);
        field.set(loginRequest, "email@email.com");

        final String result = loginRequest.getEmail();

        assertEquals("Field wasn't retrieved properly", result, "email@email.com");
    }

    @Test
    public void testSetter_setEmail() throws NoSuchFieldException, IllegalAccessException {
        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("email@email.com");

        final Field field = loginRequest.getClass().getDeclaredField("email");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(loginRequest), "email@email.com");
    }

    @Test
    public void testGetter_getPassword() throws NoSuchFieldException, IllegalAccessException {
        final LoginRequest loginRequest = new LoginRequest();
        final Field field = loginRequest.getClass().getDeclaredField("password");
        field.setAccessible(true);
        field.set(loginRequest, "thisIsAPassword");

        final String result = loginRequest.getPassword();

        assertEquals("Field wasn't retrieved properly", result, "thisIsAPassword");
    }

    @Test
    public void testSetter_setPassword() throws NoSuchFieldException, IllegalAccessException {
        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("thisIsAPassword");

        final Field field = loginRequest.getClass().getDeclaredField("password");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(loginRequest), "thisIsAPassword");
    }
}