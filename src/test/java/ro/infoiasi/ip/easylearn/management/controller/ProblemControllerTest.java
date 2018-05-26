package ro.infoiasi.ip.easylearn.management.controller;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import ro.infoiasi.ip.easylearn.configuration.SQLConfiguration;
import ro.infoiasi.ip.easylearn.management.model.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import ro.infoiasi.ip.easylearn.management.model.Problem;
import ro.infoiasi.ip.easylearn.management.model.ProblemResponse;
import ro.infoiasi.ip.easylearn.management.repository.api.ProblemRepository;

import javax.transaction.Transactional;

import java.sql.Types;
import java.util.List;
import java.util.Random;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SQLConfiguration.class})
@Transactional
@Rollback
public class ProblemControllerTest {

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private ProblemController problemController = new ProblemController(problemRepository);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /*
     * The testGetProblemByValidId tests if the method getByProblemId from the ProblemController class returns the correct Problem object for valid given id.
     */
    @Ignore
    @Test
    public void testGetProblemByValidId() {
        Problem problem = createTestProblemFor();

        insertProblemInDB(problem);

        Problem problemGotById = problemController.getProblemById(problem.getId());

        Assert.assertEquals(problem.toValuesString(),problemGotById.toValuesString());
    }


    /*
     * The testGetProblemByInvalidId tests if the method getByProblemId from the ProblemController class returns the correct Problem object for invalid given id.
     */
    @Test
    public void testGetProblemByInvalidId(){
        Problem expected = problemController.getProblemById(1L);
        Problem actual = problemRepository.findById(1L);

        Assert.assertNotEquals(expected, actual);
    }

