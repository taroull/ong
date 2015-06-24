package es.ull.taro.ong_core.services;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;

@Service(DBpediaService.BEAN_ID)
public class DBpediaServiceImpl implements DBpediaService{

	public HashMap<String, String> retrieveCenterInfo(String uri) {
		
		StringBuilder dbpediaQuery = new StringBuilder();
		dbpediaQuery.append("PREFIX dbpedia-owl: <http://dbpedia.org/ontology/> ");
		dbpediaQuery.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ");
		dbpediaQuery.append("SELECT ?camas ?afi ?des ");
		dbpediaQuery.append("WHERE {");
		dbpediaQuery.append("  <").append(uri).append("> dbpedia-owl:bedCount ?camas .");
		dbpediaQuery.append("  <").append(uri).append("> dbpedia-owl:affiliation ?afi .");
		dbpediaQuery.append("  <").append(uri).append("> dbpedia-owl:abstract ?des");
		dbpediaQuery.append("}"); 
		
//		dbpediaQuery.append("PREFIX dbpedia-owl: <http://dbpedia.org/ontology/> ");
//		dbpediaQuery.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ");
//		dbpediaQuery.append("PREFIX owl: <http://www.w3.org/2002/07/owl#> ");
//		dbpediaQuery.append("SELECT ?camas ?afi ?des ");
//		dbpediaQuery.append("WHERE {");
//		dbpediaQuery.append("  <").append(uri).append("> dbpedia-owl:abstract ?des");
//		dbpediaQuery.append("	?urisame owl:sameAs ?urisame");
//		dbpediaQuery.append("   ?urisame dbpedia-owl:bedCount ?camas .");
//		dbpediaQuery.append("   ?urisame dbpedia-owl:affiliation ?afi .");
//		dbpediaQuery.append("   ?urisame dbpedia-owl:abstract ?des");
//		dbpediaQuery.append("}"); 
		
		QueryExecution qe2 = QueryExecutionFactory.sparqlService("http://es.dbpedia.org/sparql", dbpediaQuery.toString());
		HashMap<String, String> results = new HashMap<String, String>();
		try {
			com.hp.hpl.jena.query.ResultSet ns = qe2.execSelect();
			while (ns.hasNext()) {
				QuerySolution soln = ns.nextSolution();
				results.put("Camas", soln.getLiteral("?camas").toString());
				results.put("Descripción", soln.getLiteral("?des").toString());
				results.put("Afiliación", soln.getResource("?afi").toString());
			}
		} finally {
			qe2.close();
		}
		return results;
	}
}