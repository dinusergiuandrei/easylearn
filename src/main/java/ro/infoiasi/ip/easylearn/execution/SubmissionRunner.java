package ro.infoiasi.ip.easylearn.execution;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ro.infoiasi.ip.easylearn.submission.repository.api.SubmissionRepository;

import static java.lang.Thread.sleep;

@Component
public class SubmissionRunner {
    private SubmissionRepository submissionRepository;

    public SubmissionRunner(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    @JmsListener(destination = "submissionQueue", containerFactory = "jmsListenerContainerFactory")
    public void run(Long submissionId) throws InterruptedException {
        sleep(1000);
        System.out.println("Running submission with id: " + submissionId);
        System.out.println("Running submission: " + submissionRepository.findById(submissionId));
    }
}
