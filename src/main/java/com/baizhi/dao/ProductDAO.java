package com.baizhi.dao;

import com.baizhi.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 */
public interface ProductDAO {
    void insert(Product product);

    void updateIndex(Product product);

    List<Product> selectAll(@Param("tol") Integer tol, @Param("size") Integer size);

    Product selectOne(String id);

    Integer selectCount();

    void update(Product product);

}
