package ro.infoiasi.ip.easylearn.management.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestProblem {

    @Test
    public void testSetter_setProblemId() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        problem.setId(2017L);

        final Field field = problem.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(problem), 2017L);
    }

    @Test
    public void testSetter_setUserId() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        problem.setUserId(123L);

        final Field field = problem.getClass().getDeclaredField("userId");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(problem), 123L);
    }

    @Test
    public void testSetter_setTitle() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        problem.setTitle("titluTest");

        final Field field = problem.getClass().getDeclaredField("title");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(problem), "titluTest");
    }

    @Test
    public void testSetter_setDescription() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        problem.setDescription("This is a description");

        final Field field = problem.getClass().getDeclaredField("description");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(problem), "This is a description");
    }

    @Test
    public void testSetter_setRequirement() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        problem.setRequirement("This is a problem you should know how to solve.");

        final Field field = problem.getClass().getDeclaredField("requirement");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(problem), "This is a problem you should know how to solve.");
    }

    @Test
    public void testSetter_setInput() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        problem.setInput("n m k");

        final Field field = problem.getClass().getDeclaredField("input");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(problem), "n m k");
    }

    @Test
    public void testSetter_setOutput() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        problem.setOutput("123 5");

        final Field field = problem.getClass().getDeclaredField("output");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(problem), "123 5");
    }

    @Test
    public void testSetter_setRestrictions() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        problem.setRestrictions("1 < n < 10000\n3 < m < 10000\n1 < k < 100");

        final Field field = problem.getClass().getDeclaredField("restrictions");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(problem), "1 < n < 10000\n3 < m < 10000\n1 < k < 100");
    }

    @Test
    public void testSetter_setDifficulty() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        problem.setDifficulty(6);

        final Field field = problem.getClass().getDeclaredField("difficulty");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(problem), (int)6);
    }

    @Test
    public void testSetter_setCategoryId() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        problem.setCategoryId(2L);

        final Field field = problem.getClass().getDeclaredField("categoryId");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(problem), (long)2);
    }

    @Test
    public void testSetter_setDataType() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        problem.setDataType("tastatura");

        final Field field = problem.getClass().getDeclaredField("dataType");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(problem), "tastatura");
    }

    @Test
    public void testSetter_setInputExample() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        problem.setInputExample("200 38 5");

        final Field field = problem.getClass().getDeclaredField("inputExample");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(problem), "200 38 5");
    }

    @Test
    public void testSetter_setOutputExample() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        problem.setOutputExample("26");

        final Field field = problem.getClass().getDeclaredField("outputExample");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(problem), "26");
    }

    @Test
    public void testSetter_setInputFile() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        testProblem.setInputFile("");

        final Field field = testProblem.getClass().getDeclaredField("inputFile");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testProblem), "");
    }

    @Test
    public void testSetter_setOutputFile() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        problem.setOutputFile("raspuns.out");

        final Field field = problem.getClass().getDeclaredField("outputFile");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(problem), "raspuns.out");
    }

    @Test
    public void testSetter_setMemoryLimit() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        testProblem.setMemoryLimit(20);

        final Field field = testProblem.getClass().getDeclaredField("memoryLimit");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testProblem), (long)20);
    }

    @Test
    public void testSetter_setTimeLimit() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        problem.setTimeLimit(1000);

        final Field field = problem.getClass().getDeclaredField("timeLimit");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(problem), (double)1000);
    }

    @Test
    public void testGetter_getProblemId() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        final Field field = problem.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(problem, (long)20);

        final long result = problem.getId();

        assertEquals("Field wasn't retrieved properly", result, (long)20);
    }


    @Test
    public void testGetter_getAuthorId() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        final Field field = problem.getClass().getDeclaredField("userId");
        field.setAccessible(true);
        field.set(problem, problem.getUserId());

        final Long authorId = problem.getUserId();

        assertEquals("Field wasn't retrieved properly", authorId, problem.getUserId());
    }


    @Test
    public void testGetter_getTitle() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        final Field field = problem.getClass().getDeclaredField("title");
        field.setAccessible(true);
        field.set(problem, "titluTest");

        final String title = problem.getTitle();

        assertEquals("Field wasn't retrieved properly", title, "titluTest");
    }

    @Test
    public void testGetter_getDescription() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        final Field field = problem.getClass().getDeclaredField("description");
        field.setAccessible(true);
        field.set(problem, "This is a description");

        final String result = problem.getDescription();

        assertEquals("Field wasn't retrieved properly", result, "This is a description");
    }

    @Test
    public void testGetter_getRequirement() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        final Field field = problem.getClass().getDeclaredField("requirement");
        field.setAccessible(true);
        field.set(problem, "Sum up the input numbers.");

        final String result = problem.getRequirement();

        assertEquals("Field wasn't retrieved properly", result, "Sum up the input numbers.");

    }

    @Test
    public void testGetter_getInput() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        final Field field = problem.getClass().getDeclaredField("input");
        field.setAccessible(true);
        field.set(problem, "n m k");

        final String result = problem.getInput();

        assertEquals("Field wasn't retrieved properly", result, "n m k");

    }

    @Test
    public void testGetter_getOutput() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        final Field field = problem.getClass().getDeclaredField("output");
        field.setAccessible(true);
        field.set(problem, "123 5");

        final String result = problem.getOutput();

        assertEquals("Field wasn't retrieved properly", result, "123 5");

    }

    @Test
    public void testGetter_getRestrictions() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        final Field field = problem.getClass().getDeclaredField("restrictions");
        field.setAccessible(true);
        field.set(problem, "1 < n <= 200");

        final String result = problem.getRestrictions();

        assertEquals("Field wasn't retrieved properly", result, "1 < n <= 200");

    }

    @Test
    public void testGetter_getDifficulty() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        final Field field = problem.getClass().getDeclaredField("difficulty");
        field.setAccessible(true);
        field.set(problem, (int)2);

        final int result = problem.getDifficulty();

        assertEquals("Field wasn't retrieved properly", result, (int)2);

    }

    @Test
    public void testGetter_getCategoryId() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        final Field field = problem.getClass().getDeclaredField("categoryId");
        field.setAccessible(true);
        field.set(problem, 3L);

        final long result = problem.getCategoryId();

        assertEquals("Field wasn't retrieved properly", result, 3L);

    }

    @Test
    public void testGetter_getDataType() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        final Field field = problem.getClass().getDeclaredField("dataType");
        field.setAccessible(true);
        field.set(problem, "fisier");

        final String result = problem.getDataType();

        assertEquals("Field wasn't retrieved properly", result, "fisier");

    }

    @Test
    public void testGetter_getInputExample() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        final Field field = testProblem.getClass().getDeclaredField("inputExample");
        field.setAccessible(true);
        field.set(testProblem, "12 5 6 89 5 6 2 3");

        final String result = testProblem.getInputExample();

        assertEquals("Field wasn't retrieved properly", result, "12 5 6 89 5 6 2 3");
    }

    @Test
    public void testGetter_getOutputExample() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        final Field field = problem.getClass().getDeclaredField("outputExample");
        field.setAccessible(true);
        field.set(problem, "2 4 6 8 10 12 14 16 18 20");

        final String result = problem.getOutputExample();

        assertEquals("Field wasn't retrieved properly", result, "2 4 6 8 10 12 14 16 18 20");
    }

    @Test
    public void testGetter_getInputFile() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        final Field field = problem.getClass().getDeclaredField("inputFile");
        field.setAccessible(true);
        field.set(problem, "foxy.in");

        final String result = problem.getInputFile();

        assertEquals("Field wasn't retrieved properly", result, "foxy.in");
    }

    @Test
    public void testGetter_getOutputFile() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        final Field field = problem.getClass().getDeclaredField("outputFile");
        field.setAccessible(true);
        field.set(problem, "foxy.out");

        final String result = problem.getOutputFile();

        assertEquals("Field wasn't retrieved properly", result, "foxy.out");

    }

    @Test
    public void testGetter_getMemoryLimit() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        final Field field = problem.getClass().getDeclaredField("memoryLimit");
        field.setAccessible(true);
        field.set(problem, (long)23);

        final long result = problem.getMemoryLimit();

        assertEquals("Field wasn't retrieved properly", result, (long)23);

    }

    @Test
    public void testGetter_getTimeLimit() throws NoSuchFieldException, IllegalAccessException {
        final Problem problem = new Problem();
        final Field field = problem.getClass().getDeclaredField("timeLimit");
        field.setAccessible(true);
        field.set(problem, (double)1000);

        final double result = problem.getTimeLimit();

//        assertEquals("Field wasn't retrieved properly", result, (double)1000);
        Assert.assertEquals((double)1000, result, 1);
    }
}