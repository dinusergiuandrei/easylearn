package ro.infoiasi.ip.easylearn.execution;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ro.infoiasi.ip.easylearn.compiler.*;
import ro.infoiasi.ip.easylearn.compiler.Compiler;
import ro.infoiasi.ip.easylearn.management.model.Problem;
import ro.infoiasi.ip.easylearn.management.model.ProblemTest;
import ro.infoiasi.ip.easylearn.management.repository.api.ProblemRepository;
import ro.infoiasi.ip.easylearn.management.repository.api.TestRepository;
import ro.infoiasi.ip.easylearn.submission.model.Run;
import ro.infoiasi.ip.easylearn.submission.model.Submission;
import ro.infoiasi.ip.easylearn.submission.repository.api.RunRepository;
import ro.infoiasi.ip.easylearn.submission.repository.api.SourceFilesRepository;
import ro.infoiasi.ip.easylearn.submission.repository.api.SubmissionRepository;
import ro.infoiasi.ip.easylearn.utils.RunState;
import ro.infoiasi.ip.easylearn.utils.SubmissionState;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class SubmissionRunner {
    private SubmissionRepository submissionRepository;
    private RunRepository runRepository;
    private ProblemRepository problemRepository;
    private TestRepository testRepository;
    private SourceFilesRepository sourceFilesRepository;
    private Compiler compiler;

    public SubmissionRunner(SubmissionRepository submissionRepository, RunRepository runRepository, ProblemRepository problemRepository, TestRepository testRepository, SourceFilesRepository sourceFilesRepository) {
        this.submissionRepository = submissionRepository;
        this.runRepository = runRepository;
        this.problemRepository = problemRepository;
        this.testRepository = testRepository;
        this.sourceFilesRepository = sourceFilesRepository;
        this.compiler = new SecurityManagerCompiler();
    }

    @JmsListener(destination = "submissionQueue", containerFactory = "jmsListenerContainerFactory")
    public void run(Long submissionId) throws Exception {
        Submission submission = initializeSubmission(submissionId);

        CompilerParameters compilerParameters = new CompilerParameters(
                submission.getLanguage(),
                submission.getSources(),
                "sandbox/" + submissionId
        );

        Output compileOutput = compiler.compile(compilerParameters);
        submission.setCompileOut(compileOutput.toString());

        if (compiledWithSuccess(compileOutput)) {
            Problem problem = problemRepository.findById(submission.getProblemId());
            List<ProblemTest> tests = testRepository.findByProblemId(problem.getId());

            for (ProblemTest test : tests) {
                Run run = new Run();
                run.setSubmissionId(submissionId);
                run.setTestId(test.getId());
                run.setRunTimeMs((long) problem.getTimeLimit());
                run.setMemoryBytes(problem.getMemoryLimit());

                RunParameters runParameters = new RunParameters(test.getInput(), run.getRunTimeMs(), TimeUnit.MILLISECONDS);
                System.out.println("Running...");
                System.out.println(submission.getMainSource());
                Output runOutput = compiler.run(submission.getMainSource(), compilerParameters, runParameters);
                System.out.println("Running complete");

                /* we have to display just the output value to compare with tests expectedOutput
                Hello World!
                Exit value: 0 -- not ok
                 */
                run.setOutput(runOutput.getOutput());
                System.out.println(runOutput);
                if (runWithSuccess(runOutput) && test.isValidOutput(runOutput.getOutput())) {
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
        submissionRepository.update(submission);
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
        submission.setSources(sourceFilesRepository.findBySubmissionId(submissionId));
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
