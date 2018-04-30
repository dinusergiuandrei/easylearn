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
import ro.infoiasi.ip.easylearn.utils.RunState;
import ro.infoiasi.ip.easylearn.utils.SubmissionState;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

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
//            for(Test test : tests) {
                // TODO: run for each test and compare results
                Run run = new Run();
                run.setSubmissionId(submission.getId());
                run.setRunTimeMs(10L);
                run.setMemoryBytes(10L);

                // TODO: run() must receive testInput
                Output runOutput = compiler.run(compilerParameters);
//                compareOutput(runOutput, testOutput);
                run.setStatus(runWithSuccess(runOutput) ? RunState.Success : RunState.Failed);

                runRepository.save(run);
//            }

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

    private Output compileAndRun(CompilerParameters compilerParameters) {
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
