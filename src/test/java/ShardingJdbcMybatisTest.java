import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sjm.entry.Student;
import org.sjm.entry.User;
import org.sjm.service.StudentService;
import org.sjm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Component:
 * Description:
 * Date: 16/9/30
 *
 * @author yue.zhang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-config.xml"})
public class ShardingJdbcMybatisTest {

    @Autowired
    public UserService userService;

    @Autowired
    public StudentService studentService;

    @Test
    public void testUserInsert() {
        User u = new User();
        u.setUserId(1);
        u.setAge(25);
        u.setName("ben1");
        Assert.assertEquals(userService.insert(u), true);
    }

    @Test
    public void testFindAll(){
        List<User> users = userService.findAll();
        if(null != users && !users.isEmpty()){
            for(User u :users){
                System.out.println(u);
            }
        }
    }

    @Test
    public void testSQLIN(){
        List<User> users = userService.findByUserIds(Arrays.asList(2, 10, 1));
        if(null != users && !users.isEmpty()){
            for(User u :users){
                System.out.println(u);
            }
        }
    }


    @Test
    public void testStudentInsert() {
        Student student = new Student();
        student.setStudentId(2);
        student.setAge(21);
        student.setName("hehe1");
        Assert.assertEquals(studentService.insert(student), true);
    }

    @Test
    public void testTransactionTestSucess(){
        userService.transactionTestSucess();
    }


    @Test(expected = IllegalAccessException.class)
    public void testTransactionTestFailure() throws IllegalAccessException{
        userService.transactionTestFailure();
    }

}
