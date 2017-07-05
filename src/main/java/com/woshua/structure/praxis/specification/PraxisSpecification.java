package com.woshua.structure.praxis.specification;

import com.woshua.structure.maptree.domain.MapTree;
import com.woshua.structure.praxis.domain.Praxis;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 2017/4/17.
 */
public class PraxisSpecification implements Specification<Praxis> {

    private Praxis criteria;

    public PraxisSpecification(Praxis params) {
        this.criteria = params;
    }

    @Override
    public Predicate toPredicate(Root<Praxis> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        final List<Predicate> predicates = new ArrayList<>();

        Path<MapTree> grade = root.get("grade");
        Path<MapTree> major = root.get("major");
        Path<MapTree> type = root.get("type");
        Path<Integer> difficult = root.get("dificulty");

        if (criteria.getGrade() != null) {
            predicates.add(criteriaBuilder.equal(grade, criteria.getGrade()));
        }

        if (criteria.getMajor() != null) {
            predicates.add(criteriaBuilder.equal(major, criteria.getMajor()));
        }

        if (criteria.getType() != null) {
            predicates.add(criteriaBuilder.equal(type, criteria.getType()));
        }

        if (criteria.getDificulty() > 0) {
            predicates.add(criteriaBuilder.equal(difficult, criteria.getDificulty()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
