package sg.edu.nus.iss.vttp5a_practice_workshop.repository;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.vttp5a_practice_workshop.constant.Constant;

@Repository
public class MapRepo {
    
    @Autowired
    @Qualifier(Constant.TEMPLATE02)
    RedisTemplate<String, String> template;


    public void create(String redisKey, String hashKey, String hashValue) {
        template.opsForHash().put(redisKey, hashKey, hashValue);
    }

    public Object get(String redisKey, String hashKey) {
    // Object can be anything, a string, a number, a class, a collection
        return template.opsForHash().get(redisKey, hashKey);
    }

    public long delete(String redisKey, String hashKey) {
        return template.opsForHash().delete(redisKey, hashKey);
    }

    public Boolean hasHashKey(String redisKey, String hashKey){
        return template.opsForHash().hasKey(redisKey, hashKey);
    }

    // <Object, Object> = <HashKeys, HashValues>
    public Map<Object, Object> getEntries(String redisKey){
        return template.opsForHash().entries(redisKey);
    }

    public Set<Object> getKeys(String redisKey) {
        return template.opsForHash().keys(redisKey);
    }

    public List<Object> getValues(String redisKey) {
        return template.opsForHash().values(redisKey);
    }

    public Long size(String redisKey){
        return template.opsForHash().size(redisKey);
    }

    public Boolean expire(String redisKey, Long expireValue){
        Duration expireDuration = Duration.ofSeconds(expireValue);
        return template.expire(redisKey, expireDuration);
    }
    
}
