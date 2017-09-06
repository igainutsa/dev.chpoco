package dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.Courses;

@Component
public class CoursesManager {

	@Autowired
	EntityManager entityManager;

	public List<Object[]> getCoursesAll() {
		Session session = this.entityManager.unwrap(Session.class);

		List<Object[]> persons = session.createNativeQuery("select * from chpoco.courses").getResultList();

		return persons;
	}

	public Courses getCoursesId(long coursesId) {
		Session session = this.entityManager.unwrap(Session.class);
		Courses courses = session.get(Courses.class, coursesId);
		return courses;
	}

	public void addCourses(String coursesName) {

		Courses courses = new Courses();
		courses.setCoursesName(coursesName);

		Session session = this.entityManager.unwrap(Session.class);
		session.beginTransaction();

		session.save(courses);

		session.getTransaction().commit();
		session.close();
	}

}
