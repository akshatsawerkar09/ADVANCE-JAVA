package dao;

import pojos.Course;
import org.hibernate.*;
import static utils.HibernateUtils.getSf;

public class CourseDaoImpl implements ICourseDao {

	@Override
	public String launchNewCourse(Course c) {

		String mesg = "Launching new course failed!!!";

		Session session = getSf().getCurrentSession();

		Transaction tx = session.beginTransaction();

		try {

			session.persist(c);

			tx.commit();

			mesg = "Launched new course with course id : " + c.getId();

		} catch (RuntimeException e) {

			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}

		return mesg;
	}

	@Override
	public String cancelCourse(int courseId) {

		String mesg = "Cancel Course Failed...";

		Session session = getSf().getCurrentSession();

		Transaction tx = session.beginTransaction();

		try {
			// get course details from id
			Course course = session.get(Course.class, courseId);

			if (course != null) {
				session.delete(course); // c: marked for removal : deletes all child records & then parent record
			}

			tx.commit();

			mesg = "Cancelled course : " + course.getTitle();

		} catch (RuntimeException e) {

			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}

		return mesg;
	}

	@Override
	public Course getCourseDetails(String courseName) {

		Course c = null;

		// String jpql = "select c from Course c where c.title = :title";

		// sol 3 join fetch : here only course query fired fast
		String jpql = "select c from Course c join fetch c.students where c.title = :title";

		Session session = getSf().getCurrentSession();

		Transaction tx = session.beginTransaction();

		try {

			c = session.createQuery(jpql, Course.class).setParameter("title", courseName).getSingleResult();

			// till now details fetched only course details

			// c : persistent

			// Sol - 2 access size of the collection

			// c.getStudents().size(); // here select will be fired on students table
			// here 2 query fired on student and course
			tx.commit();

		} catch (RuntimeException e) {

			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}

		return c; // c: detached
	}

}
