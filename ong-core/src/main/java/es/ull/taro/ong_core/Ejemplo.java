package es.ull.taro.ong_core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.jena.riot.RDFDataMgr;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;

import es.ull.taro.ong_core.domain.GeoResource;

public class Ejemplo{
	
//	public static ArrayList<GeoResource> retrievePharmacy(String name) {
//
//		Model model = loadRDFFile();
//
//		StringBuilder sparqlQuery = new StringBuilder();
//		
//		sparqlQuery.append("PREFIX org: <http://www.w3.org/TR/vocab-org/> ");
//		sparqlQuery.append("PREFIX vCard: <http://www.w3.org/TR/vcard-rdf/> ");
//		sparqlQuery.append("PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#> ");
//		sparqlQuery.append("SELECT ?organizacion ?title ?lat ?long");
//		sparqlQuery.append("{ ");
//		sparqlQuery.append("  ?organizacion a org:Organization . ");
//		sparqlQuery.append("  ?organizacion org:hasRegisteredSite ?site . ");
//		sparqlQuery.append("  ?site a org:Site . ");
//		sparqlQuery.append("  ?site vCard:Name ?title . ");
//		
//		sparqlQuery.append("  OPTIONAL { ?site org:siteAddress ?org . ");
//		sparqlQuery.append("  ?org a vCard:Organization . ");
//		sparqlQuery.append("  ?org geo:lat ?lat . ");
//		sparqlQuery.append("  ?org geo:long ?long } . ");
//		
//		sparqlQuery.append("  FILTER regex(?title, \"").append(name).append("\", \"i\"). ");
//		sparqlQuery.append("}");
//
//		//HashMap<String, String> uris = new HashMap<String, String>();
//		
//		ArrayList<GeoResource> resources = new ArrayList<GeoResource>();
//
//		QueryExecution qe = QueryExecutionFactory.create(sparqlQuery.toString(), model);
//		try {
//			ResultSet results = qe.execSelect();
//			for (; results.hasNext();) {
//				QuerySolution sol = (QuerySolution) results.next();
////				String resource = sol.getResource("?organizacion").getURI().toString();
////				String title = sol.getLiteral("?title").toString();
////				uris.put(resource, title);
////				if(sol.getLiteral("?lat").toString() != null){
////					String latitude = sol.getLiteral("?lat").toString();
////					String longitude = sol.getLiteral("?long").toString();
////					uris.put(latitude, longitude);
////				}
//				
////				uris.put("Nombre", sol.getLiteral("?title").toString());
////				uris.put("URI", sol.getResource("?organizacion").getURI().toString());
////				if(sol.getLiteral("?lat").toString() != null){
////					uris.put("Latitude", sol.getLiteral("?lat").toString());
////					uris.put("Longitude", sol.getLiteral("?long").toString());
////				}
//				
//				GeoResource resource = new GeoResource();
//				resource.setUri(sol.getResource("?organizacion").getURI().toString());
//				resource.setName(sol.getLiteral("?title").toString());
//				if(sol.getLiteral("?lat") != null && sol.getLiteral("?long") != null){
//					resource.setLatitude(sol.getLiteral("?lat").getDouble());
//					resource.setLongitude(sol.getLiteral("?long").getDouble());
//				}
//			
//				resources.add(resource);		
//			}
//		} finally {
//			qe.close();
//		}
//
//		return resources;
//	}
//	
//	protected static Model loadRDFFile() {
//		return RDFDataMgr.loadModel("farmacias.rdf");
//	}
//	
//	public static void main(String[] args){
//		ArrayList<GeoResource> pharmacyURIs = retrievePharmacy("BONNET PEREZ, ANDRES");
////		for(int i = 0; i<pharmacyURIs.size(); i++){
////			System.out.println(pharmacyURIs.get(i).getName());
////			System.out.println(pharmacyURIs.get(i).getUri());
////			System.out.println(pharmacyURIs.get(i).getLatitude());
////			System.out.println(pharmacyURIs.get(i).getLongitude());
////		}
//	}
	
	
	
//	
//	public static HashMap<String, String> retrieveCenterInfo(String uri) {
//		
//		StringBuilder dbpediaQuery = new StringBuilder();
//		dbpediaQuery.append("PREFIX dbpedia-owl: <http://dbpedia.org/ontology/> ");
//		dbpediaQuery.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ");
//		dbpediaQuery.append("SELECT ?camas ?afi ?des ");
//		dbpediaQuery.append("WHERE {");
//		//dbpediaQuery.append("?camas dcterms:subject <").append(uri).append(">");
//		dbpediaQuery.append("  <").append(uri).append("> dbpedia-owl:bedCount ?camas .");
//		dbpediaQuery.append("  <").append(uri).append("> dbpedia-owl:affiliation ?afi .");
//		dbpediaQuery.append("  <").append(uri).append("> dbpedia-owl:abstract ?des");
//		dbpediaQuery.append("}"); 
//		
//		QueryExecution qe2 = QueryExecutionFactory.sparqlService("http://es.dbpedia.org/sparql", dbpediaQuery.toString());
//		HashMap<String, String> results = new HashMap<String, String>();
//		try {
//			com.hp.hpl.jena.query.ResultSet ns = qe2.execSelect();
//			while (ns.hasNext()) {
//				QuerySolution soln = ns.nextSolution();
//				results.put("Camas", soln.getLiteral("?camas").toString());
//				results.put("Descripción", soln.getLiteral("?des").toString());
//				results.put("Afiliación", soln.getResource("?afi").toString());
//				System.out.println(results);
//			}
//		} finally {
//			qe2.close();
//		}
//		return results;
//	}
	

	
	public static HashMap<String, String> describeUri(String uri) {

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
		sparqlQuery2.append("SELECT ?Name ?PostCode ?Street ?Locality ?Telephone ?Lat ?Long");
		sparqlQuery2.append("{ ");
		sparqlQuery2.append("  ?resource vCard:Name ?Name . ");
		sparqlQuery2.append("  ?A1_Location vCard:postal-code ?PostCode . ");
		sparqlQuery2.append("  ?A2_Location vCard:street-address ?Street . ");
		sparqlQuery2.append("  ?A3_Location vCard:locality ?Locality . ");
		sparqlQuery2.append("  ?B1_Phone vCard:hasTelephone ?B2_Phone . ");
		sparqlQuery2.append("  ?B2_Phone vCard:hasValue ?Telephone . ");
		sparqlQuery2.append("  ?A4_Location geo:lat ?Lat . ");
		sparqlQuery2.append("  ?A5_Location geo:long ?Long . ");
		sparqlQuery2.append("}");

		QueryExecution qe2 = QueryExecutionFactory.create(sparqlQuery2.toString(), resultModel);
		HashMap<String, String> results = new HashMap<String, String>();
		try {
			com.hp.hpl.jena.query.ResultSet ns = qe2.execSelect();
			while (ns.hasNext()) {
				QuerySolution soln = ns.nextSolution();
				results.put("Nombre", soln.getLiteral("?Name").toString());
				results.put("Código Postal", soln.getLiteral("?PostCode").toString());
				results.put("Domicilio", soln.getLiteral("?Street").toString());
				results.put("Municipio", soln.getLiteral("?Locality").toString());
				results.put("Teléfono", soln.getResource("?Telephone").toString());
				results.put("Latitud", soln.getLiteral("?Lat").toString());
				results.put("Longitud", soln.getLiteral("?Long").toString());
			}
		} finally {
			qe2.close();
		}
		return results;
	}
	
	
	public static ArrayList<GeoResource> retrievePharmacyAround(String uri, int radius) {
		
		HashMap<String, String> resource = describeUri(uri);

		String latitude = null, longitude = null;
		String lat = "Latitud";
		String lon = "Longitud";
		for(Entry<String, String> e : resource.entrySet()){
			if(e.getKey() == lat){
				latitude = e.getValue();
			}
			if(e.getKey() == lon){
				longitude = e.getValue();
			}
			
		}
		System.out.println(latitude);
		System.out.println(longitude);
		ArrayList<GeoResource> around = findPharmacyAround(latitude, longitude , radius);
		return around;
	
	}
	
