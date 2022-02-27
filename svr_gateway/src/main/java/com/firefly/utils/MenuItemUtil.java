package com.firefly.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.firefly.entity.power.Menu;

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
}
