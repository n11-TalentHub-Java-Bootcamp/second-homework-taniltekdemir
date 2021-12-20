package com.taniltekdemir.springboot.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "urun_yorum")
public class ProductComment implements Serializable {

    @SequenceGenerator(name = "generator", sequenceName = "URUN_YORUM_ID_SEQ")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "YORUM", length = 500)
    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    private Date comment_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_URUN", foreignKey = @ForeignKey(name = "FK_YORUM_URUN_ID"))
    private Urun urun;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_KULLANICI", foreignKey = @ForeignKey(name = "FK_YORUM_KULLANICI_ID"))
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getComment_date() {
        return comment_date;
    }

    public void setComment_date(Date comment_date) {
        this.comment_date = comment_date;
    }

    public Urun getUrun() {
        return urun;
    }

    public void setUrun(Urun urun) {
        this.urun = urun;
    }

    @Override
    public String toString() {
        return "ProductComment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", comment_date=" + comment_date +
                ", urun=" + urun +
                ", user=" + user +
                '}';
    }
}
