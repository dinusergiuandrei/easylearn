package ro.infoiasi.ip.easylearn.submission.repository.impl;

import org.springframework.stereotype.Repository;
import ro.infoiasi.ip.easylearn.submission.model.Submission;
import ro.infoiasi.ip.easylearn.submission.repository.api.SubmissionRepository;
import ro.infoiasi.ip.easylearn.utils.RunState;

import java.util.*;

//@Repository
public class MapSubmissionRepository implements SubmissionRepository{
    private Map<Long, Submission> submissions;
    private Long id = 1L;

    public MapSubmissionRepository() {
        this.submissions = new HashMap <>();
    }

    @Override
    public Long save(Submission submission) {
        submission.setId(this.id++);
        submissions.put(submission.getId(), submission);

        return submission.getId();
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
    public List <Submission> findByState(RunState state) {
        List<Submission> filteredSubmissions = new LinkedList <>();

        for(Submission submission : findAll()){
            if(submission.getState().equals(state)){
                filteredSubmissions.add(submission);
            }
        }

        return filteredSubmissions;
    }

    @Override
    public Long update(Submission submission) {
        return null;
    }
}
