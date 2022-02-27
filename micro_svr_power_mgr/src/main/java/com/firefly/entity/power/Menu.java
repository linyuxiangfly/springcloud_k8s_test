package com.firefly.entity.power;

import com.firefly.entity.BaseTable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @Author fly
 * @Date 2018/3/15
 * @Description
 */
@Entity
@Table(
        name = "pw_menu",
        indexes = {
                @Index(name = "index_pid",  columnList="pid", unique = false)
        }
        )
public class Menu extends BaseTable {
    //外键
    @Column(length = 35)
    private String pid;//所属菜单ID
    //基本字段
    @Column(length = 35)
    private String tag;//分类
    @Column(length = 40)
    private String name;//菜单名称
    @Column(columnDefinition="TEXT")
    private String href;//链接
    @Column(length = 20)
    private String target;//目标target
    private boolean vis;//是否显示菜单
    @Column(columnDefinition="TEXT")
    private String cls;//class,为了菜单样式做设置
    @Column(length = 50)
    private String powerCode;//权限代码，为了给前端判断是否显示菜单下的某个按钮或或者个功能
    private int ord;//排序用
    @Column(columnDefinition="TEXT")
    private String remark;//备注

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public boolean isVis() {
        return vis;
    }

    public void setVis(boolean vis) {
        this.vis = vis;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public String getPowerCode() {
        return powerCode;
    }

    public void setPowerCode(String powerCode) {
        this.powerCode = powerCode;
    }

    public int getOrd() {
        return ord;
    }

    public void setOrd(int ord) {
        this.ord = ord;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
