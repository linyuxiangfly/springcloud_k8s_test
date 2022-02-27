package com.firefly.repository.power;

import com.firefly.entity.power.RoleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author fly
 * @Date 2019/6/13
 * @Description
 */
@Repository
public interface RoleDataRepository extends JpaRepository<RoleData, String> {
}
