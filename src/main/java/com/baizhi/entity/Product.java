package com.baizhi.entity;

import java.util.Date;

/**
 * 商品实体类
 */
public class Product {
    private String id;
    private String name;
    private Double price;
    private Date uploadTime;
    private String fileName;
    private String contentType;
    private String path;
    private String descript;
    private Integer ind;

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInd(Integer ind) {
        this.ind = ind;
    }

    public Integer getInd() {
        return ind;
    }

    public void setIndex(Integer ind) {
        this.ind = ind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String desc) {
        this.descript = desc;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", uploadTime=" + uploadTime +
                ", fileName='" + fileName + '\'' +
                ", contentType='" + contentType + '\'' +
                ", path='" + path + '\'' +
                ", descript='" + descript + '\'' +
                ", ind=" + ind +
                '}';
    }

    public Product(String id, String name, Double price, Date uploadTime, String fileName, String contentType, String path, String descript, Integer ind) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.uploadTime = uploadTime;
        this.fileName = fileName;
        this.contentType = contentType;
        this.path = path;
        this.descript = descript;
        this.ind = ind;
    }
}
