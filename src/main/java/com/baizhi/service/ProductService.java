package com.baizhi.service;

import com.baizhi.entity.Product;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/6/22.
 */
@Service
public interface ProductService {

    void addProduct(Product product);

    List<Product> queryAll(Integer page, Integer size);

    Integer queryCount();

    void creatIndex(String id,String path);

    void deleteIndex(String id,String path);
}
