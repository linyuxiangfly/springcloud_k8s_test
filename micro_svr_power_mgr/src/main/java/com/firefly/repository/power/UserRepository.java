package com.firefly.repository.power;

import com.firefly.entity.power.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author fly
 * @Date 2019/6/13
 * @Description
 */
@Repository
//@CacheConfig(cacheNames = "user")
public interface UserRepository extends JpaRepository<User, String> {
//    @Cacheable(keyGenerator = "firstParamKeyGenerator")
    List<User> findByIdAndDelIsFalse(String id);
}
