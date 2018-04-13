package ro.infoiasi.ip.easylearn.submission.service;

import org.springframework.stereotype.Service;
import ro.infoiasi.ip.easylearn.submission.model.Submission;
import ro.infoiasi.ip.easylearn.submission.model.SubmissionResponse;


@Service
public class SubmissionService {
    public SubmissionResponse submit(Submission submission){
//        proceseaza submisia
        SubmissionResponse response = new SubmissionResponse();
        response.result = "Succesfull processing";

        return response;
    }

}
