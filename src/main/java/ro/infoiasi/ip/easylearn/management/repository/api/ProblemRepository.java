package ro.infoiasi.ip.easylearn.management.repository.api;

import java.util.List;

import ro.infoiasi.ip.easylearn.management.model.Problem;

public interface ProblemRepository {
    boolean add(Problem P);
    Long getLastID();
    
	Long save(Problem zp);
	
	Problem findById(Long id);
    List<Problem> findAll();
    List<Problem> findByCategory(int catid);
    List<Problem> findByAuthor(int authorid);
    List<Problem> findSolved(int userID);
    List<Problem> findAttempted(int userID);
}
