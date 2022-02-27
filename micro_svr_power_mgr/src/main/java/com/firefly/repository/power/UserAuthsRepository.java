package com.firefly.repository.power;

import com.firefly.entity.power.UserAuths;
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
public interface UserAuthsRepository extends JpaRepository<UserAuths, String> {
//    @Cacheable(keyGenerator = "firstParamKeyGenerator")
    List<UserAuths> findByIdentityTypeAndIdentityFierAndDelIsFalse(
            String identityType,
            String identityFier
    );
}
