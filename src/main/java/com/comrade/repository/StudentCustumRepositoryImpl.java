package com.comrade.repository;

import java.util.List;
import com.comrade.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentCustumRepositoryImpl implements StudentCustumRepository{
@Autowired
EntityManager entityManager;
	/*@Autowired
	SessionFactory sessionFactory;*/

	@Override
	public void saveStudent(Student student) {
		
		
	}

	@Override
	public List<Student> getAllStudents() {
		
		List<Student> all=entityManager.createQuery("FROM Student", Student.class).getResultList();
		return all;
	}

	@Override
	public List<String> getAllName() {
		Query Query=entityManager.createQuery("select s.name from Student s");
		List<String> s=Query.getResultList();
		return s;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getAllStudentsByID(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query query=session.createQuery("select s from Student s where s.id=:id");
		
		/*Query query=entityManager.createQuery("select s from Student s where s.id=:id");*/
		query.setParameter("id", new Long(id));
		List<Student> s=query.getResultList();
		return s;
	}

	@Override
	public Long getMaxID() {
		Long id=entityManager.createQuery("select Max(s.id) from Student s",Long.class).getSingleResult();
		return id;
	}

	@Override
	public void Update(int id) {
		Student s=entityManager.find(Student.class, new Long(id));
		s.setName("Satish");
		s.setPassportNumber("XXXXXXXX");
		entityManager.merge(s);
		
	}

	@Override
	public void remove(int id) {
		
		Student s=entityManager.find(Student.class, new Long(id));
		if(s !=null) {
			entityManager.remove(s);
		}
		
	}

}
