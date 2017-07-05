package com.woshua.structure.praxis.domain;

import com.woshua.structure.user.domain.User;

import javax.persistence.*;

/**
 * Created by Acer on 2017/3/30.
 */
@Entity(name = "user_favorite")
public class FavoriteRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "praxis_id")
    private Praxis praxis;

    public FavoriteRelation(){

    }

    public FavoriteRelation(User user, Praxis praxis) {
        this.user = user;
        this.praxis = praxis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Praxis getPraxis() {
        return praxis;
    }

    public void setPraxis(Praxis praxis) {
        this.praxis = praxis;
    }
}
