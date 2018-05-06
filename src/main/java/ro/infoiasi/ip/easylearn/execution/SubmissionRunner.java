package ro.infoiasi.ip.easylearn.execution;

import org.aspectj.weaver.ast.Test;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ro.infoiasi.ip.easylearn.compiler.Compiler;
import ro.infoiasi.ip.easylearn.compiler.CompilerParameters;
import ro.infoiasi.ip.easylearn.compiler.Output;
import ro.infoiasi.ip.easylearn.management.model.ProblemTest;
import ro.infoiasi.ip.easylearn.submission.model.Run;
import ro.infoiasi.ip.easylearn.submission.model.Submission;
import ro.infoiasi.ip.easylearn.submission.repository.api.RunRepository;
import ro.infoiasi.ip.easylearn.submission.repository.api.SubmissionRepository;
import ro.infoiasi.ip.easylearn.utils.RunState;
import ro.infoiasi.ip.easylearn.utils.SubmissionState;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Component
public class SubmissionRunner {
    private SubmissionRepository submissionRepository;
    private RunRepository runRepository;
    private Compiler compiler;

    public SubmissionRunner(SubmissionRepository submissionRepository, RunRepository runRepository) {
        this.submissionRepository = submissionRepository;
        this.runRepository = runRepository;
        this.compiler = new Compiler();
    }

    @JmsListener(destination = "submissionQueue", containerFactory = "jmsListenerContainerFactory")
    public void run(Long submissionId) throws Exception {
        System.out.println("Running submission with id: " + submissionId);
        Submission submission = submissionRepository.findById(submissionId);
        submission.setState(SubmissionState.Running);
        System.out.println("Running submission: " + submission);

        String filename = generateSourceCodeMainClassPath(submission);
        addSubmissionSourceCodeToFile(submission, filename);

        CompilerParameters compilerParameters = new CompilerParameters(filename, "sandbox/sample/generated/java/", "", 1000L, MILLISECONDS);

        Output compileOutput = compiler.compile(compilerParameters);

        if (compiledWithSuccess(compileOutput)) {
//            List<ProblemTest> test = problemTestRepository.findById(submission.getProblemId());
            ProblemTest problemTest = new ProblemTest(1L, 1L, "", "Hello World!\n");
            ProblemTest problemTest2 = new ProblemTest(1L, 1L, "", "Hello World not good");
            List<ProblemTest> tests = Arrays.asList(problemTest, problemTest2);

            for(ProblemTest test : tests) {
                Run run = new Run();
                run.setSubmissionId(submission.getId());
                run.setRunTimeMs(10L);
                run.setMemoryBytes(10L);

                // TODO: run() must receive testInput
                Output runOutput = compiler.run(compilerParameters);

                if (runOutput.getOutput().equals(test.getExpectedOutput())) {
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

    // TODO: language cases
    private String generateSourceCodeMainClassPath(Submission submission) {
        return "sandbox/sample/sources/java/Main.java";
    }

    // TODO: implement it
    private boolean runWithSuccess(Output runOutput) {
        return runOutput.getExitValue() == 0;
    }

    private boolean compiledWithSuccess(Output compileOutput) {
        return compileOutput.getExitValue() == 0;
    }

    private void addSubmissionSourceCodeToFile(Submission submission, String filename) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(filename);
        writer.write(submission.getSourceCode());
        writer.flush();
        writer.close();
    }

    private Output compileAndRun(Compiler compiler, CompilerParameters compilerParameters) {
        //compiler.getSecurityManager().check ...

        try {
            Output compileOutput = compiler.compile(compilerParameters);
            if (compileOutput.getExitValue() == 0) {
                Output runOutput = compiler.run(compilerParameters);
                return runOutput;
            } else {
                return compileOutput;
            }
        } catch (Exception e) {
            Output errorOutput = new Output();
            errorOutput.setError(e.getMessage());
            return errorOutput;
        }
    }
}
