package ro.infoiasi.ip.easylearn.management.controller;

import javafx.application.Application;
import org.junit.Assert;
import org.junit.Test;
import ro.infoiasi.ip.easylearn.configuration.SQLConfiguration;
import ro.infoiasi.ip.easylearn.management.model.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ro.infoiasi.ip.easylearn.management.model.Problem;
import ro.infoiasi.ip.easylearn.management.repository.api.ProblemRepository;

import javax.transaction.Transactional;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SQLConfiguration.class})
@Transactional
@Rollback
public class ProblemControllerTest {

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private ProblemController problemControllerToTest = new ProblemController(problemRepository);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /*
     * The testGetProblemByValidId tests if the method getByProblemId from the ProblemController class returns the correct Problem object for valid given id.
     */
    @Test
    public void testGetProblemByValidId() {
        Problem myProblem = new Problem(problemRepository.getLastID()+1,
                "1",
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
        int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER,
                                Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.DOUBLE };

        int row = jdbcTemplate.update(query, params, types);


        Problem problemGotById = new Problem();
        problemGotById = problemControllerToTest.getProblemById(myProblem.getProblemID());

        Assert.assertEquals(myProblem.toValuesString(),problemGotById.toValuesString());
    }


    /*
     * The testGetProblemByInvalidId tests if the method getByProblemId from the ProblemController class returns the correct Problem object for invalid given id.
     */
    @Test
    public void testGetProblemByInvalidId(){
        Random rand = new Random();

        int  offset = rand.nextInt(150) + 1;
        long newId = problemRepository.getLastID() + offset;

        Problem problemGotById = new Problem();
        problemGotById = problemControllerToTest.getProblemById(newId);

        Assert.assertEquals(new Problem().toValuesString(), problemGotById.toValuesString());
    }

    /*
     * The testGetProblemsByValidCategory tests if the method getProblemsByCategory from the ProblemController class returns correctly
     * the items from the database. To ensure that all the objects are taken correctly from the DB, we create a new category, add a few
     * problems of that category, and check if the method returns those exact items.
     */
    @Test
    public void testGetProblemsByValidCategory() {

    }

    @Test
    public void testGetProblemsByInvalidCategory() {
    }

    @Test
    public void testGetProblemsByValidAuthor() {
    }

    @Test
    public void testGetProblemsByInvalidAuthor() {
    }

    @Test
    public void getSolvedProblems() {
    }

    @Test
    public void getAttemptedProblems() {
    }

    @Test
    public void addProblem() {
    }
}