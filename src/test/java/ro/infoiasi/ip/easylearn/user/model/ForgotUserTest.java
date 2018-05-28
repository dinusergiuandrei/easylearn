package ro.infoiasi.ip.easylearn.user.model;

import org.junit.Test;
import java.lang.reflect.Field;
import static org.junit.Assert.*;

public class ForgotUserTest {

    @Test
    public void testGetter_getId() throws NoSuchFieldException, IllegalAccessException {
        final ForgotUser forgotUser = new ForgotUser();
        final Field field = forgotUser.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(forgotUser, 1235L);

        final long result = forgotUser.getId();

        assertEquals("Field wasn't retrieved properly", result, 1235L);
    }

    @Test
    public void testSetter_setId() throws NoSuchFieldException, IllegalAccessException {
        final ForgotUser forgotUser = new ForgotUser();
        forgotUser.setId(1235L);

        final Field field = forgotUser.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(forgotUser), 1235L);
    }

    @Test
    public void testGetter_getEmail() throws NoSuchFieldException, IllegalAccessException {
        final ForgotUser forgotUser = new ForgotUser();
        final Field field = forgotUser.getClass().getDeclaredField("email");
        field.setAccessible(true);
        field.set(forgotUser, "userEmail@email.com");

        final String result = forgotUser.getEmail();

        assertEquals("Field wasn't retrieved properly", result, "userEmail@email.com");
    }

    @Test
    public void testSetter_setEmail() throws NoSuchFieldException, IllegalAccessException {
        final ForgotUser forgotUser = new ForgotUser();
        forgotUser.setEmail("userEmail@email.com");

        final Field field = forgotUser.getClass().getDeclaredField("email");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(forgotUser), "userEmail@email.com");
    }

    @Test
    public void testGetter_getSecretAnswer() throws NoSuchFieldException, IllegalAccessException {
        final ForgotUser forgotUser = new ForgotUser();
        final Field field = forgotUser.getClass().getDeclaredField("secretAnswer");
        field.setAccessible(true);
        field.set(forgotUser, "kitten");

        final String result = forgotUser.getSecretAnswer();

        assertEquals("Field wasn't retrieved properly", result, "kitten");
    }

    @Test
    public void testSetter_setSecretAnswer() throws NoSuchFieldException, IllegalAccessException {
        final ForgotUser forgotUser = new ForgotUser();
        forgotUser.setSecretAnswer("kitten");

        final Field field = forgotUser.getClass().getDeclaredField("secretAnswer");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(forgotUser), "kitten");
    }

    @Test
    public void testGetter_getSecretQuestion() throws NoSuchFieldException, IllegalAccessException {
        final ForgotUser forgotUser = new ForgotUser();
        final Field field = forgotUser.getClass().getDeclaredField("secretQuestion");
        field.setAccessible(true);
        field.set(forgotUser, "What is your favorite animal?");

        final String result = forgotUser.getSecretQuestion();

        assertEquals("Field wasn't retrieved properly", result, "What is your favorite animal?");
    }

    @Test
    public void testSetter_setSecretQuestion() throws NoSuchFieldException, IllegalAccessException {
        final ForgotUser forgotUser = new ForgotUser();
        forgotUser.setSecretQuestion("What is your favorite animal?");

        final Field field = forgotUser.getClass().getDeclaredField("secretQuestion");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(forgotUser), "What is your favorite animal?");
    }
}