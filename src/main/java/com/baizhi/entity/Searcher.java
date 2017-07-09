package com.baizhi.entity;

/**
 * Created by Administrator on 2017/6/25.
 */
public class Searcher {
    private String id;
    private String name;
    private String price;
    private String descript;
    private String path;
    private float score;

    public Searcher() {
    }

    public Searcher(String id, String name, String price, String descript, String path, float score) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.descript = descript;
        this.path = path;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Searcher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", descript='" + descript + '\'' +
                ", path='" + path + '\'' +
                ", score=" + score +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
