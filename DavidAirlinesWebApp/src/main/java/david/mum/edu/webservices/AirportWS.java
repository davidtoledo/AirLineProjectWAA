package david.mum.edu.webservices;

import cs545.airline.dao.AirportDao;
import cs545.airline.model.Airport;
import cs545.airline.service.AirportService;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/airport")
public class AirportWS {
    
    private AirportService airportService = new AirportService(new AirportDao());

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("findAll")            
    public List <Airport> findAll() {
        // return airportService.findAll();
        return getSimulatedData();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("create")            
    public String create(Airport airport) {
        airportService.create(airport);
        return "OK";
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("delete")            
    public String delete(Airport airport) {
        airportService.delete(airport);
        return "OK";
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("update")            
    public Airport update(Airport airport) {
        return airportService.update(airport);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("find")                
    public Airport find(long id) {
        return airportService.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("findByCode")                    
    public Airport findByCode(String airportcode) {
        return airportService.findByCode(airportcode);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("findByArrival")                        
    public List<Airport> findByArrival(long flightID) {
        return airportService.findByArrival(flightID);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("findByDeparture")                            
    public List<Airport> findByDeparture(long flightID) {
        return airportService.findByDeparture(flightID);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("findByCity")                                
    public List<Airport> findByCity(String city) {
        return airportService.findByCity(city);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("findByCountry")                                    
    public List<Airport> findByCountry(String country) {
        return airportService.findByCountry(country);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("findByName")
    public List<Airport> findByName(String name) {
        return airportService.findByName(name);
    }
    
        
    /****************************************
     * Return simulated data
     */
    private List getSimulatedData() {

        // Simulating return
        List <Airport> airports = new ArrayList <> ();
        Airport a = new Airport();
        a.setId(1);
        a.setAirportcode("SDU");
        a.setCity("Rio de Janeiro");
        a.setCountry("Brazil");
        a.setName("Santos Dumount");
        airports.add(a);
        
        a = new Airport();
        a.setId(2);
        a.setAirportcode("SFD");
        a.setCity("Orlando");
        a.setCountry("USA");
        a.setName("Ornando Sanford");
        airports.add(a);
        
        return airports;
    }
    
}