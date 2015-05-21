package es.ull.taro.ong_core.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.jena.riot.RDFDataMgr;
import org.springframework.stereotype.Service;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;

@Service(PharmacyService.BEAN_ID)

public class PharmacyServiceImpl implements PharmacyService{

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

	
	
	@Override
	public Map<String, String> describeUri(String uri) {

		Model model = loadRDFFile();

		StringBuilder sparqlQuery = new StringBuilder();
		sparqlQuery.append("DESCRIBE ").append("<").append(uri).append(">");

		QueryExecution qe = QueryExecutionFactory.create(sparqlQuery.toString(), model);
		Model resultModel;
		try {
			resultModel = qe.execDescribe();
		} finally {
			qe.close();
		}
		StringBuilder sparqlQuery2 = new StringBuilder();
		sparqlQuery2.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> ");
		sparqlQuery2.append("PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#> ");
		sparqlQuery2.append("PREFIX org: <http://www.w3.org/TR/vocab-org/> ");
		sparqlQuery2.append("PREFIX vCard: <http://www.w3.org/TR/vcard-rdf/> ");
		sparqlQuery2.append("SELECT ?Name");
		sparqlQuery2.append("{ ");
		sparqlQuery2.append("  ?resource vCard:Name ?Name. ");
		sparqlQuery2.append("}");

		QueryExecution qe2 = QueryExecutionFactory.create(sparqlQuery2.toString(), resultModel);
		HashMap<String, String> results = new HashMap<String, String>();
		try {
			com.hp.hpl.jena.query.ResultSet ns = qe2.execSelect();
			while (ns.hasNext()) {
				QuerySolution soln = ns.nextSolution();
				results.put("Nombre", soln.getResource("?Name").toString());
			}
		} finally {
			qe2.close();
		}
		return results;
	}
	

	protected static Model loadRDFFile() {
		return RDFDataMgr.loadModel("farmacias.rdf");
	}
}
