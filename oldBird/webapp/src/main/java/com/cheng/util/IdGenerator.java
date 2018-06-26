package com.cheng.util;


import org.redisson.api.RedissonClient;
import org.redisson.api.annotation.RId;
import org.redisson.liveobject.resolver.RIdResolver;

/**
 * @author cheng_mboy
 */
public class IdGenerator  implements RIdResolver<RId,Long>{

    @Override
    public Long resolve(Class<?> cls, RId annotation, String idFieldName, RedissonClient redisson) {
        return 1L;
    }
}
