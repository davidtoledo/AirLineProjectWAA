package david.mum.edu.webservices;

import cs545.airline.dao.AirplaneDao;
import cs545.airline.model.Airplane;
import cs545.airline.service.AirplaneService;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/airplane")
public class AirplaneWS {
    
    private AirplaneService airplaneService = new AirplaneService(new AirplaneDao());
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("findAll")        
    public List <Airplane> findAll() {
        return airplaneService.findAll();
        // return getSimulatedData();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("create")    
    public String create(Airplane airplane) {
        airplaneService.create(airplane);
        return "OK";
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("delete")    
    public String delete(Airplane airplane) {
        airplaneService.delete(airplane);
        return "OK";
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("update")    
    public Airplane update(Airplane airplane) {
        return airplaneService.update(airplane);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("find")        
    public Airplane find(long id) {
        return airplaneService.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("findBySrlnr")        
    public Airplane findBySrlnr(String serialno) {
        return airplaneService.findBySrlnr(serialno);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("findByFlight")        
    public List<Airplane> findByFlight(long id) {
        return airplaneService.findByFlight(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("findByModel")            
    public List<Airplane> findByModel(String model) {
        return airplaneService.findByModel(model);
    }
        
    /****************************************
     * Return simulated data
     */
    private List getSimulatedData() {

        // Simulating return
        List <Airplane> airplanes = new ArrayList <> ();
        Airplane a = new Airplane();
        a.setId(1);
        a.setModel("Boing 747");
        a.setSerialnr("ABCD1000");
        a.setCapacity(100);
        airplanes.add(a);

        a = new Airplane();
        a.setId(2);
        a.setModel("Boing 757");
        a.setSerialnr("ABCD1010");
        a.setCapacity(200);
        airplanes.add(a);

        a = new Airplane();
        a.setId(2);
        a.setModel("Boing 787");
        a.setSerialnr("ABCD8080");
        a.setCapacity(300);
        airplanes.add(a);
        
        return airplanes;
    }
    
}