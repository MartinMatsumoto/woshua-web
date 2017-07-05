package com.woshua.structure.user.domain;

import com.woshua.core.enums.SexType;
import com.woshua.core.enums.UserType;
import com.woshua.core.web.BaseEntity;

import javax.persistence.*;

/**
 * Created by Acer on 2017/3/24.
 */

@Entity(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String account;

    private String password;

    private String nickname;

    @Column(name = "user_type")
    private UserType usertype;

    private SexType sex;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserStatistic userStatistic;

    @Column(name = "icon_path")
    private String iconPath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public SexType getSex() {
        return sex;
    }

    public void setSex(SexType sex) {
        this.sex = sex;
    }

    public UserType getUsertype() {
        return usertype;
    }

    public void setUsertype(UserType usertype) {
        this.usertype = usertype;
    }

    public UserStatistic getUserStatistic() {
        return userStatistic;
    }

    public void setUserStatistic(UserStatistic userStatistic) {
        this.userStatistic = userStatistic;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public void addFavoriteCount(){
        if(userStatistic == null){
            userStatistic = new UserStatistic();
            userStatistic.setUser(this);
        }
        userStatistic.setFavoriteNum(userStatistic.getFavoriteNum() + 1);
    }

    public void decreaseFavoriteCount(){
        if(userStatistic == null){
            userStatistic = new UserStatistic();
            userStatistic.setUser(this);
        }
        userStatistic.setFavoriteNum(userStatistic.getFavoriteNum() - 1);
    }
}
