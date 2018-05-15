package ro.infoiasi.ip.easylearn.execution;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ro.infoiasi.ip.easylearn.compiler.*;
import ro.infoiasi.ip.easylearn.compiler.Compiler;
import ro.infoiasi.ip.easylearn.management.model.ProblemTest;
import ro.infoiasi.ip.easylearn.submission.model.Run;
import ro.infoiasi.ip.easylearn.submission.model.Submission;
import ro.infoiasi.ip.easylearn.submission.repository.api.RunRepository;
import ro.infoiasi.ip.easylearn.submission.repository.api.SubmissionRepository;
import ro.infoiasi.ip.easylearn.utils.RunState;
import ro.infoiasi.ip.easylearn.utils.SubmissionState;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class SubmissionRunner {
    private SubmissionRepository submissionRepository;
    private RunRepository runRepository;
    private Compiler compiler;

    public SubmissionRunner(SubmissionRepository submissionRepository, RunRepository runRepository) {
        this.submissionRepository = submissionRepository;
        this.runRepository = runRepository;
        this.compiler = new SecurityManagerCompiler();
    }

    @JmsListener(destination = "submissionQueue", containerFactory = "jmsListenerContainerFactory")
    public void run(Long submissionId) throws Exception {

        Submission submission = initializeSubmission(submissionId);

        //File rootDirectory = generateRootDirectoryForSubmission(submissionId);

        String rootDirectoryPath = "sandbox/"+submissionId;

        CompilerParameters compilerParameters = new CompilerParameters(
                submission.getLanguage(),
                submission.getSources(),
                rootDirectoryPath
        );

        System.out.println("Compiling...");
        Output compileOutput = compiler.compile(compilerParameters);
        System.out.println("Compiling completed.");

        if (compiledWithSuccess(compileOutput)) {
//            Problem problem = ... . findById(submission.getProblemId());
//            List<ProblemTest> tests = problemTestRepository.findById(submission.getProblemId());
            ProblemTest problemTest = new ProblemTest(1L, 1L, "", "Hello World!\n");
            ProblemTest problemTest2 = new ProblemTest(1L, 1L, "", "Hello World not good");
            List<ProblemTest> tests = Arrays.asList(problemTest, problemTest2);

            for (ProblemTest test : tests) {
                Run run = new Run();
                run.setSubmissionId(submissionId);
                run.setRunTimeMs(10L); // problem.getRuntime();
                run.setMemoryBytes(10L); // problem.getMaxMemory();

                RunParameters runParameters = new RunParameters(test.getInput(), run.getRunTimeMs(), TimeUnit.MILLISECONDS);
                Output runOutput = compiler.run(submission.getMainSource(), compilerParameters, runParameters);

                if ( runWithSuccess(runOutput) && test.isValidOutput(runOutput.getOutput())) {
                    run.setStatus(RunState.Success);
                } else {
                    run.setStatus(RunState.Failed);
                }
                runRepository.save(run);
            }

            submission.setState(SubmissionState.Completed);
        } else {
            submission.setState(SubmissionState.CompilationFailed);
        }
    }

    private boolean runWithSuccess(Output runOutput) {
        return runOutput.getExitValue() == 0;
    }

    private boolean compiledWithSuccess(Output compileOutput) {
        return compileOutput.getExitValue() == 0;
    }

    private Submission initializeSubmission(Long submissionId) {
        System.out.println("Running submission with id: " + submissionId);
        Submission submission = submissionRepository.findById(submissionId);
        submission.setState(SubmissionState.Running);
        System.out.println("Running submission: " + submission);
        return submission;
    }

    private File generateRootDirectoryForSubmission(Long submissionId) throws IOException {
        File rootDirectory = new File("sandbox/" + submissionId);
        if (!rootDirectory.mkdir()) {
            throw new IOException("Could not create root directory for run of submission: " + submissionId);
        }
        return rootDirectory;
    }
}
