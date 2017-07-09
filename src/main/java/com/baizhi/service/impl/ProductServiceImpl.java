package com.baizhi.service.impl;

import com.baizhi.dao.ProductDAO;
import com.baizhi.entity.Product;
import com.baizhi.service.ProductService;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wltea.analyzer.lucene.IKAnalyzer;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/6/22.
 */
@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    public void addProduct(Product product) {
        product.setId(UUID.randomUUID().toString());
        product.setUploadTime(new Date());
        product.setInd(1);
        productDAO.insert(product);
    }

    public List<Product> queryAll(Integer page, Integer size) {
        return productDAO.selectAll(size * (page - 1), size);
    }

    public Integer queryCount() {
        return productDAO.selectCount();
    }

    public void creatIndex(String id,String path) {
        File file = new File(path, "/index");
        if(!file.exists()){
            file.mkdirs();
        }

        Product product = productDAO.selectOne(id);

        IndexWriter indexWriter = null;
        try {

            //1.指定索引目录
            Directory directory = FSDirectory.open(file);
            //2.创建IndexWriteConfig
            Analyzer analyzer = new IKAnalyzer(true);
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_35, analyzer);
            //3.创建IndexWriter
            indexWriter = new IndexWriter(directory, config);
            //4.创建Document
            Document document = new Document();
            //5.构建Filed
            document.add(new Field("id", product.getId(), Field.Store.YES, Field.Index.NOT_ANALYZED));
            document.add(new Field("name", product.getName(), Field.Store.YES, Field.Index.ANALYZED));
            document.add(new Field("descript", product.getDescript(), Field.Store.YES, Field.Index.ANALYZED));
            document.add(new Field("path",product.getPath(), Field.Store.YES,Field.Index.NO));
            //价格
            NumericField priceField = new NumericField("price", Field.Store.YES, true);
            priceField.setDoubleValue(product.getPrice());
            document.add(priceField);


            //默认检索域   Store.NO  Index.ANALYZERED  值 所有检索域
            Field defualtField = new Field("default", product.getId() + " " + product.getName() + " " + product.getDescript() + " "+product.getPrice(), Field.Store.NO, Field.Index.ANALYZED);
            document.add(defualtField);
             /*if(i==2){
                document.setBoost(2);
            }*/
            //6.添加document到索引库
            indexWriter.addDocument(document);
            //7.提交索引
            indexWriter.commit();//提交索引 flush 索引变更到磁盘

            product.setInd(0);
            productDAO.updateIndex(product);

        } catch (CorruptIndexException e1) {
            e1.printStackTrace();
        } catch (LockObtainFailedException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            //8.关闭资源
            try {
                indexWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        product.setInd(0);
    }

    public void deleteIndex(String id,String path) {


        File file = new File(path, "/index");
        if(!file.exists()){
            file.mkdirs();
        }
        Product product = productDAO.selectOne(id);

        try {
            //1.指定索引目录
            Directory directory=FSDirectory.open(file);
            //2.创建IndexWriteConfig
            Analyzer analyzer = new IKAnalyzer();
            IndexWriterConfig config=new IndexWriterConfig(Version.LUCENE_35, analyzer);
            //3.创建IndexWriter
            IndexWriter indexWriter=new IndexWriter(directory, config);

            indexWriter.deleteDocuments(new Term("id",id));

            //提交关闭资源
            indexWriter.commit();
            indexWriter.close();

            product.setInd(1);
            productDAO.updateIndex(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}