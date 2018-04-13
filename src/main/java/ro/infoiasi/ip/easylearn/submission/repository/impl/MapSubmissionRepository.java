package ro.infoiasi.ip.easylearn.submission.repository.impl;

import org.springframework.stereotype.Repository;
import ro.infoiasi.ip.easylearn.submission.model.Submission;
import ro.infoiasi.ip.easylearn.submission.repository.api.SubmissionRepository;

import java.util.*;

@Repository
public class MapSubmissionRepository implements SubmissionRepository{
    private Map<Long, Submission> submissions;

    public MapSubmissionRepository() {
        this.submissions = new HashMap <>();
    }

    @Override
    public void save(Submission submission) {
        submissions.put(submission.id, submission);
    }

    @Override
    public Submission findById(Long id) {
        return submissions.get(id);
    }

    @Override
    public List<Submission> findAll() {
        return new LinkedList <>(submissions.values());
    }

    @Override
    public List <Submission> findByState(String state) {
        List<Submission> filteredSubmissions = new LinkedList <>();

        for(Submission submission : findAll()){
            if(submission.state.equals(state)){
                filteredSubmissions.add(submission);
            }
        }

        return filteredSubmissions;
    }
}
