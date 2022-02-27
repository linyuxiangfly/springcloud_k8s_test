package com.firefly.repository.power;

import com.firefly.entity.power.Role;
import com.firefly.entity.power.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author fly
 * @Date 2019/6/13
 * @Description
 */
@Repository
public interface RoleMenuRepository extends JpaRepository<RoleMenu, String> {
    List<RoleMenu> findAllByRoleIsInAndDelIsFalse(List<Role> roleList);
}
