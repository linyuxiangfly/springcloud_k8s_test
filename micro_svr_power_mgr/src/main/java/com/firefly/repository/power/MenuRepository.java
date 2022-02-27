package com.firefly.repository.power;

import com.firefly.entity.power.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author fly
 * @Date 2019/6/13
 * @Description
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, String> {
    /**
     * 查询所有未删除的菜单
     * @return
     */
    List<Menu> findAllByDelIsFalse();
}
