package ro.infoiasi.ip.easylearn.execution;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ro.infoiasi.ip.easylearn.compiler.Compiler;
import ro.infoiasi.ip.easylearn.compiler.CompilerParameters;
import ro.infoiasi.ip.easylearn.compiler.Output;
import ro.infoiasi.ip.easylearn.submission.model.Run;
import ro.infoiasi.ip.easylearn.submission.model.Submission;
import ro.infoiasi.ip.easylearn.submission.repository.api.RunRepository;
import ro.infoiasi.ip.easylearn.submission.repository.api.SubmissionRepository;
import ro.infoiasi.ip.easylearn.submission.repository.impl.MapRunRepository;
import ro.infoiasi.ip.easylearn.utils.SubmissionState;

import java.io.*;

import static java.lang.Thread.sleep;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Component
public class SubmissionRunner {
    private SubmissionRepository submissionRepository;
    private RunRepository runRepository;

    public SubmissionRunner(SubmissionRepository submissionRepository, RunRepository runRepository) {
        this.submissionRepository = submissionRepository;
        this.runRepository = runRepository;
    }

    @JmsListener(destination = "submissionQueue", containerFactory = "jmsListenerContainerFactory")
    public void run(Long submissionId) throws Exception {
        sleep(1000);
        System.out.println("Running submission with id: " + submissionId);
        Submission submission = submissionRepository.findById(submissionId);
        System.out.println("Running submission: " + submission);

        submission.setState(SubmissionState.Running);

        PrintWriter writer = new PrintWriter("sandbox/sample/sources/java/Main.java");
        writer.write(submission.getSourceCode());

        writer.flush();
        writer.close();

        CompilerParameters compilerParameters =
                new CompilerParameters("sandbox/sample/sources/java/Main.java", "sandbox/sample/generated/java/", "", 1000L, MILLISECONDS);

        Output compileOutput = this.compileAndRun(compilerParameters);

        // TODO: save to database the result of the compilation
        submission.setState(SubmissionState.getSubmissionStateFromOutput(compileOutput));
        submission.setResult(compileOutput.toString());

        // obtain test list, run for each test, obtain info and add them to RunRepository

        Run run = new Run();
        run.setSubmissionId(submission.getId());
        run.setRunTimeMs(10L);
        run.setMemoryBytes(10L);
        run.setStatus("success");

        runRepository.save(run);
    }

    private Output compileAndRun(CompilerParameters compilerParameters){
        Compiler compiler = new Compiler();

        //compiler.getSecurityManager().check ...

        Output compileOutput;
        Output runOutput;

        try {
            compileOutput = compiler.compile(compilerParameters);
        } catch (Exception e) {
            Output errorOutput = new Output();
            errorOutput.setError(e.getMessage());
            return errorOutput;
        }

        if (compileOutput.getExitValue() == 0) {
            try {
                runOutput = compiler.run(compilerParameters);
            } catch (Exception e) {
                Output errorOutput = new Output();
                errorOutput.setError(e.getMessage());
                return errorOutput;
            }
            return runOutput;
        } else {
            return compileOutput;
        }
    }
}
