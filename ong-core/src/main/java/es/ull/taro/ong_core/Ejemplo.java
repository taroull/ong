package es.ull.taro.ong_core;

import java.util.ArrayList;

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
}