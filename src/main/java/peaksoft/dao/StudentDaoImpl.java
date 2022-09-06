package peaksoft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
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
@EnableTransactionManagement
public class StudentDaoImpl implements StudentDao{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void saveStudent(Long companyId, Student student) {
        Company company = entityManager.find(Company.class,companyId);
        company.addStudent(student);
        student.setCompany(company);
        entityManager.persist(student);
    }

    @Override
    public void updateStudent(Long id,Student student) {
        Student student1 = entityManager.find(Student.class,id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setStudyFormat(student.getStudyFormat());
        entityManager.merge(student1);
    }


    @Override
    public List<Student> getAllStudents(Long id) {
        List<Student> students = entityManager.createQuery("select s from Student s where s.company.companyId =:id",Student.class).setParameter("id", id).getResultList();
        entityManager.getTransaction().commit();

        return students;
    }

//        return entityManager.createQuery("SELECT s FROM Student s where s.company.companyId = : id",Student.class)
//                .setParameter("id",id).getResultList();


    @Override
    public Student getStudentById(Long id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.getTransaction().commit();
        return student;
    }

    @Override
    public void deleteStudentById(Long id) {
        Student student = entityManager.find(Student.class, id);
        student.setCourse(null);
        entityManager.remove(student);
    }

    @Override
    public void assignStudentToCourse(Long studentId, Long courseId) {
        Student student = entityManager.find(Student.class, studentId);
        Course course = entityManager.find(Course.class, courseId);
        student.setCourse(course);
        course.addStudents(student);
        entityManager.merge(student);
    }


    @Override
    public List<Student> countOfStudents(Long id) {
        List<Student> students =  entityManager.createQuery("SELECT COUNT(c.students.size) FROM Company c",Student.class).getResultList();
        entityManager.getTransaction().commit();
        return students;
    }

}
