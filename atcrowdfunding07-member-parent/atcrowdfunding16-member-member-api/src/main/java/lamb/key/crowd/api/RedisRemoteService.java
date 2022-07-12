package lamb.key.crowd.api;

import net.seehope.crowd.util.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author JoinYang
 * @date 2022/5/30 18:13
 */
@FeignClient("lambkey-redis")
public interface RedisRemoteService {

    @RequestMapping("/get/value/redis/remote")
    JSONResult<String> getValueRedisRemote(@RequestParam("key") String key);

    @RequestMapping("/set/timeout/value/redis/remote")
    JSONResult<String> getTimeoutValueRedisRemote(@RequestParam("key") String key,
                                                  @RequestParam("value") String value,
                                                  @RequestParam("time") Integer time
    );
    @RequestMapping("/set/value/redis/remote")
    JSONResult<String> setValueRedisRemote(@RequestParam("key") String key, @RequestParam("value") String value);

    @RequestMapping("/delete/key/redis/remote")
    JSONResult<String> deleteRedisKey(@RequestParam("key") String key);
}
