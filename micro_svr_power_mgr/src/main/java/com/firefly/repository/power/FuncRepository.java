package com.firefly.repository.power;

import com.firefly.entity.power.Func;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author fly
 * @Date 2019/6/13
 * @Description
 */
@Repository
public interface FuncRepository extends JpaRepository<Func, String> {
    public List<Func> findByNameAndDel(String name,boolean del);
}
