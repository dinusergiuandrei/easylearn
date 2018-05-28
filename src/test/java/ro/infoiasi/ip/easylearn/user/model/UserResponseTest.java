package ro.infoiasi.ip.easylearn.user.model;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class UserResponseTest {

    @Test
    public void testGetter_getMessage() throws NoSuchFieldException, IllegalAccessException {
        final UserResponse userResponse = new UserResponse(123L);
        final Field field = userResponse.getClass().getDeclaredField("message");
        field.setAccessible(true);
        field.set(userResponse, "message");

        final String result = userResponse.getMessage();

        assertEquals("Field wasn't retrieved properly", result, "message");

    }

    @Test
    public void testGetter_getUri() throws NoSuchFieldException, IllegalAccessException {
        final UserResponse userResponse = new UserResponse(123L);
        final Field field = userResponse.getClass().getDeclaredField("uri");
        field.setAccessible(true);
        field.set(userResponse, "uri");

        final String result = userResponse.getUri();

        assertEquals("Field wasn't retrieved properly", result, "uri");
    }
}