package com.woshua.structure.user.transferobj;

import com.woshua.structure.user.domain.User;
import com.woshua.structure.user.domain.UserStatistic;

/**
 * Created by Acer on 2017/3/24.
 */
public class UserTo {

    private Long id;

    private String email;

    private String account;

    private String nickname;

    private String password;

    private int usertype;

    private String usertypeDesc;

    private int sex;

    private String sexDesc;

    private int favoriteNum;

    private String iconPath;

    public UserTo(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.account = user.getAccount();
        this.nickname = user.getNickname();
        this.usertype = user.getUsertype().getValue();
        this.usertypeDesc = user.getUsertype().getDesc();
        this.sex = user.getSex().getValue();
        this.sexDesc = user.getSex().getDesc();
        this.iconPath = user.getIconPath();
        this.password = user.getPassword();

        UserStatistic userStatistic = user.getUserStatistic();
        if (userStatistic != null) {
            favoriteNum = userStatistic.getFavoriteNum();
        }
    }

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public int getFavoriteNum() {
        return favoriteNum;
    }

    public void setFavoriteNum(int favoriteNum) {
        this.favoriteNum = favoriteNum;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getUsertypeDesc() {
        return usertypeDesc;
    }

    public void setUsertypeDesc(String usertypeDesc) {
        this.usertypeDesc = usertypeDesc;
    }

    public String getSexDesc() {
        return sexDesc;
    }

    public void setSexDesc(String sexDesc) {
        this.sexDesc = sexDesc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
