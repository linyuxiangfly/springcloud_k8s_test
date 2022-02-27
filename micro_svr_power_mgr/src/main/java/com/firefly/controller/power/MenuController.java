package com.firefly.controller.power;

import com.firefly.controller.BaseController;
import com.firefly.controller.CrudController;
import com.firefly.entity.power.Menu;
import com.firefly.entity.power.User;
import com.firefly.repository.power.MenuRepository;
import com.firefly.utils.MenuItemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author fly
 * @Date 2019/6/3
 * @Description
 */
@RestController
@RefreshScope
@RequestMapping("/menu")
public class MenuController extends CrudController<Menu> {
    MenuRepository repository;

    @Autowired
    public void setRepository(MenuRepository repository) {
        this.repository = repository;
        super.setRepository(repository);
    }

    /**
     * 获取菜单树
     * @return
     * @throws Exception
     */
    @RequestMapping("/getMenuTree")
    public List<MenuItemUtil.MenuItem> getMenuTree() throws Exception {
        List<Menu> menuList=repository.findAllByDelIsFalse();
        return MenuItemUtil.createMenuTree(menuList);
    }

}