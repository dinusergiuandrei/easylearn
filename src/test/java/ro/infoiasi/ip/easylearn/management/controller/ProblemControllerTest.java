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
        Problem testProblem = createTestProblemFor();

        insertProblemInDB(testProblem);

        Problem problemGotById = new Problem();
        problemGotById = problemControllerToTest.getProblemById(testProblem.getProblemID());

        Assert.assertEquals(testProblem.toValuesString(),problemGotById.toValuesString());
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
        Category category = createAndInsertNewCategory(10, "hardcore");

        Problem testProblem1 = createTestProblemFor(category.getCategoryId());
        Problem testProblem2 = createTestProblemFor(category.getCategoryId());
        Problem testProblem3 = createTestProblemFor(category.getCategoryId());

        String expectedString = testProblem1.toValuesString() + testProblem2.toValuesString() + testProblem3.toValuesString();

        insertProblemInDB(testProblem1);
        insertProblemInDB(testProblem2);
        insertProblemInDB(testProblem3);

        List<Problem> methodRersult = problemControllerToTest.getProblemsByCategory(category.getCategoryId());

        String actualString = "";
        for(Problem problem : methodRersult){
            actualString += problem.toValuesString();
        }

        Assert.assertEquals(expectedString, actualString);
    }

    /**
     * The testGetProblemsByInvalidCategory tests if the method getProblemsByCategory from the ProblemController class returns correctly the
     * items from the database. We create an inexistent category and call the method. If it returns no items, the test is declared succesful.
     */
    @Test
    public void testGetProblemsByInvalidCategory() {
        Category category = new Category(10,"hardcore");

        List<Problem> methodRersult = problemControllerToTest.getProblemsByCategory(category.getCategoryId());
        Assert.assertEquals(0, methodRersult.size());
    }

    /**
     * The testGetProblemsByValidAuthor method tests if the method getProblemsByAuthor from the ProblemController class returns correctly the
     * items from the database. To ensure that all the objects are taken correctly from the DB, we create a new authorID, add a few problems
     * of that author, and then check if the method returns those exact items.
     */
    @Test
    public void testGetProblemsByValidAuthor() {
        String authorId = "thisIsAnAuthorId";
        Problem testProblem1 = createTestProblemFor(authorId);
        Problem testProblem2 = createTestProblemFor(authorId);
        Problem testProblem3 = createTestProblemFor(authorId);

        String expectedResult = testProblem1.toValuesString() + testProblem2.toValuesString() + testProblem3.toValuesString();

        insertProblemInDB(testProblem1);
        insertProblemInDB(testProblem2);
        insertProblemInDB(testProblem3);

        List<Problem> methodResult = problemControllerToTest.getProblemsByAuthor(authorId);
        String actualResult = "";
        for(Problem problem : methodResult){
            actualResult = problem.toValuesString();
        }

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetProblemsByInvalidAuthor() {
        String invalidAuthorId = "thiscantbevalid193ecsa54";
        List<Problem> methodResult = problemControllerToTest.getProblemsByAuthor(invalidAuthorId);

        Assert.assertEquals(0, methodResult.size());
    }

    @Test
    public void getSolvedProblems() {
    }

    @Test
    public void getAttemptedProblems() {
    }

    @Test
    public void test_addProblem() {
        Problem testProblem = createTestProblemFor();
        problemControllerToTest.addProblem(testProblem);

        Problem actualProblem = problemControllerToTest.getProblemById(testProblem.getProblemID());

        Assert.assertEquals(testProblem.toValuesString(), actualProblem.toValuesString());
    }



    private Problem createTestProblemFor(String authorID){
        return new Problem(problemRepository.getLastID()+1,
                authorID,
                "Eureni2",
                "Problema Eureni2",
                "Pentru cadourile pe care Moş Crăciun urmează să le cumpere copiilor cuminţi, Consiliul Polului Nord a alocat suma de S eureni. Ştiind că în comerţul polar se utilizează n+1 tipuri de bancnote de valori 1, e1, e2, e3,…, en şi faptul că Moşul trebuie să primească un număr minim de bancnote pentru suma aprobată, să se determine numărul de bancnote din fiecare tip utilizat în plata sumei şi numărul total de bancnote care i s-au alocat.",
                "Fișierul de intrare eureni.in conține pe prima linie numerele S n e.",
                "Pe ultima linie se va scrie numai numărul total de bancnote folosite.",
                "1 < S < 2 000 000\n" +
                        "1 < n < 10\n",
                1,
                10,
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
    }

    private Problem createTestProblemFor(long categoryID){
        return new Problem(problemRepository.getLastID()+1,
                "1",
                "Eureni2",
                "Problema Eureni2",
                "Pentru cadourile pe care Moş Crăciun urmează să le cumpere copiilor cuminţi, Consiliul Polului Nord a alocat suma de S eureni. Ştiind că în comerţul polar se utilizează n+1 tipuri de bancnote de valori 1, e1, e2, e3,…, en şi faptul că Moşul trebuie să primească un număr minim de bancnote pentru suma aprobată, să se determine numărul de bancnote din fiecare tip utilizat în plata sumei şi numărul total de bancnote care i s-au alocat.",
                "Fișierul de intrare eureni.in conține pe prima linie numerele S n e.",
                "Pe ultima linie se va scrie numai numărul total de bancnote folosite.",
                "1 < S < 2 000 000\n" +
                        "1 < n < 10\n",
                1,
                10,
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
    }


    private Problem createTestProblemFor(){
        return new Problem(problemRepository.getLastID()+1,
                "1",
                "Eureni2",
                "Problema Eureni2",
                "Pentru cadourile pe care Moş Crăciun urmează să le cumpere copiilor cuminţi, Consiliul Polului Nord a alocat suma de S eureni. Ştiind că în comerţul polar se utilizează n+1 tipuri de bancnote de valori 1, e1, e2, e3,…, en şi faptul că Moşul trebuie să primească un număr minim de bancnote pentru suma aprobată, să se determine numărul de bancnote din fiecare tip utilizat în plata sumei şi numărul total de bancnote care i s-au alocat.",
                "Fișierul de intrare eureni.in conține pe prima linie numerele S n e.",
                "Pe ultima linie se va scrie numai numărul total de bancnote folosite.",
                "1 < S < 2 000 000\n" +
                        "1 < n < 10\n",
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
    }

    private void insertProblemInDB(Problem pb){
        String query = "INSERT INTO probleme (problemID, authorID, titlu, descriere, cerinta, date_intrare, date_iesire, restrictii, dificultate, categorie, tip_date, exemplu_intrare, exemplu_iesire, input_file, output_file, max_memory, max_time) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] params = new Object[] {pb.getProblemID(), pb.getAuthorID(), pb.getTitlu(), pb.getDescriere(), pb.getCerinta(), pb.getDate_intrare(),
                pb.getDate_iesire(), pb.getRestrictii(), pb.getDificultate(), pb.getCategorie(),
                pb.getTip_date(), pb.getExemplu_intrare(), pb.getExemplu_iesire(), pb.getInput_file(),
                pb.getOutput_file(), pb.getMax_memory(),pb.getMax_time()};
        int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER,
                Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.DOUBLE };

        int row = jdbcTemplate.update(query, params, types);
    }

    private Category createAndInsertNewCategory(long categoryID, String categoryName){
        Category category = new Category(categoryID, categoryName);

        String catQuery = "INSERT INTO categorii(categoryID, nume) VALUES(?,?)";
        Object catParams = new Object[]{category.getCategoryId(), category.getNume()};
        int[] catTypes = new int[]{Types.INTEGER, Types.VARCHAR};

        int catRow = jdbcTemplate.update(catQuery, catParams, catTypes);

        return category;
    }
}