package ro.infoiasi.ip.easylearn.management.model;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class ProblemResponseTest {

    @Test
    public void testGetter_getMessage() throws NoSuchFieldException, IllegalAccessException {
        final ProblemResponse problemResponse = new ProblemResponse();
        final Field field = problemResponse.getClass().getDeclaredField("message");
        field.setAccessible(true);
        field.set(problemResponse, "message");

        final String result = problemResponse.getMessage();

        assertEquals("Field wasn't retrieved properly", result, "message");
    }

    @Test
    public void testGetter_getUri() throws NoSuchFieldException, IllegalAccessException {
        final ProblemResponse problemResponse = new ProblemResponse();
        final Field field = problemResponse.getClass().getDeclaredField("uri");
        field.setAccessible(true);
        field.set(problemResponse, "http://localhost:8100/problems/");

        final String result = problemResponse.getUri();

        assertEquals("Field wasn't retrieved properly", result, "http://localhost:8100/problems/");
    }

    @Test
    public void testGetter_getId() throws NoSuchFieldException, IllegalAccessException {
        final ProblemResponse problemResponse = new ProblemResponse();
        final Field field = problemResponse.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(problemResponse, 1235L);

        final long result = problemResponse.getId();

        assertEquals("Field wasn't retrieved properly", result, 1235L);
    }
}