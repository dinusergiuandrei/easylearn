package ro.infoiasi.ip.easylearn.management.repository.api;

import java.util.List;

import ro.infoiasi.ip.easylearn.management.model.Problem;

public interface ProblemRepository {
	Long save(Problem submission);
	Problem findById(Long id);
    List<Problem> findAll();
}
