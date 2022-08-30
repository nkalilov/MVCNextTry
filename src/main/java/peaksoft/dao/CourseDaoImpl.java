package peaksoft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CourseDaoImpl implements CourseDao{

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public CourseDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void saveCourse(Long companyId, Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
//        Company company = entityManager.find(Company.class,companyId);
//        company.addCourse(course);
//        course.setCompany(company);
        entityManager.persist(course);
        entityManager.getTransaction().commit();
        entityManager.close();

//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction entityTransaction = entityManager.getTransaction();
//        entityTransaction.begin();
//        entityManager.persist(company);
//        entityManager.getTransaction().commit();
//        entityManager.close();
    }

    @Override
    public void updateCourse(Long id,Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Course course1 = entityManager.find(Course.class,id);
        course1.setCourse_name(course.getCourse_name());
        course1.setDateOfStart(course.getDateOfStart());
        course1.setDuration(course.getDuration());
        course1.setImage(course.getImage());
        entityManager.merge(course1);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Course> getAllCourse(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        List<Course> courses = entityManager.createQuery("select c from Course c").getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return courses;
    }

    @Override
    public Course getCourseById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Course course = entityManager.find(Course.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return course;
    }

    @Override
    public void deleteCourseById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