	public static ArrayList<GeoResource> findPharmacyAround(String latitude, String longitude, int radius) {
		
		Model model = loadRDFFile();

		// radius is specified in meters, but to make the query, we have to
		// divide the radius by 100.000
		double convertedRadius = Double.valueOf(radius) / 100000;

		StringBuilder sparqlQuery = new StringBuilder();
		sparqlQuery.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> ");
		sparqlQuery.append("PREFIX org: <http://www.w3.org/TR/vocab-org/> ");
		sparqlQuery.append("PREFIX vCard: <http://www.w3.org/TR/vcard-rdf/> ");
		sparqlQuery.append("PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#> ");

		sparqlQuery.append("SELECT ?organizacion ?title");
		sparqlQuery.append("{ ");
		sparqlQuery.append("  ?organizacion a org:Organization . ");
		sparqlQuery.append("  ?organizacion org:hasRegisteredSite ?site . ");
		sparqlQuery.append("  ?site a org:Site . ");
		sparqlQuery.append("  ?site vCard:Name ?title. ");
		sparqlQuery.append("  ?site org:siteAddress ?org . ");
		sparqlQuery.append("  ?org a vCard:Organization . ");
		sparqlQuery.append("  ?org geo:lat ?lat . ");
		sparqlQuery.append("  ?org geo:long ?long  . ");
		sparqlQuery.append("FILTER(xsd:double(?lat) - xsd:double('").append(latitude).append("') <= ").append(convertedRadius);
		sparqlQuery.append("  && xsd:double('").append(latitude).append("') - xsd:double(?lat) <= ").append(convertedRadius);
		sparqlQuery.append("  && xsd:double(?long) - xsd:double('").append(longitude).append("') <= ").append(convertedRadius);
		sparqlQuery.append("  && xsd:double('").append(longitude).append("') - xsd:double(?long) <= ").append(convertedRadius).append(" ). ");
		sparqlQuery.append("}");
		
		

		ArrayList<GeoResource> uris = new ArrayList<GeoResource>();

		QueryExecution qe = QueryExecutionFactory.create(sparqlQuery.toString(), model);
		try {
			ResultSet results = qe.execSelect();
			for (; results.hasNext();) {
				QuerySolution sol = (QuerySolution) results.next();
				GeoResource resource = new GeoResource();
				resource.setUri(sol.getResource("?organizacion").getURI().toString());
				resource.setName(sol.getLiteral("?title").toString());
				uris.add(resource);		
			}
		} finally {
			qe.close();
		}

		return uris;
	}
	
	

	protected static Model loadRDFFile() {
		return RDFDataMgr.loadModel("pharmacy.rdf");
	}
	
	
	public static void main(String[] args){
		ArrayList<GeoResource> pharmacyURIs = retrievePharmacyAround("http://taro.ull.es/resource/pharmacy/BONNET_PEREZ_ANDRES",500);
		for(int i = 0; i<pharmacyURIs.size(); i++){
			System.out.println(pharmacyURIs.get(i).getName());
			System.out.println(pharmacyURIs.get(i).getUri());
		}
	}
//
//	public static void main(String[] args){
//		retrieveCenterInfo("http://es.dbpedia.org/resource/Hospital_Universitario_de_Canarias");
//	}
}