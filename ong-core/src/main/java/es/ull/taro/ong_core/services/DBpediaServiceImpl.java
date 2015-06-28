package es.ull.taro.ong_core.services;

import java.util.HashMap;

import org.apache.jena.riot.RDFDataMgr;
import org.springframework.stereotype.Service;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.Model;

@Service(DBpediaService.BEAN_ID)
public class DBpediaServiceImpl implements DBpediaService{

	public HashMap<String, String> retrieveCenterInfo(String uri) {
		
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
		sparqlQuery2.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ");
		sparqlQuery2.append("PREFIX owl: <http://www.w3.org/2002/07/owl#> ");
		sparqlQuery2.append("SELECT ?same");
		sparqlQuery2.append("{ ");
		sparqlQuery2.append("	?A1_same owl:sameAs ?same ");
		sparqlQuery2.append("}");

		QueryExecution qe2 = QueryExecutionFactory.create(sparqlQuery2.toString(), resultModel);
		
		String same = null;
		try {
			com.hp.hpl.jena.query.ResultSet ns = qe2.execSelect();
			while (ns.hasNext()) {
				QuerySolution soln = ns.nextSolution();
				same = soln.getResource("?same").toString();
			}
		} finally {
			qe2.close();
		}
		
		HashMap<String, String> result = new HashMap<String, String>();
		if(same != null){
			StringBuilder dbpediaQuery = new StringBuilder();
			dbpediaQuery.append("PREFIX dbpedia-owl: <http://dbpedia.org/ontology/> ");
			dbpediaQuery.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ");
			dbpediaQuery.append("SELECT ?camas ?afi ?des ");
			dbpediaQuery.append("WHERE {");
			dbpediaQuery.append("  <").append(same).append("> dbpedia-owl:bedCount ?camas .");
			dbpediaQuery.append("  <").append(same).append("> dbpedia-owl:affiliation ?afi .");
			dbpediaQuery.append("  <").append(same).append("> dbpedia-owl:abstract ?des");
			dbpediaQuery.append("}"); 
			
			QueryExecution qe3 = QueryExecutionFactory.sparqlService("http://es.dbpedia.org/sparql", dbpediaQuery.toString());
			
			try {
				com.hp.hpl.jena.query.ResultSet ns = qe3.execSelect();
				while (ns.hasNext()) {
					QuerySolution soln = ns.nextSolution();
					result.put("Camas", soln.getLiteral("?camas").toString());
					result.put("Descripción", soln.getLiteral("?des").toString());
					result.put("Afiliación", soln.getResource("?afi").toString());
				}
			} finally {
				qe3.close();
			}
		}
		
		return result;
	}
	
	protected static Model loadRDFFile() {
		return RDFDataMgr.loadModel("centerscategory.rdf");
	}
}