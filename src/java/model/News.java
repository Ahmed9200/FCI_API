/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author 20102
 */
public class News {

    private int news_id;
    private String news_tittle;
    private String news_description;
    private String news_date;
    private int news_addedBy;

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public String getNews_tittle() {
        return news_tittle;
    }

    public void setNews_tittle(String news_tittle) {
        this.news_tittle = news_tittle;
    }

    public String getNews_description() {
        return news_description;
    }

    public void setNews_description(String news_description) {
        this.news_description = news_description;
    }

    public String getNews_date() {
        return news_date;
    }

    public void setNews_date(String news_date) {
        this.news_date = news_date;
    }

    public int getNews_addedBy() {
        return news_addedBy;
    }

    public void setNews_addedBy(int news_addedBy) {
        this.news_addedBy = news_addedBy;
    }

}
