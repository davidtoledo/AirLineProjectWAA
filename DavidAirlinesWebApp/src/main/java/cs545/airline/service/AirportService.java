package cs545.airline.service;

import java.util.List;

import cs545.airline.dao.AirportDao;
import cs545.airline.model.Airport;

public class AirportService {

	// These services should be evaluated to reconsider which methods should be public 

	private AirportDao airportDao;
	
	public AirportService(AirportDao airportDao) {
		this.airportDao = airportDao;
	}
	
	public void create(Airport airport) {
		airportDao.create(airport);
	}

	public void delete(Airport airport) {
		airportDao.delete(airport);
	}

	public Airport update(Airport airport) {
		return airportDao.update(airport);
	}
		
	public Airport find(long id) {
		return airportDao.findOne(id);
	}

	public Airport findByCode(String airportcode) {
		return airportDao.findOneByCode(airportcode);
	}

	public List<Airport> findByArrival(long flightID) {
		return airportDao.findByArrival(flightID);
	}

	public List<Airport> findByDeparture(long flightID) {
		return airportDao.findByDeparture(flightID);
	}

	public List<Airport> findByCity(String city) {
		return airportDao.findByCity(city);
	}

	public List<Airport> findByCountry(String country) {
		return airportDao.findByCountry(country);
	}

	public List<Airport> findByName(String name) {
		return airportDao.findByName(name);
	}

	public List<Airport> findAll() {
		return airportDao.findAll();
	}
}