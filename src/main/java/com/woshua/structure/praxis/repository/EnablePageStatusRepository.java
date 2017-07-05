package com.woshua.structure.praxis.repository;

import com.woshua.structure.praxis.domain.EnablePageStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Acer on 2017/4/18.
 */
public interface EnablePageStatusRepository extends CrudRepository<EnablePageStatus, Long> {

    List<EnablePageStatus> findAll();
}
