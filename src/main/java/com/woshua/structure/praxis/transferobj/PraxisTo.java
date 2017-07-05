package com.woshua.structure.praxis.transferobj;

import com.woshua.structure.praxis.domain.Praxis;

/**
 * Created by Acer on 2017/3/23.
 */
public class PraxisTo {

    private long id;

    private int clickRate;

    private String gradeName;

    private Long gradeId;

    private String majorName;

    private Long majorId;

    private String typeName;

    private Long typeId;

    private String content;

    private String prompt;

    private String resolve;

    private String answer;

    private int goodNum;

    private int badNum;

    private String position;

    private int dificulty;

    private boolean favorite;

    public PraxisTo(Praxis praxis) {
        this.id = praxis.getId();
        this.clickRate = praxis.getClickRate();
        this.typeName = praxis.getType().getName();
        this.typeId = praxis.getType().getId();
        this.gradeId = praxis.getGrade().getId();
        this.gradeName = praxis.getGrade().getName();
        this.majorId = praxis.getMajor().getId();
        this.majorName = praxis.getMajor().getName();
        this.content = praxis.getContent();
        this.prompt = praxis.getPrompt();
        this.resolve = praxis.getResolve();
        this.answer = praxis.getAnswer();
        this.goodNum = praxis.getGoodNum();
        this.badNum = praxis.getBadNum();
        this.dificulty = praxis.getDificulty();
    }

    public PraxisTo(Praxis praxis,boolean isFavorite){
        this(praxis);
        this.favorite = isFavorite;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getDificulty() {
        return dificulty;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void setDificulty(int dificulty) {
        this.dificulty = dificulty;
    }
}
