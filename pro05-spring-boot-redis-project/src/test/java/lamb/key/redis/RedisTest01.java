package lamb.key.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author JoinYang
 * @date 2022/5/17 18:48
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTest01 {

    @Autowired
    private RedisTemplate<String,String> redisTemplate ;
    @Test
    public void TestRedis01(){
        redisTemplate.opsForValue().set("username","yzy");
    }

    @Test
    public void TestRedis02(){
        String username = redisTemplate.opsForValue().get("username");
        System.out.println(username);
    }


}
