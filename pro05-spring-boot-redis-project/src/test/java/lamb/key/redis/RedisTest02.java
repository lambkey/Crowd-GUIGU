package lamb.key.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author JoinYang
 * @date 2022/5/17 19:56
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTest02 {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate ;
    @Test
    public void TestRedis01() throws JsonProcessingException {
        Student student =new Student("张三","男",18);
        String student1 = objectMapper.writeValueAsString(student);
        redisTemplate.opsForValue().set("user:"+student.getStuName(),student1);
    }

    @Test
    public void TestRedis02() throws JsonProcessingException {
        String s = redisTemplate.opsForValue().get("user:张三");
        Student student = objectMapper.readValue(s,Student.class);
        System.out.println(student);
    }


}
