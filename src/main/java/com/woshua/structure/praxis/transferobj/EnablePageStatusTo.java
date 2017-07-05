package com.woshua.structure.praxis.transferobj;

import com.woshua.structure.maptree.domain.MapTree;
import com.woshua.structure.praxis.domain.EnablePageStatus;

/**
 * Created by Acer on 2017/4/18.
 */
public class EnablePageStatusTo {

    private Long id;

    private String gradeName;

    private Long gradeId;

    private String majorName;

    private Long majorId;

    public EnablePageStatusTo(EnablePageStatus enablePageStatus) {
        this.id = enablePageStatus.getId();
        MapTree major = enablePageStatus.getMajor();
        MapTree grade = enablePageStatus.getGrade();

        if (major != null) {
            this.majorId = major.getId();
            this.majorName = major.getName();
        }

        if (grade != null) {
            this.gradeId = grade.getId();
            this.gradeName = grade.getName();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }
}
