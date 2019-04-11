package upb.sparql;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.rdf.model.Model;

public class QueryBean {
	public Model query(QueryRequest request) {
		Query query = QueryFactory.create(request.getSparql());
		QueryExecution exec = QueryExecutionFactory.sparqlService(request.getEndpoint(), query);
		
		return exec.execConstruct();
	}
}
