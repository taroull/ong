package es.ull.taro.ong_core.services;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.jena.riot.RDFDataMgr;
import org.springframework.stereotype.Service;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;

import es.ull.taro.ong_core.domain.CenterResource;
import es.ull.taro.ong_core.domain.GeoResource;

@Service(CenterService.BEAN_ID)
public class CenterServiceImpl implements CenterService {
	
	public ArrayList<GeoResource> find(String name) {

		Model model = loadRDFFile();

		StringBuilder sparqlQuery = new StringBuilder();
		
		sparqlQuery.append("PREFIX org: <http://www.w3.org/TR/vocab-org/> ");
		sparqlQuery.append("PREFIX vCard: <http://www.w3.org/TR/vcard-rdf/> ");
		sparqlQuery.append("PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#> ");
		sparqlQuery.append("SELECT ?organizacion ?title ?lat ?long");
		sparqlQuery.append("{ ");
		sparqlQuery.append("  ?organizacion a org:Organization . ");
		sparqlQuery.append("  ?organizacion org:hasRegisteredSite ?site . ");
		sparqlQuery.append("  ?site a org:Site . ");
		sparqlQuery.append("  ?site vCard:Name ?title . ");
		
		sparqlQuery.append("  OPTIONAL { ?site org:siteAddress ?org . ");
		sparqlQuery.append("  ?org a vCard:Organization . ");
		sparqlQuery.append("  ?org geo:lat ?lat . ");
		sparqlQuery.append("  ?org geo:long ?long } . ");
		
		sparqlQuery.append("  FILTER regex(?title, \"").append(name).append("\", \"i\"). ");
		sparqlQuery.append("}");
		
		ArrayList<GeoResource> resources = new ArrayList<GeoResource>();

		QueryExecution qe = QueryExecutionFactory.create(sparqlQuery.toString(), model);
		try {
			ResultSet results = qe.execSelect();
			for (; results.hasNext();) {
				QuerySolution sol = (QuerySolution) results.next();
				GeoResource resource = new GeoResource();
				resource.setUri(sol.getResource("?organizacion").getURI().toString());
				resource.setName(sol.getLiteral("?title").toString());
				if(sol.getLiteral("?lat") != null && sol.getLiteral("?long") != null){
					resource.setLatitude(sol.getLiteral("?lat").toString());
					resource.setLongitude(sol.getLiteral("?long").toString());
				}
			
				resources.add(resource);		
			}
		} finally {
			qe.close();
		}

		return resources;
	}
	
	@Override
	public HashMap<String, String> describeUri(String uri) {

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
		sparqlQuery2.append("PREFIX foaf: <http://xmlns.com/foaf/spec/> ");
//		sparqlQuery2.append("SELECT ?Name ?PostCode ?Street ?Locality ?Telephone ?Web ?Fax");
		sparqlQuery2.append("SELECT ?Name ?PostCode ?Street ?Locality ?Email ?Web ?Telephone ?Fax");
		sparqlQuery2.append("{ ");
		sparqlQuery2.append("OPTIONAL {?C1_Info vCard:Email ?Email . }");
//		sparqlQuery2.append("OPTIONAL {?C2_Info vCard:hasEmail ?Email . }");
		sparqlQuery2.append("OPTIONAL {?C3_Info foaf:homepage ?Web . }");
		sparqlQuery2.append("OPTIONAL {?B1_Phone vCard:hasTelephone ?B2_Phone . }");
		sparqlQuery2.append("OPTIONAL {?B2_Phone vCard:hasValue ?Telephone . }");
		sparqlQuery2.append("OPTIONAL {?B3_Phone vCard:Fax ?Fax . }");
		sparqlQuery2.append("OPTIONAL {?A2_Location vCard:street-address ?Street . }");
		sparqlQuery2.append("  ?resource vCard:Name ?Name . ");
		sparqlQuery2.append("  ?A1_Location vCard:postal-code ?PostCode . ");		
		sparqlQuery2.append("  ?A3_Location vCard:locality ?Locality . ");

		sparqlQuery2.append("}");

		QueryExecution qe2 = QueryExecutionFactory.create(sparqlQuery2.toString(), resultModel);
		HashMap<String, String> results = new HashMap<String, String>();
		try {
			com.hp.hpl.jena.query.ResultSet ns = qe2.execSelect();
			while (ns.hasNext()) {
				QuerySolution soln = ns.nextSolution();
				results.put("Nombre", soln.getLiteral("?Name").toString());
				results.put("Código Postal", soln.getLiteral("?PostCode").toString());
				if(soln.getLiteral("?Street").toString() != null){
					results.put("Domicilio", soln.getLiteral("?Street").toString());
				}	
				results.put("Municipio", soln.getLiteral("?Locality").toString());
				if(soln.getResource("?Telephone") != null){
					results.put("Teléfono", soln.getResource("?Telephone").toString());
				}
				if(soln.getResource("?Web") != null){
					results.put("Página Web", soln.getResource("?Web").toString());
				}
				if(soln.getLiteral("?Email") != null){
					results.put("Email", soln.getLiteral("?Email").toString());
				}
				if(soln.getLiteral("?Fax") != null){
					results.put("Fax", soln.getLiteral("?Fax").toString());
				}
			}
		} finally {
			qe2.close();
		}
		return results;
	}
	
