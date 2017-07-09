package com.baizhi.controller;

import com.baizhi.entity.Product;
import com.baizhi.service.ProductService;
import com.baizhi.util.CreateIndexUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2017/6/22.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @RequestMapping("/save")
    @ResponseBody
    public void save(Product product, MultipartFile aa, HttpServletRequest request) throws IOException {
        String realPath = request.getSession().getServletContext().getRealPath("/");
        File file = new File(realPath,"/images");
//        String parent = new File(realPath).getParent();
//        File file = new File(parent + "/images");
        if(!file.exists()){
            file.mkdirs();
        }
        //重命名文件
        String extension = FilenameUtils.getExtension(aa.getOriginalFilename());
        String newName= UUID.randomUUID().toString()+"."+extension;
        String path="/images/"+newName;

        //文件上传
        aa.transferTo(new File(file,newName));

        //添加信息到RDBMS
        product.setContentType(extension);
        product.setFileName(aa.getOriginalFilename());
        product.setPath(path);
        productService.addProduct(product);
    }

    @RequestMapping("/showAll")
    @ResponseBody
    public Map<String,Object> showAll(Integer page, Integer rows){
        Map<String,Object> map = new HashMap<String,Object>();

        Integer count = productService.queryCount();

        List<Product> products = productService.queryAll(page,rows);

        map.put("total", count);
        map.put("rows", products);

        return map;
    }




    @RequestMapping("/cindex")
    @ResponseBody
    public void createIndex(String id,HttpServletRequest request){
        String realPath = request.getSession().getServletContext().getRealPath("/");
        String parent = new File(realPath).getParent();

        productService.creatIndex(id,parent);
    }

    @RequestMapping("/dindex")
    @ResponseBody
    public void deleteIndex(String id,HttpServletRequest request){
        String realPath = request.getSession().getServletContext().getRealPath("/");
        String parent = new File(realPath).getParent();

        productService.deleteIndex(id,parent);
    }
}
