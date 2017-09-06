package dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.Courses;
import model.Groups;
import model.Specialty;

@Component
public class GroupsManager {

	@Autowired
	EntityManager entityManager;

	public List<Object[]> getGroupsAll() {
		Session session = this.entityManager.unwrap(Session.class);

		List<Object[]> persons = session.createNativeQuery("select * from chpoco.groups").getResultList();

		return persons;
	}

	public List<Object[]> getGroupsName(String groupsName) {
		Session session = this.entityManager.unwrap(Session.class);

		List<Object[]> persons = session.createNativeQuery("select * from chpoco.groups where groups_name = :groups_name").setParameter("groups_name", groupsName).getResultList();

		return persons;
	}

	public void addGroups(Courses courses, Specialty specialty, String groupName) {

		Groups groups = new Groups();
		groups.setGroupName(groupName);
		groups.setCourses(courses);
		groups.setSpecialty(specialty);

		Session session = this.entityManager.unwrap(Session.class);
		session.beginTransaction();

		session.save(groups);

		session.getTransaction().commit();
		session.close();
	}

	public Groups getGroups(long groupsId) {
		Session session = this.entityManager.unwrap(Session.class);
		Groups groups = session.get(Groups.class, groupsId);
		return groups;
	}

	public List<Object[]> getGroupsCourses(long idCourses, long idSpecialty) {
		Session session = this.entityManager.unwrap(Session.class);
		List<Object[]> persons = session.createNativeQuery("select * from chpoco.groups where id_courses = :idCourses and id_specialty= :idSpecialty").setParameter("idCourses", idCourses).setParameter("idSpecialty", idSpecialty).getResultList();
		return persons;
	}

}
