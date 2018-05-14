package ro.infoiasi.ip.easylearn.management.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import ro.infoiasi.ip.easylearn.management.model.ProblemTest;
import ro.infoiasi.ip.easylearn.management.repository.api.TestRepository;

import java.util.List;

@RestController
@Api (value="test",description ="text1")
public class TestController {
    TestRepository t;

    public TestController(TestRepository t){this.t=t;}

    @RequestMapping(path = "/test/{id}", method = RequestMethod.GET)
    @ResponseBody

    public ProblemTest tests (@PathVariable Long id)
    {
        return t.findById(id);
    }

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    @ResponseBody

    public List<ProblemTest> allTests (){
        List<ProblemTest> listOfTest=t.findAll();
        return listOfTest;
    }
}
