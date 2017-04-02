package com.sevenj.dal.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.mysql.jdbc.StringUtils;
import com.sevenj.dal.dao.UserDAO;
import com.sevenj.model.User;

public class UserDAOImpl extends BaseDAOImpl<User> implements UserDAO{

	public User getUser(String username, String password) throws Exception {
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery("FROM User u where u.username = :username and u.password = :password");
			query.setParameter("username", username);
			query.setParameter("password", password);
			List<User> users = query.list();
			if (users != null && users.size() > 0){
				return users.get(0);
			}
		}finally{
			closeSession();
		}
		return null;
	}
	public List<User> getUserList(int startIndex, int pageSize,String sortingColumn) throws Exception {
		List<User> result=null;
			try{
				session = sessionFactory.openSession();
				String hql = "FROM User";
				if (!StringUtils.isNullOrEmpty(sortingColumn)){
					hql += " Order by " + sortingColumn;
				}
				Query query = session.createQuery(hql);
				query.setFirstResult(startIndex);
				query.setMaxResults(pageSize);
				result = query.list();
		}finally{
			closeSession();
		}
		return result;
	}


}
