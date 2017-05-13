package david.mum.edu.webservices;

import cs545.airline.dao.FlightDao;
import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.FlightService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/flight")
public class FlightWS {
    
    private FlightService flightService = new FlightService(new FlightDao());

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("findAll")
    public List<Flight> findAll() {
        return flightService.findAll();
        // return getSimulatedData();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("create")                
    public String create(Flight flight) {
        flightService.create(flight);
        return "OK";
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("delete")                
    public String delete(Flight flight) {
        flightService.delete(flight);
        return "OK";
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("update")                    
    public Flight update(Flight flight) {
        return flightService.update(flight);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("find")                        
    public Flight find(long flightID) {
        return flightService.find(flightID);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("findByNumber")                            
    public List<Flight> findByNumber(String flightnr) {
        return flightService.findByNumber(flightnr);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("findByAirline")                                
    public List<Flight> findByAirline(long airlineID) {
        return flightService.findByAirline(airlineID);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("findByOrigin")                                
    public List<Flight> findByOrigin(long airportID) {
        return flightService.findByOrigin(airportID);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("findByDestination")                                
    public List<Flight> findByDestination(long airportID) {
        return flightService.findByDestination(airportID);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("findByArrival")                                    
    public List<Flight> findByArrival(long airplaneID) {
        return flightService.findByArrival(airplaneID);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("findByArrivalDate")                                        
    public List<Flight> findByArrival(Date datetime) {
        return flightService.findByArrival(datetime);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("findByDeparture")                                                
    public List<Flight> findByDeparture(Date datetime) {
        return flightService.findByDeparture(datetime);
    }
 
    /****************************************
     * Return simulated data
     */
    private List getSimulatedData() {

        // Simulating return
        Airline airline = new Airline();
        airline.setId(1);
        airline.setName("Alegiant");
        
        Airplane airplane = new Airplane();
        airplane.setId(1);
        airplane.setModel("BING 747");
        airplane.setCapacity(100);
        airplane.setSerialnr("AAA1000");
        
        Airport airport = new Airport();
        airport.setId(1);
        airport.setCity("Rio de Janeiro");
        airport.setCountry("Brazil");
        airport.setName("SDU");
        
        List <Flight> flights = new ArrayList <> ();
        Flight f = new Flight();
        f.setId(1);
        f.setAirline(airline);
        f.setAirplane(airplane);
        f.setArrivalDate("28 AUG 2017");
        f.setArrivalTime("12:00");
        f.setDepartureDate("28 AUG 2017");
        f.setDepartureTime("10:00");
        f.setDestination(airport);
        f.setFlightnr("8854");
        f.setOrigin(airport);
        
        flights.add(f);
        
        return flights;
    }
    
}