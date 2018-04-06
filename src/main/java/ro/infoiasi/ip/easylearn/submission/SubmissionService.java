package ro.infoiasi.ip.easylearn.submission;

import org.springframework.stereotype.Service;


@Service
public class SubmissionService {
    public SubmissionResponse submit(Submission submission){
//        proceseaza submisia
        SubmissionResponse response = new SubmissionResponse();
        response.result = "Succesfull processing";

        return response;
    }

}
