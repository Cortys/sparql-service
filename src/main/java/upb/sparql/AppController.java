package upb.sparql;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
	
	@Autowired
	QueryBean queryBean;
	
	@Autowired
	StoreBean storeBean;
	
	private void outputModel(Model model, HttpServletResponse response) {
		response.setContentType("text/plain;charset=UTF-8");
		try {
			RDFDataMgr.write(response.getOutputStream(), model, RDFFormat.NTRIPLES);
		} catch (IOException e) {
			response.setStatus(500);
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/")
	public String status() {
		return "SPARQL REST service is running.";
	}
	
	@RequestMapping("/query")
	public void query(@RequestParam String sparql, @RequestParam String endpoint, HttpServletResponse response) {
		Model result = queryBean.query(new QueryRequest(sparql, endpoint));
		
		storeBean.insertModel(result);
		
		outputModel(result, response);
	}
	
	@RequestMapping("/fetch-all")
	public void fetchAll(HttpServletResponse response) {
		storeBean.fetchAll(m -> outputModel(m, response));
	}
}
