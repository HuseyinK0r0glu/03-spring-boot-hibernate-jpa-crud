package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    // define field for entity manager
    private EntityManager entityManager;


    // Inject entity manager using constructor ınjection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    // no need to add @transactional since we are doing only a query(sorgu)
    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class,id);
    }

    // no need to add @transactional since we are doing only a query(sorgu)
    @Override
    public List<Student> findAll() {

        // sort by last name    for order default is ascending but we can change that
        // "order by lastName asc" asc for ascending and desc for descending
        //TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student order by lastName",Student.class);

        // create a query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student",Student.class);
        // return query results
        return theQuery.getResultList();
    }


    @Override
    public List<Student> findByLastName(String theLastName) {
        // create query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName=:theData",Student.class);
        // set query parameters
        theQuery.setParameter("theData",theLastName);
        // return query results
        return theQuery.getResultList();
    }

    @Override
    // use transactional since we are performing an update
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void updateMultiple() {
        int numRowsUpdated = entityManager.createQuery("UPDATE Student SET lastName = 'Tester'").executeUpdate();
        System.out.println("Number of the rows updated:" + numRowsUpdated);
    }

    @Override
    @Transactional
    public void delete(int id) {
        // retrieve the student
        Student theStudent = entityManager.find(Student.class,id);
        // delete the student
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAllStudents() {
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return numRowsDeleted;
    }
}
