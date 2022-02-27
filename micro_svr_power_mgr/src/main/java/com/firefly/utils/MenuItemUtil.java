package com.firefly.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.firefly.entity.power.Menu;
import org.hibernate.Criteria;

import java.io.Serializable;
import java.util.*;

/**
 * @Author fly
 * @Date 2019/6/17
 * @Description
 */
public class MenuItemUtil {
    /**
     * 菜单项
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class MenuItem implements Serializable,Comparable<MenuItem>{
        private Menu menu;
        private List<MenuItem> childs;

        public Menu getMenu() {
            return menu;
        }

        public void setMenu(Menu menu) {
            this.menu = menu;
        }

        public List<MenuItem> getChilds() {
            return childs;
        }

        public void setChilds(List<MenuItem> childs) {
            this.childs = childs;
        }

        @Override
        public int compareTo(MenuItem mi) {
            //自定义比较方法，如果认为此实体本身大则返回1，否则返回-1
            if(this.getMenu().getOrd() >= mi.getMenu().getOrd()){
                return 1;
            }
            return -1;
        }
    }

    /**
     * 创建菜单树
     * @param menus
     * @return
     * @throws Exception
     */
    public static List<MenuItem> createMenuTree(List<Menu> menus) throws Exception {
        List<MenuItem> retVal=null;
        List<MenuItem> emptyRert=null;
        List<MenuItem> countRet=null;

        if(menus!=null && menus.size()>0){
            Map<String,List<MenuItem>> menusPidMap=new HashMap<>();
            //根据菜单上级ID存储菜单列表
            for(Menu m:menus){
                //根据上级ID获取列表
                List<MenuItem> pmenuList=menusPidMap.get(m.getPid());
                if(pmenuList==null){
                    pmenuList=new ArrayList<>();
                    menusPidMap.put(m.getPid(),pmenuList);
                }
                MenuItem mi=new MenuItem();
                mi.setMenu(m);
                pmenuList.add(mi);
            }

            sortMenuItemList(menusPidMap);//排序菜单列表

            countRet = new ArrayList<>();
            retVal=menusPidMap.get(null);
            emptyRert=menusPidMap.get("");
            if(retVal != null){
                countRet.addAll(retVal);
            }else if(emptyRert != null){
                countRet.addAll(emptyRert);
            }
            fillMemuItem(countRet,menusPidMap);//递归填充所有子菜单

            menusPidMap.clear();//清除上级ID菜单列表MAP
        }

        return countRet;
    }

    private static void sortMenuItemList(Map<String,List<MenuItem>> menusPidMap){
        for(List<MenuItem> list:menusPidMap.values()){
            Collections.sort(list);//根据菜单ord排序
        }
    }

    /**
     * 填充菜单列表所有子菜单项
     * @param menuItemList
     * @param menusPidMap
     */
    private static void fillMemuItem(List<MenuItem> menuItemList, Map<String,List<MenuItem>> menusPidMap){
        if(menuItemList!=null && menusPidMap!=null){
            for(MenuItem mi:menuItemList){
                //从PID MAP里查找当前菜单的ID的数据放到菜单子菜单里
                mi.setChilds(menusPidMap.get(mi.getMenu().getId()));

                //递归填充孩子菜单的所有子菜单
                fillMemuItem(mi.getChilds(),menusPidMap);
            }
        }
    }
}
