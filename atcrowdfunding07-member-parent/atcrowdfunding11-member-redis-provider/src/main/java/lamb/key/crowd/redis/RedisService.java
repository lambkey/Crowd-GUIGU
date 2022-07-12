package lamb.key.crowd.redis;

import lombok.extern.slf4j.Slf4j;
import net.seehope.crowd.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author JoinYang
 * @date 2022/5/30 18:24
 */
@RestController
@Slf4j
public class RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    // 根据键获取变量值
    @RequestMapping("/get/value/redis/remote")
    public JSONResult<String> getValueRedisRemote(@RequestParam("key") String key){
        try {
            String s = redisTemplate.opsForValue().get(key);
            log.info(s);
            return JSONResult.successNeedMessage(s);
        }catch (Exception e){
            return JSONResult.FailureNeedMessage(e.getMessage());
        }
    }

    // 设定变量key的同时设置键值的存活时间
    @RequestMapping("/set/timeout/value/redis/remote")
    public JSONResult<String> getTimeoutValueRedisRemote(@RequestParam("key") String key,
                                                  @RequestParam("value") String value,
                                                  @RequestParam("time") Integer time
                                                  ){
        try {
            // 设置key存储的时长为分钟
            redisTemplate.opsForValue().set(key,value,time, TimeUnit.MINUTES);
            return JSONResult.successNeedMessage("存入成功");
        }catch (Exception e){
            return JSONResult.FailureNeedMessage(e.getMessage());
        }
    }

    // 普通存入键值
    @RequestMapping("/set/value/redis/remote")
    public JSONResult<String> setValueRedisRemote(@RequestParam("key") String key,
                                           @RequestParam("value") String value){

        try {
            redisTemplate.opsForValue().set(key,value);
            return JSONResult.successNeedMessage("存入成功");
        }catch (Exception e){
            return JSONResult.FailureNeedMessage(e.getMessage());
        }

    }
    // 根据key删除
    @RequestMapping("/delete/key/redis/remote")
    public JSONResult<String> deleteRedisKey(@RequestParam("key") String key){

        try {
            redisTemplate.delete(key);
            return JSONResult.successNeedMessage("删除成功");
        }catch (Exception e){
            return JSONResult.FailureNeedMessage(e.getMessage());
        }

    }
}
