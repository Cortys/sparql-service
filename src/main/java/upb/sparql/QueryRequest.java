package upb.sparql;

public class QueryRequest {
	private String sparql;
	private String endpoint;
	
	public QueryRequest() {
		
	}
	
	public QueryRequest(String sparql, String endpoint) {
		this.sparql = sparql;
		this.endpoint = endpoint;
	}

	public String getSparql() {
		return sparql;
	}

	public void setSparql(String sparql) {
		this.sparql = sparql;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
}
