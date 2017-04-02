package com.sevenj.service.impl;

import java.util.List;

import com.sevenj.dal.dao.DestinationDAO;
import com.sevenj.dal.dao.impl.DestinationDAOImpl;
import com.sevenj.model.Destination;
import com.sevenj.service.DestinationService;

public class DestinationServiceImpl implements DestinationService {

	private DestinationDAO destinationDAO;
	
	public DestinationServiceImpl(DestinationDAO dao) {
		this.destinationDAO = dao;
	}
	
	public DestinationServiceImpl() {
		this.destinationDAO =  new DestinationDAOImpl();
	}
	@Override
	public List<Destination> getAllDestination(String sortName)
			throws Exception {
		// TODO Auto-generated method stub
		return destinationDAO.getAllDestination(sortName);
	}

	@Override
	public void createDestination(Destination destination) throws Exception {
		// TODO Auto-generated method stub
		destinationDAO.insertDestination(destination);
	}

	@Override
	public void deleteDestination(String destinationID) throws Exception {
		// TODO Auto-generated method stub
		destinationDAO.deleteDestination(destinationID);
	}

	@Override
	public void updateDestination(Destination destination) throws Exception {
		// TODO Auto-generated method stub
		Destination  oldDestination = destinationDAO.getDestination(destination.getDestinationCode());
		oldDestination.setDestinationDescription(destination.getDestinationDescription());
		oldDestination.setSortOrder(destination.getSortOrder());
		destinationDAO.updateDestination(oldDestination);
	}

	@Override
	public Destination getDestination(String destinationID) throws Exception {
		// TODO Auto-generated method stub
		return destinationDAO.getDestination(destinationID);
	}

}
