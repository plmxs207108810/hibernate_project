import java.util.List;

public interface StudentsDAO {

    public void saveStudent(Students s);//添加学生
    public void deleteStudent(Students s);//删除学生
    public void updateStudent(Students s);//更新学生
    public Students selectStudentBySid(Integer id);//查询学生
    public List<Students> findAll();//查询所有
}
