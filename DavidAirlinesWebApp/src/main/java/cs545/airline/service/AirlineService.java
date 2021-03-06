package cs545.airline.service;

import java.util.List;

import cs545.airline.dao.AirlineDao;
import cs545.airline.model.Airline;
import cs545.airline.model.Flight;

public class AirlineService {
	
	// These services should be evaluated to reconsider which methods should be public 

	private AirlineDao airlineDao;
	
	public AirlineService(AirlineDao airlineDao) {
		this.airlineDao=airlineDao;
	}

	public void create(Airline airport) {
		airlineDao.create(airport);
	}

	public void delete(Airline airport) {
		airlineDao.delete(airport);
	}

	public Airline update(Airline airport) {
		return airlineDao.update(airport);
	}
		
	public Airline find(long id) {
		return airlineDao.findOne(id);
	}

	public Airline findByName(String name) {
		return airlineDao.findOneByName(name);
	}

	public List<Airline> findByFlight(long id) {
		return airlineDao.findByFlight(id);
	}

	public List<Airline> findAll() {
		return airlineDao.findAll();
	}	
}
