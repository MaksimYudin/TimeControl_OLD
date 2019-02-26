package com.hibernate.crud.operations;

import org.russianfeature.model.Student;

import java.util.List;
import java.util.Optional;

public class StudentService {
    private static StudentDao studentDao;

    public StudentService() {
        studentDao = new StudentDao();
    }

    public void persist(Student entity) {
        studentDao.openCurrentSessionWithTransaction();
        studentDao.persist(entity);
        studentDao.closeCurrentSessionWithTransaction();
    }

    public void update(Student entity) {
        studentDao.openCurrentSessionWithTransaction();
        studentDao.update(entity);
        studentDao.closeCurrentSessionWithTransaction();
    }

    public void delete(Student entity) {
        studentDao.openCurrentSessionWithTransaction();
        studentDao.delete(entity);
        studentDao.closeCurrentSessionWithTransaction();
    }

    public Student getDataById(Integer id) {
        studentDao.openCurrentSession();
        Optional<Student> optStudent = studentDao.getDataById(id);
        studentDao.closeCurrentSession();
        return optStudent.orElseThrow(() -> new NullPointerException());
    }

    public List<Student> getAllData() {
        studentDao.openCurrentSession();
        List<Student> studentList = studentDao.getAllData();
        studentDao.closeCurrentSession();

        return studentList;
    }
}
