package com.taniltekdemir.springboot.dto;

import java.util.Date;

public class ProductCommentDto {

    private String comment;
    private Date comment_date;
    private String urunname;
    private String username;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getComment_date() {
        return comment_date;
    }

    public void setComment_date(Date comment_date) {
        this.comment_date = comment_date;
    }

    public String getUrunname() {
        return urunname;
    }

    public void setUrunname(String urunname) {
        this.urunname = urunname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
