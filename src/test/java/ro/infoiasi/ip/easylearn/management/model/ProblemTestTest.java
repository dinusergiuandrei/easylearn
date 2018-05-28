package ro.infoiasi.ip.easylearn.management.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import ro.infoiasi.ip.easylearn.management.controller.TestController;
import ro.infoiasi.ip.easylearn.management.repository.api.ProblemRepository;
import ro.infoiasi.ip.easylearn.management.repository.api.TestRepository;
import ro.infoiasi.ip.easylearn.user.repository.api.SessionRepository;

import javax.transaction.Transactional;
import java.sql.Types;
import java.util.Random;

import static org.junit.Assert.*;

public class ProblemTestTest {

    @Test
    public void testSetter_setInput() {
        ProblemTest myTestProblem = new ProblemTest();
        String input = "String Random pentru test";
        myTestProblem.setInput(input);

        String getinput = myTestProblem.getInput();
        Assert.assertEquals(input, getinput);

    }

    @Test
    public void testSetter_setExpectedOutput() {
        ProblemTest myTestProblem = new ProblemTest();
        String exoutput = "Expected Output123";
        myTestProblem.setExpectedOutput(exoutput);

        String output = myTestProblem.getExpectedOutput();
        Assert.assertEquals(exoutput, output);
    }

    @Test
    public void testGetter_getId(){
        ProblemTest myProBlemTest = new ProblemTest();
        Random rand = new Random();
        long id = rand.nextInt(150) + 1;

        myProBlemTest.setId(id);
        final long getid = myProBlemTest.getId();
        Assert.assertEquals(id, getid);
    }

    @Test
    public void testGetter_getProblemID(){
        ProblemTest myProBlemTest = new ProblemTest();
        Random rand = new Random();
        long id = rand.nextInt(150) + 1;

        myProBlemTest.setProblemId(id);
        final long getid = myProBlemTest.getProblemId();
        Assert.assertEquals(id, getid);
    }

    @Test
    public void testGetter_getInput(){
        ProblemTest myProBlemTest = new ProblemTest();
        String input = "Input0123";
        myProBlemTest.setInput(input);

        final String getinput = myProBlemTest.getInput();
        Assert.assertEquals(input, getinput);
    }

    @Test
    public void testGetter_getExpectedOutput(){
        ProblemTest myProBlemTest = new ProblemTest();
        String output = "Expected Output1230";
        myProBlemTest.setExpectedOutput(output);

        final String getoutput = myProBlemTest.getExpectedOutput();
        Assert.assertEquals(output, getoutput);
    }
}