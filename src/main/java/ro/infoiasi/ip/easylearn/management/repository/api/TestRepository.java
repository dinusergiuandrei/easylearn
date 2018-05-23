package ro.infoiasi.ip.easylearn.management.repository.api;

import ro.infoiasi.ip.easylearn.management.model.ProblemTest;

import java.util.List;

public interface TestRepository {
    Long getLastId();
    Long save(ProblemTest submission);
    ProblemTest findById(Long id);
    List<ProblemTest> findAll();
    List<ProblemTest> findAllForProblem(int problemID);
}
