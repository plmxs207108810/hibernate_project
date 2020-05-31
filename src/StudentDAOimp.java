import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public  class StudentDAOimp implements StudentsDAO {

    static SessionFactory sf = null;

    static{
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        sf = config.buildSessionFactory();

    }


    @Override
    public void saveStudent(Students s) {
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();
        session.save(s);
        tr.commit();
        session.close();
    }

    @Override
    public void deleteStudent(Students s) {
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();
        session.delete(s);
        tr.commit();
        session.close();
    }

    @Override
    public void updateStudent(Students s) {
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();
        session.update(s);
        tr.commit();
        session.close();
    }

    @Override
    public Students selectStudentBySid(Integer id) {
        Session session = sf.openSession();
        Object obj = session.get(Students.class, id);
        return (Students) obj;
    }

    @Override
    public List<Students> findAll() {
        Session session = sf.openSession();
        String hql = "from Students ";
        return session.createQuery(hql).list();
    }
}
