package com.woshua.structure.catalogue.repository;

import com.woshua.structure.catalogue.domain.Catalogue;
import com.woshua.structure.praxis.domain.Praxis;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Acer on 2017/7/4.
 */
public interface CatalogueRepository extends CrudRepository<Catalogue, Long>, JpaSpecificationExecutor<Catalogue> {

    public List<Catalogue> findAll(Specification<Catalogue> params);

    public Catalogue findById(Long id);
}
