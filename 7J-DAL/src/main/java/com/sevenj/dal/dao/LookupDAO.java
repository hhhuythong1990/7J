package com.sevenj.dal.dao;

import java.util.List;
import java.util.Map;

import com.sevenj.model.Lookup;

public interface LookupDAO extends BaseDAO<Lookup>{
   public List<Lookup> getDataList(String typeName) throws Exception;
   public Map<Integer,String> getDataMap() throws Exception;
   public Lookup getObjLookupByDescription(String description) throws Exception;
}
