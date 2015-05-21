package es.ull.taro.ong_rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ull.taro.ong_core.services.BotiquinService;
import es.ull.taro.ong_core.services.CenterService;
import es.ull.taro.ong_core.services.DBpediaService;
import es.ull.taro.ong_core.services.DisabilityService;
import es.ull.taro.ong_core.services.ElderlyService;
import es.ull.taro.ong_core.services.PharmacyService;

@Component
@Path("/")
public class OngAPI {
	
	@Autowired
	protected CenterService centerService;
	
	@Autowired
	protected PharmacyService pharmacyService;
	
	@Autowired
	protected BotiquinService botiquinService;
	
	@Autowired
	protected DisabilityService disabilityService;
	
	@Autowired
	protected ElderlyService elderlyService;
	
	@Autowired
	protected DBpediaService dbpediaService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("info")
	public Map<String, String> retrieveCenterInfo(@QueryParam(value = "uri") String uri) throws Exception {
		return dbpediaService.retrieveCenterInfo(uri);
	}
	 
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("center")
	public HashMap<String, String> findCenterByName(@QueryParam(value = "name") String name) throws Exception {
		return centerService.find(name);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("pharmacy")
	public HashMap<String, String> findPharmacyByName(@QueryParam(value = "name") String name) throws Exception {
		return pharmacyService.find(name);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("botiquin")
	public HashMap<String, String> findBotiquinByName(@QueryParam(value = "name") String name) throws Exception {
		return botiquinService.find(name);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("disability")
	public HashMap<String, String> findDisabilityByName(@QueryParam(value = "name") String name) throws Exception {
		return disabilityService.find(name);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("elderly")
	public HashMap<String, String> findElderlyByName(@QueryParam(value = "name") String name) throws Exception {
		return elderlyService.find(name);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("pharmacyuri")
	public Map<String, String> findPharmacyByUri(@QueryParam(value = "uri") String uri) throws Exception {
		return pharmacyService.describeUri(uri);
	}
	
	
	
	
}
