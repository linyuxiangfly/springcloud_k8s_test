package com.firefly.repository.power;

import com.firefly.entity.power.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author fly
 * @Date 2019/6/13
 * @Description
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
