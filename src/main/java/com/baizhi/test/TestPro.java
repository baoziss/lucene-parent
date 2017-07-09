package com.baizhi.test;

import com.baizhi.entity.Product;
import com.baizhi.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2017/6/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-basit.xml")
public class TestPro {

    @Autowired
    private ProductService productService;

    @Test
    public void testShowAll(){
        List<Product> products = productService.queryAll(1, 2);
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