	@Override
	public ArrayList<CenterResource> findCategory(String category) {

		Model model = loadRDFFile2();

		StringBuilder sparqlQuery = new StringBuilder();
		
		sparqlQuery.append("PREFIX org: <http://www.w3.org/TR/vocab-org/> ");
		sparqlQuery.append("PREFIX vCard: <http://www.w3.org/TR/vcard-rdf/> ");
		sparqlQuery.append("PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#> ");
		sparqlQuery.append("SELECT ?organizacion ?title ?lat ?long ?cat");
		sparqlQuery.append("{ ");
		sparqlQuery.append("  ?organizacion a org:Organization . ");
		sparqlQuery.append("  ?organizacion org:hasRegisteredSite ?site . ");
		sparqlQuery.append("  ?site a org:Site . ");
		sparqlQuery.append("  ?site vCard:Name ?title . ");
		sparqlQuery.append("  ?site org:classification ?cat . ");
		
		sparqlQuery.append("  OPTIONAL { ?site org:siteAddress ?org . ");
		sparqlQuery.append("  ?org a vCard:Organization . ");
		sparqlQuery.append("  ?org geo:lat ?lat . ");
		sparqlQuery.append("  ?org geo:long ?long } . ");
		
		sparqlQuery.append("  FILTER regex(?cat, \"").append(category).append("\", \"i\"). ");
		sparqlQuery.append("}");
		
		ArrayList<CenterResource> resources = new ArrayList<CenterResource>();

		QueryExecution qe = QueryExecutionFactory.create(sparqlQuery.toString(), model);
		try {
			ResultSet results = qe.execSelect();
			for (; results.hasNext();) {
				QuerySolution sol = (QuerySolution) results.next();
				CenterResource resource = new CenterResource();
				resource.setUri(sol.getResource("?organizacion").getURI().toString());
				resource.setName(sol.getLiteral("?title").toString());
				resource.setCategory(sol.getLiteral("?cat").toString());
				if(sol.getLiteral("?lat") != null && sol.getLiteral("?long") != null){
					resource.setLatitude(sol.getLiteral("?lat").toString());
					resource.setLongitude(sol.getLiteral("?long").toString());
				}
			
				resources.add(resource);		
			}
		} finally {
			qe.close();
		}

		return resources;
	}
	
	

//	public HashMap<String, String> find(String name) {
//
//		Model model = loadRDFFile();
//
//		StringBuilder sparqlQuery = new StringBuilder();
//
//		sparqlQuery.append("PREFIX org: <http://www.w3.org/TR/vocab-org/> ");
//		sparqlQuery.append("PREFIX vCard: <http://www.w3.org/TR/vcard-rdf/> ");
//
//		sparqlQuery.append("SELECT ?organizacion ?title");
//		sparqlQuery.append("{ ");
//		sparqlQuery.append("  ?organizacion a org:Organization. ");
//		sparqlQuery.append("  ?organizacion org:hasRegisteredSite ?site. ");
//		sparqlQuery.append("  ?site a org:Site. ");
//		sparqlQuery.append("  ?site vCard:Name ?title. ");
//		sparqlQuery.append("  FILTER regex(?title, \"").append(name).append("\", \"i\"). ");
//		sparqlQuery.append("}");
//
//		HashMap<String, String> uris = new HashMap<String, String>();
//
//		QueryExecution qe = QueryExecutionFactory.create(sparqlQuery.toString(), model);
//		try {
//			ResultSet results = qe.execSelect();
//			for (; results.hasNext();) {
//				QuerySolution sol = (QuerySolution) results.next();
//				String resource = sol.getResource("?organizacion").getURI().toString();
//				String title = sol.getLiteral("?title").toString();
//				uris.put(resource, title);
//			}
//		} finally {
//			qe.close();
//		}
//
//		return uris;
//	}

	protected static Model loadRDFFile() {
		return RDFDataMgr.loadModel("centers.rdf");
	}
	
	protected static Model loadRDFFile2() {
		return RDFDataMgr.loadModel("centerscategory.rdf");
	}
}