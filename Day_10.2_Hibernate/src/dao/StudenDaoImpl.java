package dao;

import static utils.HibernateUtils.getSf;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.Course;
import pojos.Student;

public class StudenDaoImpl implements IStudentDao {

	@Override
	public String admitStudent(Student student, String courseTitle) {

		String mesg = "Student admission failed...";

		String jpql = "select c from Course c where c.title = :title";

		Session session = getSf().getCurrentSession();

		Transaction tx = session.beginTransaction();

		try {

			// 1.1 get course details by course title

			Course c = session.createQuery(jpql, Course.class).setParameter("title", courseTitle).getSingleResult();

			// 1.2 c: persistent
			// 1.3 if we update persistent entity : hibernate automatically propagates
			// changes
			// 1.4 to DB
			// 1.5 add student reference to course
			// 1. prob it is not inserting record in DB earlier we made changes to
			// persistent hibernate automatically does changes in DB so reason behind this
			// we have not supplied cascading yet : Parent->Child
			// solution we are using without cascade
			// 1.8 use - case -> uni-directional imagine from course we cannot find students
			// :
			// suppose not list of students property in course pojo
			// here we are commenting below line

			// for bi-dir : association

			// 1.6, 1.9 commented this line because we cannot find student from course
			// c.getStudents().add(student);

			// 1.13 establish the link from course -> student
			// c.getStudents().add(student);

			// 1.2, 1.12 Establish link from student to course
			// student.setSelectedCourse(c);

			c.addStudent(student);

			// 1.11 System.out.println(student.getSelectedCourse()); // null if won't
			// establish
			// above link from student to
			// course

			// 1.10 session.persist(student); // course_id won't be assigned

			// if we have not supplied cascading then we have to persist the student

			// 1.7
			tx.commit();

			mesg = "Student admission done";

		} catch (RuntimeException e) {

			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}

		return mesg;
	}

	@Override
	public String cancelAdmission(String courseName, int studentId) {

		String mesg = "Cancelling Admission Failed...";

		String jpql = "select c from Course c where c.title = :title";

		Session session = getSf().getCurrentSession();

		Transaction tx = session.beginTransaction();

		try {

			Course course = session.createQuery(jpql, Course.class).setParameter("title", courseName).getSingleResult();

			Student student = session.get(Student.class, studentId);

			if (student != null) {

				course.removeStudent(student);

				mesg = "Cancelled admission for student " + student.getName();
			}

			// this method is only doing null value of student table : course_id and not
			// deleting record so we have to do orphan removal in pojo
			// and added not null constraint : in selected course student pojo
			tx.commit();

		} catch (RuntimeException e) {

			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}

		return mesg;
	}

}
