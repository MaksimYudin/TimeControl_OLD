package com.hibernate.crud.operations;

import java.util.List;
import java.util.Optional;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.russianfeature.model.Student;

public class StudentDao implements IDao<Student, Integer> {
    //static Session sessionObj;
    //static SessionFactory sessionFactoryObj;

    private Session currentSession;
    private Transaction currentTransaction;

    StudentDao() { }

    @Override
    public void deleteAllRecords() {
        try {
            List<Student> studentList = getAllData();
            for (Student st : studentList)
                delete(st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        try {
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            sessionObj.beginTransaction();

            Query queryObj = sessionObj.createQuery("DELETE FROM Student");
            queryObj.executeUpdate();

            sessionObj.getTransaction().commit();

        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
        */
    }

    @Override
    public void persist(Student student) {
        try {
            currentSession.persist(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //sessionFactoryObj = HibernateUtil.getSessionFactory();
        //sessionFactoryObj.openSession().persist(student);
    }

    @Override
    public List<Student> getAllData() {
        List<Student> studentsList = null;
        try {
            studentsList = currentSession.createQuery("from Student").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentsList;
/*
        try {

            sessionFactoryObj = HibernateUtil.getSessionFactory();
            sessionObj = sessionFactoryObj.openSession();

            studentsList = sessionObj.createQuery("from Student").list();

        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                //logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                //sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
        return studentsList;
        */
    }

    @Override
    public void delete(Student student) {
        try {
            currentSession.delete(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Student> getDataById(Integer id) {
        Student std = null;
        try {
            std = currentSession.get(Student.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(std);
    }

    @Override
    public void update(Student student) {
        try {
            currentSession.update(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //sessionFactoryObj = HibernateUtil.getSessionFactory();
        //sessionFactoryObj.openSession().update(student);
    }

    public Session openCurrentSession() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

}
