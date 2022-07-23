package com.model;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.entity.Faculty;

@Stateless
@TransactionManagement(value = TransactionManagementType.BEAN)
public class FacultyModel implements FacultyRemote 
{

	public String insertData(Faculty F) throws Exception {
	
		    EntityManagerFactory emf = Persistence.createEntityManagerFactory("klu");
		    EntityManager em1 = emf.createEntityManager();
		    em1.getTransaction().begin();
		    em1.persist(F);
		    em1.getTransaction().commit();
		    em1.close();
		    emf.close();
		    return "Record inserted succesfylly...";
		  }

		 
	public List<Faculty> readAll() throws Exception {
		 
		    EntityManagerFactory emf = Persistence.createEntityManagerFactory("klu");
		    EntityManager em = emf.createEntityManager();
		    em.getTransaction().begin();
		    Query qry = em.createQuery("select f from Faculty f");
		    @SuppressWarnings("unchecked")
		    List<Faculty> L = qry.getResultList();
		    em.getTransaction().commit();
		    em.close();
		    emf.close();
		    return L;
		  

		  
	}


	public Faculty findData(int id) throws Exception {
		
		    EntityManagerFactory emf = Persistence.createEntityManagerFactory("klu");
		    EntityManager em = emf.createEntityManager();
		    em.getTransaction().begin();
		    Faculty F = em.find(Faculty.class, id);
		    em.getTransaction().commit();
		    em.close();
		    emf.close();
		    return F;
		  }


	public String updateData(int id, Faculty data) throws Exception {

		    EntityManagerFactory emf = Persistence.createEntityManagerFactory("klu");
		    EntityManager em2 = emf.createEntityManager();
		    em2.getTransaction().begin();
		    Faculty E = em2.find(Faculty.class, id);
		    E.setId(data.getId());
		    E.setName(data.getName());
		    E.setDsg(data.getDsg());
		    E.setAch(data.getAch());
		    E.setCourse(data.getCourse());
		   
		    em2.getTransaction().commit();
		    em2.close();
		    emf.close();
		    return "Record updated successfully...";
		  }

	public String deleteData(int id) throws Exception {
	  
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("klu");
	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    Faculty F = em.find(Faculty.class, id);
	    em.remove(F);
	    em.getTransaction().commit();
	    em.close();
	    emf.close();
	    return "Record deleted successfully...";
	  }


	@Override
	public Faculty ValidateData(int id,String password) throws Exception {
		 
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("klu");
	    EntityManager entityManager = entityManagerFactory.createEntityManager();
	    entityManager.getTransaction().begin();
	    Query qry=entityManager.createQuery("select u from Faculty u where id=? and  password=?");
	    qry.setParameter(1, id);
	    qry.setParameter(2, password);
	    
	   Faculty f = null;
	    
	    if (qry.getResultList().size()==1) 
	    {
	    	f =  (Faculty) qry.getSingleResult();
	    }
	    
	    entityManager.getTransaction().commit();
	    
	    entityManager.close();
	    entityManagerFactory.close();
	    
	    return f;
	}

	@Override
	public Faculty readOne(int id) throws Exception {
		 
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("klu");
	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    Faculty F = em.find(Faculty.class, id);
	    em.getTransaction().commit();
	    em.close();
	    emf.close();
	    return F;
	  }
	  

	  
	}

  
