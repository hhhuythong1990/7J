package com.sevenj.dal.dao;

import java.util.List;
import java.util.Map;

public interface BaseDAO<T> {
   public List<T> executeAndGetList(String hql, Map<String, Object> parameters) throws Exception;
   public List<T> getListByPrimaryKeys(Class<T> clazz, String pkProperty, List<?> keys) throws Exception;
   public void createEntitiesBatch(List<T> listEntities) throws Exception;
   public T getEntityById(Class clazz, Object id) throws Exception;
   public void update(T entity) throws Exception;
   public void updateEntityBatch(List<T> listEntities) throws Exception;
   public List<T> getList(Class<T> clazz) throws Exception;
   public long getCount(String hql) throws Exception;
   public void createEntity(T entity) throws Exception;
}
