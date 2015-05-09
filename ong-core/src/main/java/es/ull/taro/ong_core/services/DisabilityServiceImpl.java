package es.ull.taro.ong_core.services;

import java.util.HashMap;

import org.apache.jena.riot.RDFDataMgr;
import org.springframework.stereotype.Service;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;

@Service(DisabilityService.BEAN_ID)

public class DisabilityServiceImpl implements DisabilityService{
	
	public HashMap<String, String> find(String name) {

		Model model = loadRDFFile();

		StringBuilder sparqlQuery = new StringBuilder();

		sparqlQuery.append("PREFIX org: <http://www.w3.org/TR/vocab-org/> ");
		sparqlQuery.append("PREFIX vCard: <http://www.w3.org/TR/vcard-rdf/> ");

		sparqlQuery.append("SELECT ?organizacion ?title");
		sparqlQuery.append("{ ");
		sparqlQuery.append("  ?organizacion a org:Organization. ");
		sparqlQuery.append("  ?organizacion org:hasRegisteredSite ?site. ");
		sparqlQuery.append("  ?site a org:Site. ");
		sparqlQuery.append("  ?site vCard:Name ?title. ");
		sparqlQuery.append("  FILTER regex(?title, \"").append(name).append("\", \"i\"). ");
		sparqlQuery.append("}");

		HashMap<String, String> uris = new HashMap<String, String>();

		QueryExecution qe = QueryExecutionFactory.create(sparqlQuery.toString(), model);
		try {
			ResultSet results = qe.execSelect();
			for (; results.hasNext();) {
				QuerySolution sol = (QuerySolution) results.next();
				String resource = sol.getResource("?organizacion").getURI().toString();
				String title = sol.getLiteral("?title").toString();
				uris.put(resource, title);
			}
		} finally {
			qe.close();
		}

		return uris;
	}

	protected static Model loadRDFFile() {
		return RDFDataMgr.loadModel("recursos_discapacidad.rdf");
	}
}
