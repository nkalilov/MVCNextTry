package peaksoft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao{


    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public StudentDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void saveStudent(Long companyId, Student student) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(student);
        entityManager.persist(companyId);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void updateStudent(Long id,Student student) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Student student1 = entityManager.find(Student.class,id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setStudyFormat(student.getStudyFormat());
        entityManager.merge(student1);
        entityManager.getTransaction().commit();
        entityManager.close();
    }


    @Override
    public List<Student> getAllStudents(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        List<Student> students = entityManager.createQuery("select s from Student s").setParameter("student"+id, id).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return students; }

//        return entityManager.createQuery("SELECT s FROM Student s where s.company.companyId = : id",Student.class)
//                .setParameter("id",id).getResultList();


    @Override
    public Student getStudentById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Student student = entityManager.find(Student.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return student;
    }

    @Override
    public void deleteStudentById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Student student = entityManager.find(Student.class, id);
        student.setCourse(null);
        entityManager.remove(student);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void assignStudentToCourse(Long studentId, Long courseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Student student = entityManager.find(Student.class, studentId);
        Course course = entityManager.find(Course.class, courseId);
        student.setCourse(course);
        course.addStudents(student);
        entityManager.merge(student);
        entityManager.getTransaction().commit();
        entityManager.close();
    }


    @Override
    public List<Student> countOfStudents(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        List<Student> students =  entityManager.createQuery("SELECT COUNT(c.students.size) FROM Company c",Student.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return students;
    }

}
