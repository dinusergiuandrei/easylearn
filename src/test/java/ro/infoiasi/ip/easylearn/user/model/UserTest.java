package ro.infoiasi.ip.easylearn.user.model;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testGetter_getId() throws IllegalAccessException, NoSuchFieldException {
        final User user= new User();
        final Field field = user.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(user, 12L);

        final long result = user.getId();

        assertEquals("Field wasn't retrieved properly", result, 12L);
    }

    @Test
    public void testSetter_setId() throws NoSuchFieldException, IllegalAccessException {
        final User user = new User();
        user.setId(1235L);

        final Field field = user.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(user), 1235L);
    }

    @Test
    public void testGetter_getName() throws IllegalAccessException, NoSuchFieldException {
        final User user = new User();
        final Field field = user.getClass().getDeclaredField("name");
        field.setAccessible(true);
        field.set(user, "userName");

        final String result = user.getName();

        assertEquals("Field wasn't retrieved properly", result, "userName");
    }

    @Test
    public void testSetter_setName() throws NoSuchFieldException, IllegalAccessException {
        final User user = new User();
        user.setName("userName");

        final Field field = user.getClass().getDeclaredField("name");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(user), "userName");
    }

    @Test
    public void testGetter_getFirstName() throws IllegalAccessException, NoSuchFieldException {
        final User user = new User();
        final Field field = user.getClass().getDeclaredField("firstName");
        field.setAccessible(true);
        field.set(user, "FirstName");

        final String result = user.getFirstName();

        assertEquals("Field wasn't retrieved properly", result, "FirstName");

    }

    @Test
    public void testSetter_setFirstName() throws NoSuchFieldException, IllegalAccessException {
        final User user = new User();
        user.setFirstName("userFirstName");

        final Field field = user.getClass().getDeclaredField("firstName");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(user), "userFirstName");
    }

    @Test
    public void testGetter_getEmail() throws IllegalAccessException, NoSuchFieldException {
        final User user = new User();
        final Field field = user.getClass().getDeclaredField("email");
        field.setAccessible(true);
        field.set(user, "UserEmail");

        final String result = user.getEmail();

        assertEquals("Field wasn't retrieved properly", result, "UserEmail");
    }

    @Test
    public void testSetter_setEmail() throws NoSuchFieldException, IllegalAccessException {
        final User user = new User();
        user.setEmail("userEmail@email.com");

        final Field field = user.getClass().getDeclaredField("email");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(user), "userEmail@email.com");
    }

    @Test
    public void testGetter_getPassword() throws NoSuchFieldException, IllegalAccessException {
        final User user = new User();
        final Field field = user.getClass().getDeclaredField("password");
        field.setAccessible(true);
        field.set(user, "UserPassword");

        final String result = user.getPassword();

        assertEquals("Field wasn't retrieved properly", result, "UserPassword");
    }

    @Test
    public void testSetter_setPassword() throws NoSuchFieldException, IllegalAccessException {
        final User user = new User();
        user.setPassword("password");

        final Field field = user.getClass().getDeclaredField("password");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(user), "password");
    }

    @Test
    public void testGetter_getSecretAnswer() throws IllegalAccessException, NoSuchFieldException {
        final User user = new User();
        final Field field = user.getClass().getDeclaredField("secretAnswer");
        field.setAccessible(true);
        field.set(user, "secretAnswer");

        final String result = user.getSecretAnswer();

        assertEquals("Field wasn't retrieved properly", result, "secretAnswer");
    }

    @Test
    public void testSetter_setSecretAnswer() throws NoSuchFieldException, IllegalAccessException {
        final User user = new User();
        user.setSecretAnswer("kitten");

        final Field field = user.getClass().getDeclaredField("secretAnswer");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(user), "kitten");
    }

    @Test
    public void testGetter_getScore() throws NoSuchFieldException, IllegalAccessException {
        final User user = new User();
        final Field field = user.getClass().getDeclaredField("score");
        field.setAccessible(true);
        field.set(user, 123L);

        final Long result = user.getScore();
        Long actualResult = 123L;

        assertEquals("Field wasn't retrieved properly", result, actualResult );
    }

    @Test
    public void testSetter_setScore() throws NoSuchFieldException {
        final User user = new User();
        user.setScore(1235L);

        final Field field = user.getClass().getDeclaredField("score");
        field.setAccessible(true);
        try {
            assertEquals("Fields didn't match", field.get(user), 1235L);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetter_getSecretQuestion() throws IllegalAccessException, NoSuchFieldException {
        final User user = new User();
        final Field field = user.getClass().getDeclaredField("secretQuestion");
        field.setAccessible(true);
        field.set(user, "secretQuestion");

        final String result = user.getSecretQuestion();

        assertEquals("Field wasn't retrieved properly", result, "secretQuestion");
    }

    @Test
    public void testSetter_setSecretQuestion() throws NoSuchFieldException, IllegalAccessException {
        final User user = new User();
        user.setSecretQuestion("What is your favorite animal?");

        final Field field = user.getClass().getDeclaredField("secretQuestion");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(user), "What is your favorite animal?");
    }
}