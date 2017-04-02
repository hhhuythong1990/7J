package com.sevenj.dal.dao;

import java.util.List;
import com.sevenj.model.Destination;

public interface DestinationDAO extends BaseDAO<Destination> {
	public List<Destination> getAllDestination(String sortName) throws Exception;
	public void insertDestination(Destination destination) throws Exception;
	public void deleteDestination(String destinationID) throws Exception;
	public void updateDestination(Destination destination) throws Exception;
	public Destination getDestination(String destinationID) throws Exception;
}
