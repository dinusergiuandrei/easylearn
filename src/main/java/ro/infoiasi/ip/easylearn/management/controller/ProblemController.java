package ro.infoiasi.ip.easylearn.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import ro.infoiasi.ip.easylearn.management.model.Problem;
import ro.infoiasi.ip.easylearn.management.repository.api.ProblemRepository;
import ro.infoiasi.ip.easylearn.management.repository.impl.SqlProblemRepository;

@RestController
@Api(value = "problems", description = "text1")
public class ProblemController {

	ProblemRepository p;
	

    public ProblemController( ProblemRepository p)
    {
        this.p=p;
    }
    
    @RequestMapping(path = "/problems", method = RequestMethod.GET)
    @ResponseBody
    public List<Problem> allProblems() {
    	List<Problem> P = p.findAll();
    	
        return P;
    }

    @RequestMapping(path = "/problems/id={id}", method = RequestMethod.GET)
    @ResponseBody
    public Problem problems(@PathVariable Long id) {
    	Problem P = p.findById(id);
    	
    	if (P==null)
    		P = new Problem();
    	
        return P;
    }
    
    @RequestMapping(path = "/problems/cat={cat_id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Problem> categoryProblems(@PathVariable int cat_id) {
    	List<Problem> P = p.findByCategory(cat_id);
    	
        return P;
    }
    
    @RequestMapping(path = "/problems/auth={auth_id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Problem> authorProblems(@PathVariable int auth_id) {
    	List<Problem> P = p.findByAuthor(auth_id);
    	
        return P;
    }
    
    @RequestMapping(path = "/problems/add", method = RequestMethod.GET)
    @ResponseBody
    public boolean addProblem(@RequestBody Problem jsonProblem) 
    {
        return p.add(jsonProblem);
    }
    
    @RequestMapping(path = "/problems/populate", method = RequestMethod.GET)
    @ResponseBody
    public boolean populateTable() 
    {
    	System.out.println("New ID: " + Long.toString(p.getLastID()+1));
    	
    	//problema de test
    	//datele vor fi preluate prin json
    	Problem P = new Problem(p.getLastID()+1, 1, 
    			"Camioane", "Problema Camioane", 
    			"O firma are doua tipuri de camioane: camioane de tipul 1, care pot transporta cate t1 tone de marfa pe zi, si camioane de tipul 2, care pot transporta cate t2 tone de marfa pe zi. \n Stiind ca firma are n camioane de tipul 1 si m camioane de tipul 2, cate tone de marfa pot transporta camioanele firmei in z zile.",
    			"Programul citeste de la tastatura numerele naturale t1 t2 n m z.",
                "Programul va afisa pe ecran rezultatul.",
                "2 < t1, t2 < 100\n" + 
                "2 < n, m < 100\n" + 
                "2 < z < 30",
                1, 
                1, 
                "tastatura",
                "3 5 4 2 5", 
                "110", 
                "", 
                "", 
                1,
                1);
    	
        return p.add(P);
    }
}
