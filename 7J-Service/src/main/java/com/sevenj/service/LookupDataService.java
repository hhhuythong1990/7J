package com.sevenj.service;

import java.util.List;

import com.sevenj.model.Destination;

public interface LookupDataService {
  public List<Destination> getDestinationList() throws Exception;
  public Destination getDestinationById(String id) throws Exception;
}
