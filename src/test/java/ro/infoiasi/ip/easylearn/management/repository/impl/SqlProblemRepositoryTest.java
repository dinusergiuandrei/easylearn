package ro.infoiasi.ip.easylearn.management.repository.impl;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import ro.infoiasi.ip.easylearn.management.model.Problem;
import ro.infoiasi.ip.easylearn.management.repository.api.ProblemRepository;

import javax.transaction.Transactional;

import java.sql.Types;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)

public class SqlProblemRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProblemRepository problemRepository;

    @Ignore
    @Test
    public void findById() {
        Problem myProblem = new Problem(1L,
                1L,
                1L,
                "Eureni",
                "Problema Eureni",
                "Pentru cadourile pe care Moş Crăciun urmează să le cumpere copiilor cuminţi, Consiliul Polului Nord a alocat suma de S eureni. Ştiind că în comerţul polar se utilizează n+1 tipuri de bancnote de valori 1, e1, e2, e3,…, en şi faptul că Moşul trebuie să primească un număr minim de bancnote pentru suma aprobată, să se determine numărul de bancnote din fiecare tip utilizat în plata sumei şi numărul total de bancnote care i s-au alocat.",
                "Fișierul de intrare eureni.in conține pe prima linie numerele S n e.",
                "Pe ultima linie se va scrie numai numărul total de bancnote folosite.",
                "1 < S < 2 000 000 000\n" +
                        "1 < n < 10\n" +
                        "1 < e < 10",
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

        String query = "INSERT INTO problems (id, userId, categoryId, title, description, requirement, input, output, restrictions, difficulty, dataType, inputExample, outputExample, inputFile, outputFile, memoryLimit, timeLimit) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] params = new Object[] {myProblem.getId(), myProblem.getUserId(), myProblem.getCategoryId(), myProblem.getTitle(), myProblem.getDescription(), myProblem.getRequirement(), myProblem.getInput(),
                myProblem.getOutput(), myProblem.getRestrictions(), myProblem.getDifficulty(),
                myProblem.getDataType(), myProblem.getInputExample(), myProblem.getOutputExample(), myProblem.getInputFile(),
                myProblem.getOutputFile(), myProblem.getMemoryLimit(),myProblem.getTimeLimit()};
        int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.DOUBLE };

        int row = jdbcTemplate.update(query, params, types);

        Problem problemFound = problemRepository.findById(myProblem.getId());

        Assert.assertEquals(myProblem.toValuesString(), problemFound.toValuesString());

    }

    @Test
    public void add() {
    }

    @Test
    public void findByCategory() {
    }

    @Test
    public void findByAuthor() {
    }

    @Test
    public void findSolved() {
    }

    @Test
    public void findAttempted() {
    }
}