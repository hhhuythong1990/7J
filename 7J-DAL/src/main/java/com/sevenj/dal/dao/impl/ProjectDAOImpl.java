package com.sevenj.dal.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.mysql.jdbc.StringUtils;
import com.sevenj.dal.dao.ProjectDAO;
import com.sevenj.dal.util.HibernateUtil;
import com.sevenj.dal.util.Utilities;
import com.sevenj.model.Project;
import com.sevenj.model.Project;

public class ProjectDAOImpl extends BaseDAOImpl<Project> implements ProjectDAO{

	public void insertProject(Project project) throws Exception {
		String projectId = null;
		try{
			Calendar cal = Calendar.getInstance();
			project.setCreatedDate(cal.getTime());
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			project.setProjectId(Utilities.uniqueid());
			projectId = (String)session.save(project);			
			tx.commit();
		}catch(Exception e){
			tx.rollback();
			throw e;
		}finally{
			closeSession();
		}
		project.setProjectId(projectId);
	}

	public List<Project> getAllProjects(String sortName) throws Exception {
		List<Project> projects;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			String hql = "FROM Project";
			if (!StringUtils.isNullOrEmpty(sortName)){
				hql += " Order by " + sortName;  
			}
			projects = (List<Project>)session.createQuery(hql).list();
		}finally{
			closeSession();
		}
		return projects;
	}

	public void deleteProject(String projectId) throws Exception {
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query =session.createQuery("delete from Project p where p.projectId = :id");
			query.setParameter("id", projectId);
			query.executeUpdate();
			tx.commit();
		}catch(Exception e){
			tx.rollback();
			throw e;
		}finally{
			closeSession();
		}
	}

	public void updateProject(Project project) throws Exception {
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(project);
			tx.commit();
		}catch(Exception e){
			tx.rollback();
			throw e;
		}finally{
			closeSession();
		}
	}

	public Project getProject(String projectId) throws Exception {
		Project project = null;
		try{
			session = sessionFactory.openSession();
			project = (Project)session.get(Project.class, projectId);
		}finally{
			closeSession();
		}
		return project;
	}

}
