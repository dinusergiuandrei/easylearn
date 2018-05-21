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

import javax.transaction.Transactional;
import java.sql.Types;
import java.util.Random;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)

public class ProblemTestTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private TestController testController = new TestController(testRepository);


    @Test
    public void setId() {
        ProblemTest myTestProblem = new ProblemTest();
        Random rand = new Random();

        int offset = rand.nextInt(150) + 1;

        long newID = offset + testRepository.getLastID();
        myTestProblem.setId(newID);

        long id = myTestProblem.getId();
        Assert.assertEquals(id, newID);
    }
    @Test
    public void setProblemId() {
        ProblemTest myTestProblem = new ProblemTest();
        Random rand = new Random();

        long problemId = rand.nextInt(150) + 1;
        myTestProblem.setProblemId(problemId);
        long id = myTestProblem.getProblemId();

        Assert.assertEquals(id, problemId);
    }

    @Test
    public void setInput() {
        ProblemTest myTestProblem = new ProblemTest();


        String input = "String Random pentru test";
        myTestProblem.setInput(input);

        String getinput = myTestProblem.getInput();
        Assert.assertEquals(input, getinput);

    }

    @Test
    public void setExpectedOutput() {
        ProblemTest myTestProblem = new ProblemTest();
        String exoutput = "Expected Output123";
        myTestProblem.setExpectedOutput(exoutput);

        String output = myTestProblem.getExpectedOutput();

        Assert.assertEquals(exoutput, output);
    }

    @Test
    public void getId(){
        Problem myProblem = new Problem(problemRepository.getLastID()+1,
                1,
                "Eureni",
                "Problema Eureni",
                "Pentru cadourile pe care Moş Crăciun urmează să le cumpere copiilor cuminţi, Consiliul Polului Nord a alocat suma de S eureni. Ştiind că în comerţul polar se utilizează n+1 tipuri de bancnote de valori 1, e1, e2, e3,…, en şi faptul că Moşul trebuie să primească un număr minim de bancnote pentru suma aprobată, să se determine numărul de bancnote din fiecare tip utilizat în plata sumei şi numărul total de bancnote care i s-au alocat.",
                "Fișierul de intrare eureni.in conține pe prima linie numerele S n e.",
                "Pe ultima linie se va scrie numai numărul total de bancnote folosite.",
                "1 < S < 2 000 000 000\n" +
                        "1 < n < 10\n" +
                        "1 < e < 10",
                1,
                1,
                "fisier",
                "107 4 5",
                "25 4\n" +
                        "5 1\n" +
                        "1 2\n" +
                        "7\n",
                "eureni.in",
                "eureni.out",
                1,
                1);

        String query = "INSERT INTO probleme (problemID, authorID, titlu, descriere, cerinta, date_intrare, date_iesire, restrictii, dificultate, categorie, tip_date, exemplu_intrare, exemplu_iesire, input_file, output_file, max_memory, max_time) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] params = new Object[] {myProblem.getProblemID(), myProblem.getAuthorID(), myProblem.getTitlu(), myProblem.getDescriere(), myProblem.getCerinta(), myProblem.getDate_intrare(),
                myProblem.getDate_iesire(), myProblem.getRestrictii(), myProblem.getDificultate(), myProblem.getCategorie(),
                myProblem.getTip_date(), myProblem.getExemplu_intrare(), myProblem.getExemplu_iesire(), myProblem.getInput_file(),
                myProblem.getOutput_file(), myProblem.getMax_memory(),myProblem.getMax_time()};
        int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR,Types.INTEGER,
                Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.INTEGER, Types.DOUBLE };

        int row = jdbcTemplate.update(query, params, types);



        long id = 10;
        long problemId = myProblem.getProblemID();
        String output = "Output12345";
        String expOutput = "Expected Output";
        ProblemTest myTestProblem = new ProblemTest(id, problemId, output, expOutput);

        String testequery = "INSERT INTO teste(testID, problemID, input, expected_output) values(?,?,?,?);";
        Object[] testeparams = new Object[]{myTestProblem.getId(), myTestProblem.getProblemId(), myTestProblem.getInput(), myTestProblem.getExpectedOutput()};
        int[] testetypes = new int[]{Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.VARCHAR};
        int testerow = jdbcTemplate.update(testequery, testeparams, testetypes);

        ProblemTest problemTestById = new ProblemTest();
        problemTestById = testController.tests(myTestProblem.getId());
        problemTestById.setId(myTestProblem.getId());

        Assert.assertEquals(myTestProblem.getId() + myTestProblem.getProblemId() + myTestProblem.getInput() +
                myTestProblem.getExpectedOutput(), problemTestById.getId() + problemTestById.getProblemId()+
                problemTestById.getInput() +
                problemTestById.getExpectedOutput());

    }
}