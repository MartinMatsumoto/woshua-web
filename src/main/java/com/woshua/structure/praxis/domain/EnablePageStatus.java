package com.woshua.structure.praxis.domain;

import com.woshua.core.web.BaseEntity;
import com.woshua.structure.maptree.domain.MapTree;

import javax.persistence.*;

/**
 * Created by Acer on 2017/4/18.
 */
@Entity(name = "enable_page_status")
public class EnablePageStatus extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    private MapTree grade;

    @ManyToOne
    @JoinColumn(name = "major_id")
    private MapTree major;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
