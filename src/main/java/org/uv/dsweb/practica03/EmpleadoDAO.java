/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.dsweb.practica03;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Transient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ian
 */
public class EmpleadoDAO implements Serializable{
    
    SessionFactory sf = null;
    @Transient
    Transaction tra = null;
    Session session = null;

    public boolean save(EmpleadoEntity empleado) {
        boolean success = false;
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.getCurrentSession();
            tra = session.beginTransaction();
            session.save(empleado);
            tra.commit();
            session.close();
            success = true;
        }
        catch(HibernateException e) {
            if (tra != null) {
                tra.rollback();
            }
        } 
        return success;
    }

    public boolean update(EmpleadoEntity empleado, Long id) {
        boolean success = false;
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.getCurrentSession();
            tra = session.beginTransaction();
            EmpleadoEntity emp = session.get(EmpleadoEntity.class, id);
            if (emp != null){
                
                emp.setName(empleado.getName());
                emp.setAddress(empleado.getAddress());
                emp.setPhone(empleado.getPhone());
                session.update(emp);
                tra.commit();
                success = true;
            }
            session.close();
        }
        catch(HibernateException e) {
            if (tra != null) {
                tra.rollback();
            }
            System.out.println(e);
        } 
        return success;
    }

    public boolean delete(Long id) {
        boolean success = false;
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.getCurrentSession();
            tra = session.beginTransaction();
            EmpleadoEntity emp = session.get(EmpleadoEntity.class, id);
            if (emp != null){
                session.delete(emp);
                tra.commit();
                success = true;
            }
            session.close();
        }
        catch(HibernateException e) {
            if (tra != null) {
                tra.rollback();
                System.out.println(e);
            }
        } 
        return success;
    }

    public EmpleadoEntity findByID(Long id) {
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.getCurrentSession();
            tra = session.beginTransaction();
            EmpleadoEntity emp = session.get(EmpleadoEntity.class, id);
            session.close();
            return emp;
        }
        catch(HibernateException e) {
            if (tra != null) {
                tra.rollback();
            }
            return null;
        } 
    }

    public List<EmpleadoEntity> findAll() {
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.getCurrentSession();
            tra = session.beginTransaction();
            List<EmpleadoEntity> emp = session.createQuery("From EmpleadoEntity emp order by emp.id").list();
            tra.commit();
            session.close();
            return emp;
        }
        catch(HibernateException e){
            if (tra != null) {
                tra.rollback();
            }
            return null;
        }
    }
}
