package com.woshua.structure.praxis.repository;

import com.woshua.structure.praxis.domain.FavoriteRelation;
import com.woshua.structure.praxis.domain.Praxis;
import com.woshua.structure.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Acer on 2017/3/24.
 */
public interface FavoriteRelationRepository extends PagingAndSortingRepository<FavoriteRelation, Long> {

    @Override
    FavoriteRelation save(FavoriteRelation favoriteRelation);

    List<FavoriteRelation> findByUserAndPraxis(User user, Praxis praxis);

    Page<FavoriteRelation> findByUser(User user, Pageable pageable);
}
