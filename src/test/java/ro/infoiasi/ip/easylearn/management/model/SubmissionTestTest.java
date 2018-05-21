package ro.infoiasi.ip.easylearn.management.model;

import org.junit.Test;
import ro.infoiasi.ip.easylearn.submission.model.Submission;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class SubmissionTestTest {

    @Test
    public void testSetter_setSubmissionID() throws NoSuchFieldException, IllegalAccessException {
        final SubmissionTest testSubmissionTest = new SubmissionTest();
        testSubmissionTest.setSubmissionID(123056);

        final Field field = testSubmissionTest.getClass().getDeclaredField("submissionID");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testSubmissionTest), (long)123056);
    }

    @Test
    public void testSetter_setTestID() throws NoSuchFieldException, IllegalAccessException {
        final SubmissionTest testSubmissionTest = new SubmissionTest();
        testSubmissionTest.setTestID(5642);

        final Field field = testSubmissionTest.getClass().getDeclaredField("testID");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(testSubmissionTest), (long)5642);
    }


}