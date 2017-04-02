package com.sevenj.service;

import java.util.List;
import java.util.Map;

import com.sevenj.model.Lookup;

public interface LookupService {
   public List<Lookup> getDataList(String typeName) throws Exception;
   public Map<Integer,String> getDataMap() throws Exception;
   public Lookup getObjLookupByDescription(String description) throws Exception;
   public void addNewLookup(Lookup lookup) throws Exception;
}
