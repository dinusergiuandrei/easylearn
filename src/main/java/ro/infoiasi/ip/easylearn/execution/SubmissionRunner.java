package ro.infoiasi.ip.easylearn.execution;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ro.infoiasi.ip.easylearn.compiler.Compiler;
import ro.infoiasi.ip.easylearn.compiler.*;
import ro.infoiasi.ip.easylearn.management.model.Problem;
import ro.infoiasi.ip.easylearn.management.model.ProblemTest;
import ro.infoiasi.ip.easylearn.management.repository.api.ProblemRepository;
import ro.infoiasi.ip.easylearn.management.repository.api.TestRepository;
import ro.infoiasi.ip.easylearn.submission.model.Run;
import ro.infoiasi.ip.easylearn.submission.model.Submission;
import ro.infoiasi.ip.easylearn.submission.repository.api.RunRepository;
import ro.infoiasi.ip.easylearn.submission.repository.api.SourceFilesRepository;
import ro.infoiasi.ip.easylearn.submission.repository.api.SubmissionRepository;
import ro.infoiasi.ip.easylearn.utils.FileManager;
import ro.infoiasi.ip.easylearn.utils.RunState;
import ro.infoiasi.ip.easylearn.utils.SubmissionState;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static ro.infoiasi.ip.easylearn.utils.FileManager.removeDirectory;
import static ro.infoiasi.ip.easylearn.utils.SubmissionState.*;

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
        String rootDirectoryPath = "sandbox/" + submissionId;

        CompilerParameters compilerParameters = new CompilerParameters(
                submission.getLanguage(),
                submission.getSources(),
                rootDirectoryPath
        );

        // todo: set up permissions
        //compilerParameters.getPermissions().add( ... );

        Output compileOutput = compiler.compile(compilerParameters);
        submission.setCompileOut(compileOutput.toString());

        System.out.println("Compiling submiossion: " + submission);
        if (compiledWithSuccess(compileOutput)) {
            System.out.println("Running the tests for submission: " + submission);
            Problem problem = problemRepository.findById(submission.getProblemId());
            List <ProblemTest> tests = testRepository.findByProblemId(problem.getId());

            for (ProblemTest test : tests) {
                System.out.println("Running test: " + test);
                Run run = createRun(submissionId, problem, test);

                RunParameters runParameters = new RunParameters(test.getInput(), run.getRunTimeMs(), TimeUnit.MILLISECONDS);
                Output runOutput = compiler.run(submission.getMainSource(), compilerParameters, runParameters);
                System.out.println("Ran test: " + test);

                run.setOutput(runOutput.getOutput());

                if (runWithSuccess(runOutput) && test.isValidOutput(runOutput.getOutput())) {
                    run.setStatus(RunState.Success);
                } else {
                    run.setStatus(RunState.Failed);
                    submission.setState(SubmissionState.Failed);
                }

                runRepository.save(run);
            }

            if (submission.getState() != SubmissionState.Failed) {
                submission.setState(SubmissionState.Passed);
            }
        } else {
            submission.setState(CompilationFailed);
        }
        System.out.println("Finished running submission: " + submission);
        submissionRepository.update(submission);

        removeDirectory(rootDirectoryPath);
    }

    private Run createRun(Long submissionId, Problem problem, ProblemTest test) {
        Run run = new Run();
        run.setSubmissionId(submissionId);
        run.setTestId(test.getId());
        run.setRunTimeMs(problem.getTimeLimit());
        run.setMemoryBytes(problem.getMemoryLimit());
        return run;
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
        submission.setState(Running);
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
