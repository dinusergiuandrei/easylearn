package ro.infoiasi.ip.easylearn.management.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import ro.infoiasi.ip.easylearn.management.model.Category;
import ro.infoiasi.ip.easylearn.management.model.Problem;
import ro.infoiasi.ip.easylearn.management.repository.api.ProblemRepository;

import javax.transaction.Transactional;

import java.sql.Types;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)

public class SqlProblemRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProblemRepository problemRepository;

    @Test
    public void test_findById() {
        Problem testProblem = createTestProblemFor();

        insertProblemInDB(testProblem);
        Problem problemFound = new Problem();
        problemFound = problemRepository.findById(testProblem.getProblemID());

        Assert.assertEquals("Fields don't match", testProblem.toValuesString(), problemFound.toValuesString());
    }

    @Test
    public void test_add() {
        String newAuthorId = "thisIsANewAuthor";
        Problem expectedProblem = createTestProblemFor(newAuthorId);

        problemRepository.add(expectedProblem);

        String query = "SELECT * FROM probleme WHERE authorID LIKE \"" + newAuthorId + "\"";
        Problem actualProblem = jdbcTemplate.queryForObject(query, Problem.class);

        Assert.assertEquals(expectedProblem.toValuesString(), actualProblem.toValuesString());
    }

    @Test
    public void test_findByCategory() {
        long categoryId = 120;
        createAndInsertNewCategory(categoryId, "hardcore");
        Problem expectedProblem = createTestProblemFor(categoryId);
        insertProblemInDB(expectedProblem);
        Problem actualProblem = problemRepository.findById(categoryId);

        Assert.assertEquals(expectedProblem.toValuesString(),actualProblem.toValuesString());
    }

    @Test
    public void test_findByAuthor() {
        String newAuthorId = "thisIsANewAuthorId";
        Problem expectedProblem1 = createTestProblemFor(newAuthorId);
        insertProblemInDB(expectedProblem1);
        Problem expectedProblem2 = createTestProblemFor(newAuthorId);
        insertProblemInDB(expectedProblem2);

        String expectedResult = expectedProblem1.toValuesString() + expectedProblem2.toValuesString();

        List<Problem> actualProblems = problemRepository.findByAuthor(newAuthorId);
        String actualResult = "";
        for(Problem problem : actualProblems){
            actualResult += problem.toValuesString();
        }

        Assert.assertEquals(expectedResult,actualResult);
    }

    @Test
    public void findSolved() {
    }

    @Test
    public void findAttempted() {
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
                categoryID,
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
        Object[] catParams = new Object[]{category.getCategoryId(), category.getNume()};
        int[] catTypes = new int[]{Types.INTEGER, Types.VARCHAR};

        int catRow = jdbcTemplate.update(catQuery, catParams, catTypes);

        return category;
    }

}