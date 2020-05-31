import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.Date;
import java.util.List;

/**
 * Created by DreamBoy on 2016/5/15.
 */
//测试类
public class HibernateTest {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    @Before
    public void init() {
        //创建配置对象
        Configuration config = new Configuration().configure();
        //创建服务注册对象
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure().build();
        //创建会话工厂对象
        sessionFactory = new MetadataSources( serviceRegistry ).buildMetadata().buildSessionFactory();
        //会话对象
        session = sessionFactory.openSession();
        //开启事务
        transaction = session.beginTransaction();
    }

    @After
    public void destory() {
        transaction.commit(); //提交事务
        session.close(); //关闭会话
        sessionFactory.close(); //关闭会话工厂
    }

    @Test
    public void testSaveStudents() {
        //生成学生对象
        Students s = new Students(201401001, "令狐冲", "男", new Date(), "1001");
        session.save(s); //保存对象进入数据库
    }

    @Test
    public void testQueryStudents() {
//        Query<Students> findAllMan = session.createQuery("from Students s where s.gender='男'");
//        List<Students> students = findAllMan.list();
//        System.out.println(students.toString());
        Object obj = session.get(Students.class, 201401001);
        System.out.println((Students)obj);
    }

    @Test
    public void testDeleteStudents() {
        Students s = new Students(1111111, "bilibli", "男", new Date(), "1004");
        session.save(s); //保存对象进入数据库

        Query<Students> findAllMan = session.createQuery("from Students s where s.gender='男'");
        List<Students> students = findAllMan.list();
        System.out.println(students.toString());

        Query<Students> findSome = session.createQuery("from Students s where s.sname='bilibli'");
        List<Students> list = findSome.getResultList();
        for(Students sd : list){
            session.delete((sd));
        }

        Query<Students> findAllMan2 = session.createQuery("from Students s where s.gender='男'");
        List<Students> students2 = findAllMan2.list();
        System.out.println(students2.toString());

    }

    @Test
    public void testUpdateStudents() {
        Students students = session.get(Students.class,201401001);
        students.setSname("令狐冲更新了!!");
        session.update(students);

        Query<Students> findAllMan = session.createQuery("from Students s where s.gender='男'");
        List<Students> studentslist = findAllMan.list();
        System.out.println(studentslist.toString());
    }


}