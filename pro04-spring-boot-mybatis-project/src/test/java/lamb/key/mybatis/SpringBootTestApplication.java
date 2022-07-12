package lamb.key.mybatis;

import lamb.key.mybatis.entity.Teacher;
import lamb.key.mybatis.mapper.TeacherMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/5/17 12:31
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class SpringBootTestApplication {
    @Autowired
    private TeacherMapper teacherMapper;
    @Test
    public void test(){
      List<Teacher> teacherList = teacherMapper.selectList(null);
      teacherList.forEach(System.out::println);
    }
}
