package lamb.key.spring.boot.test;



import lamb.key.spring.boot.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author JoinYang
 * @date 2022/5/13 21:33
 */

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class WebSpringBootTest {

    @Autowired
    private Student student;

    @Value("${lamb.key.wish}")
    private String wishes;
    @Test
    public void testRead(){
        log.info(student.toString());
        log.info("你好");
        log.debug("????????????????????????????????????????????????????????");
        log.debug(wishes);
    }
}
