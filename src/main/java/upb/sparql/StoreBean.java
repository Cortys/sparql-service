package upb.sparql;

import java.util.function.Consumer;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.TxnType;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.tdb.TDBFactory;

public class StoreBean {
	private final String directory;
	private final Dataset dataset;
	
	public StoreBean() {
		String storePath = System.getenv("STORE_PATH");
		
		directory = storePath == null || storePath.isEmpty() ? "/tmp/sparql-service" : storePath;
		dataset = TDBFactory.createDataset(directory);
	}
	
	public void insertModel(Model m) {
		try {
			dataset.begin(TxnType.WRITE);
			dataset.getDefaultModel().add(m);
			dataset.commit();
		}
		finally {
			dataset.end();
		}
	}
	
	public void fetchAll(Consumer<Model> c) {
		try {
			dataset.begin(TxnType.READ);
			c.accept(dataset.getDefaultModel());
			dataset.commit();
		}
		finally {
			dataset.end();
		}
	}
}
