package com.woshua.structure.catalogue.domain;

import com.woshua.core.web.BaseEntity;
import com.woshua.structure.maptree.domain.MapTree;
import com.woshua.structure.praxis.domain.Praxis;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Acer on 2017/7/3.
 */
@Entity(name = "catalogue")
public class Catalogue extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "grade")
    private MapTree grade;

    @ManyToOne
    @JoinColumn(name = "major")
    private MapTree major;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    @OrderBy("id")
    private List<Catalogue> children;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private Catalogue parent;

    private int type;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "catalogue_praxis",
            joinColumns = @JoinColumn(name = "catalogue_id", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "praxis_id", referencedColumnName = "ID"))
    private List<Praxis> praxisList;

    public Catalogue(){

    }

    public Catalogue(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Catalogue getParent() {
        return parent;
    }

    public void setParent(Catalogue parent) {
        this.parent = parent;
    }

    public List<Catalogue> getChildren() {
        return children;
    }

    public void setChildren(List<Catalogue> children) {
        this.children = children;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Praxis> getPraxisList() {
        return praxisList;
    }

    public void setPraxisList(List<Praxis> praxisList) {
        this.praxisList = praxisList;
    }

    /**
     * 判断是否是叶子节点
     *
     * @return
     */
    @Transient
    public boolean isLeaf() {
        return children == null || children.isEmpty();
    }
}
