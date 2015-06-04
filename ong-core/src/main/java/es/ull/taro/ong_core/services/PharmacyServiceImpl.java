package es.ull.taro.ong_core.services;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.jena.riot.RDFDataMgr;
import org.springframework.stereotype.Service;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;

import es.ull.taro.ong_core.domain.GeoResource;

@Service(PharmacyService.BEAN_ID)

public class PharmacyServiceImpl implements PharmacyService{
	
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

	
//	@Override
//	public HashMap<String, String> describeUri(String uri) {
//
//		Model model = loadRDFFile();
//
//		StringBuilder sparqlQuery = new StringBuilder();
//		sparqlQuery.append("DESCRIBE ").append("<").append(uri).append(">");
//
//		QueryExecution qe = QueryExecutionFactory.create(sparqlQuery.toString(), model);
//		Model resultModel;
//		try {
//			resultModel = qe.execDescribe();
//		} finally {
//			qe.close();
//		}
//		StringBuilder sparqlQuery2 = new StringBuilder();
//		sparqlQuery2.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> ");
//		sparqlQuery2.append("PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#> ");
//		sparqlQuery2.append("PREFIX org: <http://www.w3.org/TR/vocab-org/> ");
//		sparqlQuery2.append("PREFIX vCard: <http://www.w3.org/TR/vcard-rdf/> ");
//		sparqlQuery2.append("SELECT ?Name ?PostCode ?Street ?Locality ?Telephone ?Lat ?Long");
//		sparqlQuery2.append("{ ");
//		sparqlQuery2.append("  ?resource vCard:Name ?Name . ");
//		sparqlQuery2.append("  ?A1_Location vCard:postal-code ?PostCode . ");
//		sparqlQuery2.append("  ?A2_Location vCard:street-address ?Street . ");
//		sparqlQuery2.append("  ?A3_Location vCard:locality ?Locality . ");
//		sparqlQuery2.append("  ?B1_Phone vCard:hasTelephone ?B2_Phone . ");
//		sparqlQuery2.append("  ?B2_Phone vCard:hasValue ?Telephone . ");
//		sparqlQuery2.append("  ?A4_Location geo:lat ?Lat . ");
//		sparqlQuery2.append("  ?A5_Location geo:long ?Long . ");
//		sparqlQuery2.append("}");
//
//		QueryExecution qe2 = QueryExecutionFactory.create(sparqlQuery2.toString(), resultModel);
//		HashMap<String, String> results = new HashMap<String, String>();
//		try {
//			com.hp.hpl.jena.query.ResultSet ns = qe2.execSelect();
//			while (ns.hasNext()) {
//				QuerySolution soln = ns.nextSolution();
//				results.put("Nombre", soln.getLiteral("?Name").toString());
//				results.put("Código Postal", soln.getLiteral("?PostCode").toString());
//				results.put("Domicilio", soln.getLiteral("?Street").toString());
//				results.put("Municipio", soln.getLiteral("?Locality").toString());
//				results.put("Teléfono", soln.getResource("?Telephone").toString());
//				results.put("Latitud", soln.getLiteral("?Lat").toString());
//				results.put("Longitud", soln.getLiteral("?Long").toString());
//			}
//		} finally {
//			qe2.close();
//		}
//		return results;
//	}
//	
//	
//	@Override
//	public ArrayList<String> retrievePharmacyAround(String uri, int radius) {
//		HashMap<String, String> resource = describeUri(uri);
//		String latitude, longitude;
//		for(Entry<String, String> e : resource.entrySet()){
//			latitude = e.getKey();
//			longitude = e.getValue();
//		}
//
//		ArrayList<String> around = findPharmacyAround(latitude, longitude , radius);
//		return around;
//	
//	}
	
	

	protected static Model loadRDFFile() {
		return RDFDataMgr.loadModel("pharmacy.rdf");
	}
}
