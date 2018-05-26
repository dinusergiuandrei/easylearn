package ro.infoiasi.ip.easylearn.management.repository.api;

import java.util.List;

import ro.infoiasi.ip.easylearn.management.model.Problem;

public interface ProblemRepository {
	Long save(Problem problem);
	Problem findById(Long id);
    List<Problem> findAll();
    List<Problem> findByCategory(Long categoryId);
    List<Problem> findByAuthor(Long authorId);
    List<Problem> findSolved(Long userId);
    List<Problem> findAttempted(Long userId);
    List<Problem> getRandom();
}
