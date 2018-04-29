package ro.infoiasi.ip.easylearn.execution;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ro.infoiasi.ip.easylearn.compiler.Compiler;
import ro.infoiasi.ip.easylearn.compiler.CompilerParameters;
import ro.infoiasi.ip.easylearn.compiler.Output;
import ro.infoiasi.ip.easylearn.submission.model.Submission;
import ro.infoiasi.ip.easylearn.submission.repository.api.SubmissionRepository;

import java.io.*;

import static java.lang.Thread.sleep;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Component
public class SubmissionRunner {
    private SubmissionRepository submissionRepository;
    private Compiler compiler;

    public SubmissionRunner(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    @JmsListener(destination = "submissionQueue", containerFactory = "jmsListenerContainerFactory")
    public void run(Long submissionId) throws Exception {
        sleep(1000);
        System.out.println("Running submission with id: " + submissionId);
        Submission submission = submissionRepository.findById(submissionId);
        System.out.println("Running submission: " + submission);


        PrintWriter writer = new PrintWriter("sandbox/sample/sources/java/Main.java");
        writer.write(submission.getSourceCode());

        writer.flush();
        writer.close();

        CompilerParameters compilerParameters =
                new CompilerParameters("sandbox/sample/sources/java/Main.java", "sandbox/sample/generated/java/", "", "", 1000L, MILLISECONDS);

        Compiler compiler = new Compiler();
        Output compileOutput = compiler.compileAndRun(compilerParameters);

        // save to database the result of the compilation
        submission.setState(compileOutput.toString());

    }

}
