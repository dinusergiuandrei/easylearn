package ro.infoiasi.ip.easylearn.management.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import ro.infoiasi.ip.easylearn.management.model.Problem;
import ro.infoiasi.ip.easylearn.management.repository.api.ProblemRepository;

@RestController
@Api(value = "problems", description = "text1")
public class ProblemController {

	ProblemRepository p;

    public ProblemController( ProblemRepository p)
    {
        this.p=p;
    }

    @RequestMapping(path = "/problems/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Problem problems(@PathVariable Long id) {
    	Problem P = p.findById(id);
    	
    	if (P==null)
    		P = new Problem(1, 1, "String titlu", "String descriere", "String cerinta", "String date_intrare",
                    "String date_iesire", "String restrictii", 1, 1, "String tip_date",
                    "String exemplu_intrare", "String exemplu_iesire", "String input_file", "String output_file", 1,
                    1);
    	
        return P;
    }

}
