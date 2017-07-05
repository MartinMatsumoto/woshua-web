package com.woshua.structure.catalogue.domain;

import com.woshua.core.web.BaseEntity;
import com.woshua.structure.maptree.domain.MapTree;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Acer on 2017/7/3.
 */
@Entity(name = "catalogue")
public class Catalogue extends BaseEntity {

    public static Long MATH_ROOT = 8L;

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
