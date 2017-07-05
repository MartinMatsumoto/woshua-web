package com.woshua.structure.catalogue.specification;

import com.woshua.structure.catalogue.domain.Catalogue;
import com.woshua.structure.maptree.domain.MapTree;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 2017/7/4.
 */
public class CatalogueSpecification implements Specification<Catalogue> {

    private Catalogue criteria;

    public CatalogueSpecification(Catalogue params) {
        criteria = params;
    }

    @Override
    public Predicate toPredicate(Root<Catalogue> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        final List<Predicate> predicates = new ArrayList<>();
        Path<MapTree> grade = root.get("grade");
        Path<MapTree> major = root.get("major");
        Path<Catalogue> parent = root.get("parent");
        Path<Long> id = root.get("id");

        if (criteria.getGrade() != null) {
            predicates.add(criteriaBuilder.equal(grade, criteria.getGrade()));
        }

        if (criteria.getMajor() != null) {
            predicates.add(criteriaBuilder.equal(major, criteria.getMajor()));
        }

        if (criteria.getParent() != null) {
            predicates.add(criteriaBuilder.equal(parent, criteria.getParent()));
        }

        criteriaQuery.orderBy(criteriaBuilder.asc(id));
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
