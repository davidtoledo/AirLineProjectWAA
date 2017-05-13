package david.mum.edu.webservices;

import cs545.airline.dao.AirlineDao;
import cs545.airline.model.Airline;
import cs545.airline.service.AirlineService;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/airline")
public class AirlineWS {
    
    private AirlineService airlineService = new AirlineService(new AirlineDao());
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("findAll")
    public List <Airline> findAll() throws Exception {
        
        // Calling the service layer
        return airlineService.findAll();
        //return getSimulatedData();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("create")    
    public String create(Airline airline) {
        airlineService.create(airline);
        return "OK";
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("delete")        
    public String delete(Airline airline) {
        airlineService.delete(airline);
        return "OK";
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("update")            
    public Airline update(Airline airline) {
        return airlineService.update(airline);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("find")            
    public Airline find(long id) {
        return airlineService.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("findByName")            
    public Airline findByName(String name) {
        return airlineService.findByName(name);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("findByFlight")            
    public List <Airline> findByFlight(long id) {
        return airlineService.findByFlight(id);
    }
    
    /****************************************
     * Return simulated data
     */
    private List getSimulatedData() {

        // Simulating return
        List <Airline> airlines = new ArrayList <> ();
        Airline a = new Airline();
        a.setId(1);
        a.setName("Alegiant");
        airlines.add(a);
        
        a = new Airline();
        a.setId(2);
        a.setName("GOL Brazil");
        airlines.add(a);
        
        return airlines;
    }
    
}