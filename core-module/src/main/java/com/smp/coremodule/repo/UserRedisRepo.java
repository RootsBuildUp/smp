package com.smp.coremodule.repo;

import com.smp.coremodule.constant.AppConstant;
import com.smp.coremodule.model.UserRedis;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRedisRepo {

    private final RedisTemplate redisTemplate;

    public void save( UserRedis userRedis) {
        System.err.println( userRedis );
        redisTemplate.opsForHash().put( AppConstant.USER_REDIS_NAME, userRedis.getId(), userRedis );
    }

    public List<UserRedis> findAll() {
        return redisTemplate.opsForHash().values( AppConstant.USER_REDIS_NAME );
    }

    public UserRedis findById(Long id ) {
        return (UserRedis) redisTemplate.opsForHash().get(AppConstant.USER_REDIS_NAME, id );
    }

    public void update( UserRedis userRedis) {
        save(userRedis);
    }

    public void delete( Long id ) {
        redisTemplate.opsForHash().delete(AppConstant.USER_REDIS_NAME, id );
    }
}
