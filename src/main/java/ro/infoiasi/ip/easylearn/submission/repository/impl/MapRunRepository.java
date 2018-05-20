package ro.infoiasi.ip.easylearn.submission.repository.impl;

import org.springframework.stereotype.Repository;
import ro.infoiasi.ip.easylearn.submission.model.Run;
import ro.infoiasi.ip.easylearn.submission.repository.api.RunRepository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//@Repository
public class MapRunRepository implements RunRepository {
    private Map <Long, List <Run>> runs;
    private Long id;

    public MapRunRepository() {
        runs = new HashMap <>();
        id = 1L;
    }

    @Override
    public void save(Run run) {
        run.setId(this.id++);

        List <Run> submissionRuns = findBySubmissionId(run.getSubmissionId());
        submissionRuns.add(run);
        runs.put(run.getSubmissionId(), submissionRuns);
    }

    @Override
    public List <Run> findBySubmissionId(Long submissionId) {
        return runs.getOrDefault(submissionId, new LinkedList <>());
    }
}
