package com.firefly.utils;

import com.firefly.entity.power.*;
import com.firefly.repository.power.MenuFuncRepository;
import com.firefly.repository.power.RoleMenuRepository;
import com.firefly.repository.power.UserRoleRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author fly
 * @Date 2019/6/17
 * @Description 权限处理单元
 */
public class PowerUtil {
    /**
     * 获取用户角色权限
     * @param userRoleRepository
     * @param user
     * @return
     */
    public static List<Role> getRoleByUser(
            UserRoleRepository userRoleRepository,
            User user){
        List<UserRole> userRoleList=userRoleRepository.findAllByUserEqualsAndDelIsFalse(user);
        if(userRoleList!=null){
            List<Role> roleList=new ArrayList<>();
            for(UserRole userRole:userRoleList){
                roleList.add(userRole.getRole());
            }
            return roleList;
        }
        return null;
    }

    /**
     * 获取用户菜单权限
     * @param user
     * @return
     */
    public static List<Menu> getMenuByUser(
            UserRoleRepository userRoleRepository,
            RoleMenuRepository roleMenuRepository,
            User user){
        List<Role> roleList=getRoleByUser(userRoleRepository,user);
        if(roleList!=null){
            List<RoleMenu> roleMenuList=roleMenuRepository.findAllByRoleIsInAndDelIsFalse(roleList);
            if(roleMenuList!=null){
                List<Menu> menuList=new ArrayList<>();
                for(RoleMenu roleMenu:roleMenuList){
                    menuList.add(roleMenu.getMenu());
                }
                return menuList;
            }
        }

        return null;
    }

    /**
     * 获取用户接口权限
     * @param user
     * @return
     */
    public static List<Func> getFuncByUser(
            UserRoleRepository userRoleRepository,
            RoleMenuRepository roleMenuRepository,
            MenuFuncRepository menuFuncRepository,
            User user){
        List<Menu> menuList=getMenuByUser(userRoleRepository,roleMenuRepository,user);
        if(menuList!=null){
            List<MenuFunc> menuFuncList=menuFuncRepository.findAllByMenuIsInAndDelIsFalse(menuList);
            if(menuFuncList!=null){
                List<Func> funcList=new ArrayList<>();
                for(MenuFunc menuFunc:menuFuncList){
                    funcList.add(menuFunc.getFunc());
                }
                return funcList;
            }
        }
        return null;
    }

    /**
     * 获取用户菜单树
     * @param user
     * @return
     */
    public static  List<MenuItemUtil.MenuItem> getMenuTreeByUser(
            UserRoleRepository userRoleRepository,
            RoleMenuRepository roleMenuRepository,
            User user) throws Exception {
        List<Menu> menuList=getMenuByUser(userRoleRepository,roleMenuRepository,user);
        if(menuList!=null){
            return MenuItemUtil.createMenuTree(menuList);
        }
        return null;
    }
}
