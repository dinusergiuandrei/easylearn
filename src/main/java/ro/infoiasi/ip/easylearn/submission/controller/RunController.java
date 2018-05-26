package ro.infoiasi.ip.easylearn.submission.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;
import ro.infoiasi.ip.easylearn.management.model.SubmissionTest;
import ro.infoiasi.ip.easylearn.submission.model.Run;
import ro.infoiasi.ip.easylearn.submission.repository.api.RunRepository;

import java.util.List;

@RestController
@Api(description = "Operations pertaining to the manipulations of submission runs")
public class RunController {
    private RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @RequestMapping(path = "/submissions/{id}/runs", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Find runs for a submission")
    public List <Run> findAll(@PathVariable Long id) {
        return runRepository.findBySubmissionId(id);
    }

}
