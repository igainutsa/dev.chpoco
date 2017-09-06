package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.Groups;
import model.User;

@Component
public class UserManager {

	@Autowired
	EntityManager entityManager;

	public List<Object[]> getUserGroup(long idGroup) {

		Session session = this.entityManager.unwrap(Session.class);

		List<Object[]> persons = session
				.createNativeQuery(
						"select u.id_user, u.user_name, u.user_surname, g.groups_name, c.courses_name, s.specialty_name from user u inner join groups g on u.id_groups = g.id_groups inner join courses c on g.id_courses = c.id_courses inner join specialty s on g.id_specialty = s.id_specialty where u.id_groups = :id_groups")
				.setParameter("id_groups", idGroup).getResultList();

		return persons;
	}

	public User getUser(long userId) {
		Session session = this.entityManager.unwrap(Session.class);
		User user = session.get(User.class, userId);
		return user;
	}

	public void deleteUser(long userId) {

		Session session = this.entityManager.unwrap(Session.class);

		User personToDelete = (User) session.get(User.class, userId);

		session.beginTransaction();
		session.delete(personToDelete);
		session.getTransaction().commit();
		session.close();

	}

	public long addUser(String userName, String userSurname, long idGroup) {

		long id;
		User user = new User();
		Groups groups = new Groups();

		groups.setId(idGroup);

		user.setUserName(userName);
		user.setUserSurname(userSurname);
		user.setGroups(groups);

		Session session = this.entityManager.unwrap(Session.class);
		session.beginTransaction();

		session.save(user);
		id = user.getId();
		session.getTransaction().commit();
		session.close();
		return id;
	}

	@Transactional
	public void editUser(long idUser, String userName, String userSurname, long idGroup) {

		Session session = this.entityManager.unwrap(Session.class);
		System.out.println("userName:" + userName + "idUser: " + idUser + "idGroup: " + idGroup + " userSurname:" + userSurname);

		session.createNativeQuery("update user set user_name = :userName , user_surname= :userSurname , id_groups= :idGroup where id_user = :idUser").setParameter("userName", userName).setParameter("userSurname", userSurname)
				.setParameter("idGroup", idGroup).setParameter("idUser", idUser).executeUpdate();
	}

}
