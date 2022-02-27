package com.firefly.repository.power;

import com.firefly.entity.power.Menu;
import com.firefly.entity.power.MenuFunc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author fly
 * @Date 2019/6/13
 * @Description
 */
@Repository
public interface MenuFuncRepository extends JpaRepository<MenuFunc, String> {
    List<MenuFunc> findAllByMenuIsInAndDelIsFalse(List<Menu> menuList);
}
