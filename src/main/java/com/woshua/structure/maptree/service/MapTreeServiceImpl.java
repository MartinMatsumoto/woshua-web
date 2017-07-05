package com.woshua.structure.maptree.service;

import com.woshua.structure.maptree.domain.MapTree;
import com.woshua.structure.maptree.repository.MaptreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Acer on 2017/3/27.
 */
@Service("mapTreeService")
public class MapTreeServiceImpl implements MapTreeService {

    @Autowired
    private MaptreeRepository maptreeRepository;

    @Override
    public List<MapTree> listGrades() {
        return maptreeRepository.findByParentId(MapTree.GRADE_ID);
    }

    @Override
    public List<MapTree> listDeciplines() {
        return maptreeRepository.findByParentId(MapTree.MAJOR_ID);
    }

    @Override
    public List<MapTree> listPraxisType() {
        return maptreeRepository.findByParentId(MapTree.PRAXIS_TYPE);
    }
}
