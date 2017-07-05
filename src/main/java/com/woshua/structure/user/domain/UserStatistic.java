package com.woshua.structure.user.domain;

import com.woshua.core.web.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Acer on 2017/3/30.
 */
@Entity(name = "user_statistic")
public class UserStatistic extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "favorite_num")
    private int favoriteNum;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getFavoriteNum() {
        return favoriteNum;
    }

    public void setFavoriteNum(int favoriteNum) {
        this.favoriteNum = favoriteNum;
    }

    @Override
    public Serializable getId() {
        return user.getId();
    }

    public void setId(Long id) {
        this.id = id;
    }
}
