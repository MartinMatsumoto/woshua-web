/*
 * @(#)MapTree.java
 *
 * Copyright 2013 Vision, Inc. All rights reserved.
 */

package com.woshua.structure.maptree.domain;

import com.woshua.core.web.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

/**
 * 码表树对象
 *
 * @author fuwenguang
 * @version 1.0, 2013-4-15
 * @TypeDefs({
 * @TypeDef(name = "type", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vision.core.enums.MapTreeType") }),
 * @TypeDef(name = "stateType", typeClass = EnumType.class, parameters = { @Parameter(name = "class", value = "com.vision.core.enums.StateType") })
 * })
 */
@Entity(name = "maptree")
public class MapTree extends BaseEntity {
    /**
     * 中国区域id
     */
    public static final Long CHINA_ID = 499L;
    /**
     * 学段id
     */
    public static final Long GRADE_ID = 3L;
    /**
     * 学科id
     */
    public static final Long MAJOR_ID = 2L;
    /**
     * 题型id
     */
    public static final Long PRAXIS_TYPE = 16L;

    /**
     * 初中
     */
    public static final Long CHUZHONG = 14L;

    /**
     * 数学
     */
    public static final Long MATH = 5L;

    /**
     * 逻辑键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /*** 名称*/
    private String name;
    /**
     * 介绍
     */
    private String intro;
    /*** 父节点*/
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private MapTree parent;
    /*** 码表树子类* */
    @OneToMany(mappedBy = "parent")
    @OrderBy("id")
    private Set<MapTree> children;

    public MapTree(){}

    public MapTree(Long id){
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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public MapTree getParent() {
        return parent;
    }

    public void setParent(MapTree parent) {
        this.parent = parent;
    }

    public Set<MapTree> getChildren() {
        return children;
    }

    public void setChildren(Set<MapTree> children) {
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

