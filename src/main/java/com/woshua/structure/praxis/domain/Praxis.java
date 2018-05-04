package com.woshua.structure.praxis.domain;

import com.woshua.structure.catalogue.domain.Catalogue;
import com.woshua.structure.maptree.domain.MapTree;
import org.hibernate.engine.profile.Fetch;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Acer on 2017/3/23.
 */
@Entity(name = "praxis")
public class Praxis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "click_rate")
    private int clickRate;

    @ManyToOne
    @JoinColumn(name = "type")
    private MapTree type;

    @ManyToOne
    @JoinColumn(name = "grade")
    private MapTree grade;

    @ManyToOne
    @JoinColumn(name = "major")
    private MapTree major;

    private String content;

    private String prompt;

    private String resolve;

    private String answer;

    @Column(name = "good_num")
    private int goodNum;

    @Column(name = "bad_num")
    private int badNum;

    private int dificulty;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "catalogue_praxis",
            joinColumns = @JoinColumn(name = "praxis_id", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "catalogue_id", referencedColumnName = "ID"))
    private List<Catalogue> catalogues;

    public Praxis() {
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getClickRate() {
        return clickRate;
    }

    public void setClickRate(int clickRate) {
        this.clickRate = clickRate;
    }

    public MapTree getType() {
        return type;
    }

    public void setType(MapTree type) {
        this.type = type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getResolve() {
        return resolve;
    }

    public void setResolve(String resolve) {
        this.resolve = resolve;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(int goodNum) {
        this.goodNum = goodNum;
    }

    public int getBadNum() {
        return badNum;
    }

    public void setBadNum(int badNum) {
        this.badNum = badNum;
    }

    public int getDificulty() {
        return dificulty;
    }

    public MapTree getGrade() {
        return grade;
    }

    public void setGrade(MapTree grade) {
        this.grade = grade;
    }

    public MapTree getMajor() {
        return major;
    }

    public void setMajor(MapTree major) {
        this.major = major;
    }

    public void setDificulty(int dificulty) {
        this.dificulty = dificulty;
    }

    public List<Catalogue> getCatalogues() {
        return catalogues;
    }

    public void setCatalogues(List<Catalogue> catalogues) {
        this.catalogues = catalogues;
    }
}
