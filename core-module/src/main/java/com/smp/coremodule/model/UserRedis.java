package com.smp.coremodule.model;

import com.smp.coremodule.constant.AppConstant;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash( AppConstant.USER_REDIS_NAME )
public class UserRedis {
    private Long id;
    private String name;
    private String address;
}
