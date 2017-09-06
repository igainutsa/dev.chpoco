package dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpecialtyManager {

	@Autowired
	EntityManager entityManager;

	public List<Object[]> getSpecialtyAll() {
		Session session = this.entityManager.unwrap(Session.class);

		List<Object[]> persons = session.createNativeQuery("select * from chpoco.specialty").getResultList();

		return persons;
	}

}
