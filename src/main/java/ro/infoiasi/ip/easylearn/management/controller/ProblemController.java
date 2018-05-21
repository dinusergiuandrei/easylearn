package ro.infoiasi.ip.easylearn.management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ro.infoiasi.ip.easylearn.management.model.Problem;
import ro.infoiasi.ip.easylearn.management.repository.api.ProblemRepository;

@RestController
@Api(value = "problems", description = "Operations pertaining to the manipulations of problems")
public class ProblemController {

	ProblemRepository problemRepository;
	

    public ProblemController( ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @RequestMapping(path = "/problems", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Returns all the problems in the database")
    public List<Problem> getAllProblems() {
    	List<Problem> problems = problemRepository.findAll();
    	
        return problems;
    }

    @RequestMapping(path = "/problems/id={id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Returns the problem with the specified id")
    public Problem getProblemById(@PathVariable Long id) {
    	Problem problem = problemRepository.findById(id);
    	
    	if (problem==null)
    		problem = new Problem();
    	
        return problem;
    }
    
    @RequestMapping(path = "/problems/cat={cat_id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Returns all the problems in the provided category")
    public List<Problem> getProblemsByCategory(@PathVariable long cat_id) {
    	List<Problem> problems = problemRepository.findByCategory(cat_id);
    	
        return problems;
    }
    
    @RequestMapping(path = "/problems/author={author_id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Returns all the problems posted by the author identified by the provided userID")
    public List<Problem> getProblemsByAuthor(@PathVariable String author_id) {
    	List<Problem> problems = problemRepository.findByAuthor(author_id);
    	
        return problems;
    }
    
    @RequestMapping(path = "/problems/solved/user={user_id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Returns all problems solved by the user identified by the provided userID")
    public List<Problem> getSolvedProblems(@PathVariable int user_id) {
    	List<Problem> problems = problemRepository.findSolved(user_id);
    	
        return problems;
    }
    
    @RequestMapping(path = "/problems/attempted/user={user_id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Returns all problems attempted by the user identified by the provided userID (at least one submission)")
    public List<Problem> getAttemptedProblems(@PathVariable int user_id) {
    	List<Problem> problems = problemRepository.findAttempted(user_id);
    	
        return problems;
    }
    
    @RequestMapping(path = "/problems/add", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Adds a new problem to the database")
    public boolean addProblem(@RequestBody Problem jsonProblem) 
    {
    	//datele vor fi preluate prin json, mai putin id-ul noii probleme
    	jsonProblem.setProblemID(problemRepository.getLastID()+1);
        return problemRepository.add(jsonProblem);
    }
    
    @RequestMapping(path = "/problems/populate", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Test method for inserting a dummy problem")
    public boolean populateTable() 
    {
    	System.out.println("New ID: " + Long.toString(problemRepository.getLastID()+1));
    	
    	//problema de test
    	//datele vor fi preluate prin json
    	Problem problem = new Problem(problemRepository.getLastID() + 1, "1",
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
    	
        return problemRepository.add(problem);
    }
}