    /*
     * The testGetProblemsByValidCategory tests if the method getProblemsByCategory from the ProblemController class returns correctly
     * the items from the database. To ensure that all the objects are taken correctly from the DB, we create a new category, add a few
     * problems of that category, and check if the method returns those exact items.
     */
    @Ignore
    @Test
    public void testGetProblemsByValidCategory() {
        Category category = createAndInsertNewCategory(10L, "hardcore");

        Problem testProblem1 = createTestProblemFor(category.getId());
        Problem testProblem2 = createTestProblemFor(category.getId());
        Problem testProblem3 = createTestProblemFor(category.getId());

        String expectedString = testProblem1.toValuesString() + testProblem2.toValuesString() + testProblem3.toValuesString();

        insertProblemInDB(testProblem1);
        insertProblemInDB(testProblem2);
        insertProblemInDB(testProblem3);

        List<Problem> methodResult = problemController.getProblemsByCategory(category.getId());

        String actualString = "";
        for(Problem problem : methodResult){
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
        Category category = new Category(10L,"hardcore");

        List<Problem> methodResult = problemController.getProblemsByCategory(category.getId());
        int actualSize = -1;
        if(methodResult == null){
            actualSize = 0;
        }
        Assert.assertEquals(0, actualSize);
    }

    /**
     * The testGetProblemsByValidAuthor method tests if the method getProblemsByAuthor from the ProblemController class returns correctly the
     * items from the database. To ensure that all the objects are taken correctly from the DB, we create a new authorID, add a few problems
     * of that author, and then check if the method returns those exact items.
     */
    @Ignore
    @Test
    public void testGetProblemsByUserId() {
        Long userId = 1L;
        Problem testProblem1 = createTestProblemFor(userId);
        Problem testProblem2 = createTestProblemFor(userId);
        Problem testProblem3 = createTestProblemFor(userId);

        String expectedResult = testProblem1.toValuesString() + testProblem2.toValuesString() + testProblem3.toValuesString();

        insertProblemInDB(testProblem1);
        insertProblemInDB(testProblem2);
        insertProblemInDB(testProblem3);

        List<Problem> problems = problemController.getProblemsByAuthor(userId);
        String actualResult = "";
        for(Problem problem : problems){
            actualResult = problem.toValuesString();
        }

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetProblemsByInvalidAuthor() {
        Long invalidAuthorId = 0L;
        List<Problem> methodResult = problemController.getProblemsByAuthor(invalidAuthorId);
        int actualSize = -1;
        if (methodResult == null){
            actualSize = 0;
        }
        Assert.assertEquals(0, actualSize);
    }

    @Test
    public void getSolvedProblems() {
    }

    @Test
    public void getAttemptedProblems() {
    }

    @Test
    public void test_addProblem() {
        Problem problem = createTestProblemFor();
        ProblemResponse problemResponse = problemController.addProblem(problem);

        Problem actualProblem = problemController.getProblemById(problemResponse.getId());
        problem.setId(problemResponse.getId());

        Assert.assertEquals(problem.toValuesString(), actualProblem.toValuesString());
    }



    private Problem createTestProblemFor(Long authorId){
        return new Problem(
                1L,
                authorId,
                1L,
                "Eureni2",
                "Problema Eureni2",
                "Pentru cadourile pe care Moş Crăciun urmează să le cumpere copiilor cuminţi, Consiliul Polului Nord a alocat suma de S eureni. Ştiind că în comerţul polar se utilizează n+1 tipuri de bancnote de valori 1, e1, e2, e3,…, en şi faptul că Moşul trebuie să primească un număr minim de bancnote pentru suma aprobată, să se determine numărul de bancnote din fiecare tip utilizat în plata sumei şi numărul total de bancnote care i s-au alocat.",
                "Fișierul de intrare eureni.in conține pe prima linie numerele S n e.",
                "Pe ultima linie se va scrie numai numărul total de bancnote folosite.",
                "1 < S < 2 000 000\n" +
                        "1 < n < 10\n",
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
                1L);
    }

    private Problem createTestProblemFor(long categoryId){
        return new Problem(
                1L,
                1L,
                1L,
                "Eureni2",
                "Problema Eureni2",
                "Pentru cadourile pe care Moş Crăciun urmează să le cumpere copiilor cuminţi, Consiliul Polului Nord a alocat suma de S eureni. Ştiind că în comerţul polar se utilizează n+1 tipuri de bancnote de valori 1, e1, e2, e3,…, en şi faptul că Moşul trebuie să primească un număr minim de bancnote pentru suma aprobată, să se determine numărul de bancnote din fiecare tip utilizat în plata sumei şi numărul total de bancnote care i s-au alocat.",
                "Fișierul de intrare eureni.in conține pe prima linie numerele S n e.",
                "Pe ultima linie se va scrie numai numărul total de bancnote folosite.",
                "1 < S < 2 000 000\n" +
                        "1 < n < 10\n",
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
                1L);
    }


    private Problem createTestProblemFor(){
        return new Problem(
                1L,
                1L,
                1L,
                "Eureni2",
                "Problema Eureni2",
                "Pentru cadourile pe care Moş Crăciun urmează să le cumpere copiilor cuminţi, Consiliul Polului Nord a alocat suma de S eureni. Ştiind că în comerţul polar se utilizează n+1 tipuri de bancnote de valori 1, e1, e2, e3,…, en şi faptul că Moşul trebuie să primească un număr minim de bancnote pentru suma aprobată, să se determine numărul de bancnote din fiecare tip utilizat în plata sumei şi numărul total de bancnote care i s-au alocat.",
                "Fișierul de intrare eureni.in conține pe prima linie numerele S n e.",
                "Pe ultima linie se va scrie numai numărul total de bancnote folosite.",
                "1 < S < 2 000 000\n" +
                        "1 < n < 10\n",
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
                1L);
    }

    private void insertProblemInDB(Problem problem){
        String query = "INSERT INTO problems (id, userId, categoryId,  title, description, requirement, input, output, restrictions, difficulty, dataType, inputExample, outputExample, inputFile, outputFile, memoryLimit, timeLimit) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] params = new Object[] {problem.getId(), problem.getUserId(), problem.getCategoryId(), problem.getTitle(), problem.getDescription(), problem.getRequirement(), problem.getInput(),
                problem.getOutput(), problem.getRestrictions(), problem.getDifficulty(),
                problem.getDataType(), problem.getInputExample(), problem.getOutputExample(), problem.getInputFile(),
                problem.getOutputFile(), problem.getMemoryLimit(),problem.getTimeLimit()};
        int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.DOUBLE };

        int row = jdbcTemplate.update(query, params, types);
    }

    private Category createAndInsertNewCategory(Long categoryID, String categoryName){
        Category category = new Category(categoryID, categoryName);

        String catQuery = "INSERT INTO categories(id, name) VALUES(?,?)";
        Object catParams = new Object[]{category.getId(), category.getName()};
        int[] catTypes = new int[]{Types.INTEGER, Types.VARCHAR};

        int catRow = jdbcTemplate.update(catQuery, catParams, catTypes);

        return category;
    }
}