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
    public void testSetter_setProblemID() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        testProblem.setProblemID(2018);

        final Field field = testProblem.getClass().getDeclaredField("problemID");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testProblem), (long)2018);
    }

    @Test
    public void testSetter_setAuthorID() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        testProblem.setAuthorID("testAuthorId");

        final Field field = testProblem.getClass().getDeclaredField("authorID");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testProblem), "testAuthorId");
    }

    @Test
    public void testSetter_setTitlu() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        testProblem.setTitlu("titluTest");

        final Field field = testProblem.getClass().getDeclaredField("titlu");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testProblem), "titluTest");
    }

    @Test
    public void testSetter_setDescriere() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        testProblem.setDescriere("This is a description");

        final Field field = testProblem.getClass().getDeclaredField("descriere");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testProblem), "This is a description");
    }

    @Test
    public void testSetter_setCerinta() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        testProblem.setCerinta("This is a problem you should know how to solve.");

        final Field field = testProblem.getClass().getDeclaredField("cerinta");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testProblem), "This is a problem you should know how to solve.");
    }

    @Test
    public void testSetter_setDate_intrare() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        testProblem.setDate_intrare("n m k");

        final Field field = testProblem.getClass().getDeclaredField("date_intrare");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testProblem), "n m k");
    }

    @Test
    public void testSetter_setDate_iesire() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        testProblem.setDate_iesire("123 5");

        final Field field = testProblem.getClass().getDeclaredField("date_iesire");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testProblem), "123 5");
    }

    @Test
    public void testSetter_setRestrictii() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        testProblem.setRestrictii("1 < n < 10000\n3 < m < 10000\n1 < k < 100");

        final Field field = testProblem.getClass().getDeclaredField("restrictii");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testProblem), "1 < n < 10000\n3 < m < 10000\n1 < k < 100");
    }

    @Test
    public void testSetter_setDificultate() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        testProblem.setDificultate(6);

        final Field field = testProblem.getClass().getDeclaredField("dificultate");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testProblem), (int)6);
    }

    @Test
    public void testSetter_setCategorie() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        testProblem.setCategorie(2);

        final Field field = testProblem.getClass().getDeclaredField("categorie");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testProblem), (long)2);
    }

    @Test
    public void testSetter_setTip_date() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        testProblem.setTip_date("tastatura");

        final Field field = testProblem.getClass().getDeclaredField("tip_date");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testProblem), "tastatura");
    }

    @Test
    public void testSetter_setExemplu_intrare() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        testProblem.setExemplu_intrare("200 38 5");

        final Field field = testProblem.getClass().getDeclaredField("exemplu_intrare");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testProblem), "200 38 5");
    }

    @Test
    public void testSetter_setExemplu_iesire() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        testProblem.setExemplu_iesire("26");

        final Field field = testProblem.getClass().getDeclaredField("exemplu_iesire");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testProblem), "26");
    }

    @Test
    public void testSetter_setInput_file() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        testProblem.setInput_file("");

        final Field field = testProblem.getClass().getDeclaredField("input_file");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testProblem), "");
    }

    @Test
    public void testSetter_setOutput_file() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        testProblem.setOutput_file("raspuns.out");

        final Field field = testProblem.getClass().getDeclaredField("output_file");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testProblem), "raspuns.out");
    }

    @Test
    public void testSetter_setMax_memory() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        testProblem.setMax_memory(20);

        final Field field = testProblem.getClass().getDeclaredField("max_memory");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testProblem), (long)20);
    }

    @Test
    public void testSetter_setMax_time() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        testProblem.setMax_time(1000);

        final Field field = testProblem.getClass().getDeclaredField("max_time");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testProblem), (double)1000);
    }

    @Test
    public void testGetter_getProblemId() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        final Field field = testProblem.getClass().getDeclaredField("problemID");
        field.setAccessible(true);
        field.set(testProblem, (long)20);

        final long result = testProblem.getProblemID();

        assertEquals("Field wasn't retrieved properly", result, (long)20);
    }


    @Test
    public void testGetter_getAuthorId() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        final Field field = testProblem.getClass().getDeclaredField("authorID");
        field.setAccessible(true);
        field.set(testProblem, "testAuthorId");

        final String result = testProblem.getAuthorID();

        assertEquals("Field wasn't retrieved properly", result, "testAuthorId");
    }


    @Test
    public void testGetter_getTitlu() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        final Field field = testProblem.getClass().getDeclaredField("titlu");
        field.setAccessible(true);
        field.set(testProblem, "titluTest");

        final String result = testProblem.getTitlu();

        assertEquals("Field wasn't retrieved properly", result, "titluTest");
    }

    @Test
    public void testGetter_getDescriere() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        final Field field = testProblem.getClass().getDeclaredField("descriere");
        field.setAccessible(true);
        field.set(testProblem, "This is a description");

        final String result = testProblem.getDescriere();

        assertEquals("Field wasn't retrieved properly", result, "This is a description");
    }

    @Test
    public void testGetter_getCerinta() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        final Field field = testProblem.getClass().getDeclaredField("cerinta");
        field.setAccessible(true);
        field.set(testProblem, "Sum up the input numbers.");

        final String result = testProblem.getCerinta();

        assertEquals("Field wasn't retrieved properly", result, "Sum up the input numbers.");

    }

    @Test
    public void testGetter_getDate_Intrare() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        final Field field = testProblem.getClass().getDeclaredField("date_intrare");
        field.setAccessible(true);
        field.set(testProblem, "n m k");

        final String result = testProblem.getDate_intrare();

        assertEquals("Field wasn't retrieved properly", result, "n m k");

    }

    @Test
    public void testGetter_getDate_Iesire() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        final Field field = testProblem.getClass().getDeclaredField("date_iesire");
        field.setAccessible(true);
        field.set(testProblem, "123 5");

        final String result = testProblem.getDate_iesire();

        assertEquals("Field wasn't retrieved properly", result, "123 5");

    }

    @Test
    public void testGetter_getRestrictii() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        final Field field = testProblem.getClass().getDeclaredField("restrictii");
        field.setAccessible(true);
        field.set(testProblem, "1 < n <= 200");

        final String result = testProblem.getRestrictii();

        assertEquals("Field wasn't retrieved properly", result, "1 < n <= 200");

    }

    @Test
    public void testGetter_getDificultate() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        final Field field = testProblem.getClass().getDeclaredField("dificultate");
        field.setAccessible(true);
        field.set(testProblem, (int)2);

        final int result = testProblem.getDificultate();

        assertEquals("Field wasn't retrieved properly", result, (int)2);

    }

    @Test
    public void testGetter_getCategorie() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        final Field field = testProblem.getClass().getDeclaredField("categorie");
        field.setAccessible(true);
        field.set(testProblem, (long)3);

        final long result = testProblem.getCategorie();

        assertEquals("Field wasn't retrieved properly", result, (long)3);

    }

    @Test
    public void testGetter_getTip_Date() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        final Field field = testProblem.getClass().getDeclaredField("tip_date");
        field.setAccessible(true);
        field.set(testProblem, "fisier");

        final String result = testProblem.getTip_date();

        assertEquals("Field wasn't retrieved properly", result, "fisier");

    }

    @Test
    public void testGetter_getExemplu_Intrare() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        final Field field = testProblem.getClass().getDeclaredField("exemplu_intrare");
        field.setAccessible(true);
        field.set(testProblem, "12 5 6 89 5 6 2 3");

        final String result = testProblem.getExemplu_intrare();

        assertEquals("Field wasn't retrieved properly", result, "12 5 6 89 5 6 2 3");
    }

    @Test
    public void testGetter_getExemplu_Iesire() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        final Field field = testProblem.getClass().getDeclaredField("exemplu_iesire");
        field.setAccessible(true);
        field.set(testProblem, "2 4 6 8 10 12 14 16 18 20");

        final String result = testProblem.getExemplu_iesire();

        assertEquals("Field wasn't retrieved properly", result, "2 4 6 8 10 12 14 16 18 20");
    }

    @Test
    public void testGetter_getInput_File() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        final Field field = testProblem.getClass().getDeclaredField("input_file");
        field.setAccessible(true);
        field.set(testProblem, "foxy.in");

        final String result = testProblem.getInput_file();

        assertEquals("Field wasn't retrieved properly", result, "foxy.in");
    }

    @Test
    public void testGetter_getOutput_File() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        final Field field = testProblem.getClass().getDeclaredField("output_file");
        field.setAccessible(true);
        field.set(testProblem, "foxy.out");

        final String result = testProblem.getOutput_file();

        assertEquals("Field wasn't retrieved properly", result, "foxy.out");

    }

    @Test
    public void testGetter_getMax_Memory() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        final Field field = testProblem.getClass().getDeclaredField("max_memory");
        field.setAccessible(true);
        field.set(testProblem, (long)23);

        final long result = testProblem.getMax_memory();

        assertEquals("Field wasn't retrieved properly", result, (long)23);

    }

    @Test
    public void testGetter_getMax_Time() throws NoSuchFieldException, IllegalAccessException {
        final Problem testProblem = new Problem();
        final Field field = testProblem.getClass().getDeclaredField("max_time");
        field.setAccessible(true);
        field.set(testProblem, (double)1000);

        final double result = testProblem.getMax_time();

//        assertEquals("Field wasn't retrieved properly", result, (double)1000);
        Assert.assertEquals((double)1000, result, 1);
    }
}