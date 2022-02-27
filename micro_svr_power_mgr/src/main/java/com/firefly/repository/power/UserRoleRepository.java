package com.firefly.repository.power;

import com.firefly.entity.power.User;
import com.firefly.entity.power.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author fly
 * @Date 2019/6/13
 * @Description
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {
    /**
     * 查找用户的角色权限
     * @return
     */
    List<UserRole> findAllByUserEqualsAndDelIsFalse(User user);
}
