package com.woshua.structure.maptree.repository;

import com.woshua.structure.maptree.domain.MapTree;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Acer on 2017/3/24.
 */
public interface MaptreeRepository extends CrudRepository<MapTree, Long> {

    public List<MapTree> findByParentId(Long parentId);
}
