/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import entity.*;
import java.util.ArrayList;
import org.hibernate.*;

/**
 *
 * @author Student
 */
import java.util.*;
public class product_dao {
    public List<Products> getAll()
    {
        Session s=HibernateUtil.getSessionFactory().getCurrentSession();
        
 List<Products> lst= new ArrayList<Products>();
 try{
     s.beginTransaction();
     lst=s.createCriteria(Products.class).list();
     s.getTransaction().commit();
 }
 catch(Exception e)
 {
     e.printStackTrace();
     s.getTransaction().rollback();
    
 }
  return lst;
 }
      public List<Products> search(String keyword)
      {
          Session s=HibernateUtil.getSessionFactory().getCurrentSession();
          List<Products> lst=new ArrayList<>();
          try{
              s.beginTransaction();
              Query q=s.createQuery("select p from products p where" 
                                    + "p.name like :keyword or p.price like"
                                    + ":keyword or p.image like :keyword");
              q.setParameter("keyword", "%"+keyword+"%");
              lst=q.list();
              s.getTransaction().commit();
              return lst;
          }
          catch(Exception e)
          {
              e.printStackTrace();
              s.getTransaction().rollback();
              return null;
          }
    
          }
      }
      


