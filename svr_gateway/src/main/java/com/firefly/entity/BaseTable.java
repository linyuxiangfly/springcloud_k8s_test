package com.firefly.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author fly
 * @Date 2019/6/13
 * @Description
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class BaseTable implements Serializable {
    private String id = "";
    private Date createDate = new Date();
    private Date updateDate = new Date();
    private boolean del;

    public BaseTable() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isDel() {
        return this.del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public String toString() {
        return this.id;
    }
}