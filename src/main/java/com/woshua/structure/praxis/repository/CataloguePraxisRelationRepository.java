package com.woshua.structure.praxis.repository;

import com.woshua.structure.praxis.domain.Praxis;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Acer on 2017/3/24.
 */
public interface CataloguePraxisRelationRepository extends PagingAndSortingRepository<Praxis, Long>, JpaSpecificationExecutor<Praxis> {

}
