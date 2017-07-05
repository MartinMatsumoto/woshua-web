package com.woshua.structure.maptree.service;

import com.woshua.structure.maptree.domain.MapTree;

import java.util.List;

/**
 * Created by Acer on 2017/3/27.
 */
public interface MapTreeService {

    public List<MapTree> listGrades();

    public List<MapTree> listDeciplines();

    public List<MapTree> listPraxisType();

}
