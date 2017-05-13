package david.mum.edu.servlet;

import cs545.airline.dao.AirlineDao;
import cs545.airline.dao.FlightDao;
import cs545.airline.model.Airline;
import cs545.airline.service.AirlineService;
import cs545.airline.service.FlightService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author David Costa
 */
public class InitServlet extends HttpServlet {
    
    private static Logger logger = Logger.getLogger(InitServlet.class);
    private FlightService flightService;
    private AirlineService airlineService;

    @Override
    public void init()  throws ServletException {
        flightService = new FlightService(new FlightDao());
        airlineService = new AirlineService(new AirlineDao());
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        StringBuilder sb = new StringBuilder();
        /*sb.append("List all the flights:");
        
        List<Flight> flights = flightService.findAll();
        sb.append(String.format("%-9s%-31s%-31s", "Flight:", "Departs:", "Arrives:") + "<br>");
        
        for (Flight flight : flights) {
            
                sb.append(String.format("%-7s  %-12s %7s %8s  %-12s %7s %8s", flight.getFlightnr(),
                          flight.getOrigin().getCity(), flight.getDepartureDate(), flight.getDepartureTime(),
                          flight.getDestination().getCity(), flight.getArrivalDate(), flight.getArrivalTime() + "<br>")
                );
        } */
        
        sb.append("List all the airlines:<br><br>");
        List<Airline> airlines = airlineService.findAll();
        
        for (Airline airline : airlines) {
            sb.append( airline.getId() + " " + airline.getName() + "<br>");
        }
        
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InitServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InitServlet at " + request.getContextPath() + "</h1>");
            out.println(sb.toString());
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
}