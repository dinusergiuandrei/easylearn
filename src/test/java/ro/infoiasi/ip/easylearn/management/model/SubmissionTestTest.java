package ro.infoiasi.ip.easylearn.management.model;

import org.junit.Test;
import ro.infoiasi.ip.easylearn.submission.model.Submission;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class SubmissionTestTest {

    @Test
    public void testSetter_setSubmissionId() throws NoSuchFieldException, IllegalAccessException {
        final SubmissionTest testSubmissionTest = new SubmissionTest();
        testSubmissionTest.setSubmissionId(123056L);

        final Field field = testSubmissionTest.getClass().getDeclaredField("submissionId");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testSubmissionTest), 123056L);
    }

    @Test
    public void testSetter_setTestId() throws NoSuchFieldException, IllegalAccessException {
        final SubmissionTest testSubmissionTest = new SubmissionTest();
        testSubmissionTest.setId(5643L);

        final Field field = testSubmissionTest.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testSubmissionTest), 5643L);
    }


}