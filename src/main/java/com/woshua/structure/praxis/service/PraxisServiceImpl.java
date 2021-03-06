package com.woshua.structure.praxis.service;

import com.github.wenhao.jpa.PredicateBuilder;
import com.github.wenhao.jpa.Specifications;
import com.woshua.structure.maptree.domain.MapTree;
import com.woshua.structure.praxis.domain.FavoriteRelation;
import com.woshua.structure.praxis.domain.Praxis;
import com.woshua.structure.praxis.repository.FavoriteRelationRepository;
import com.woshua.structure.praxis.repository.PraxisRepository;
import com.woshua.structure.praxis.transferobj.PraxisTo;
import com.woshua.structure.user.domain.User;
import com.woshua.structure.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Acer on 2017/3/28.
 */
@Service("praxisService")
public class PraxisServiceImpl implements PraxisService {

    @Autowired
    private PraxisRepository praxisRepository;

    @Autowired
    private FavoriteRelationRepository favoriteRelationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Map<String, Object> listByParams(Pageable pageable, MapTree grade, MapTree decipline, MapTree type, int difficult, User user, String catalogueId) {
        Page<Praxis> praxisList;
        Map<String, Object> result = new HashMap<>();

        PredicateBuilder<Praxis> builder = Specifications.<Praxis>and();

        if (grade != null) {
            builder.eq("grade", grade);
        }

        if (decipline != null) {
            builder.eq("major", decipline);
        }

        if (type != null) {
            builder.eq("type", type);
        }

        if (difficult != -1) {
            builder.eq("dificulty", difficult);
        }

        if (catalogueId != null) {
            builder.eq("catalogues.id", catalogueId);
        }

        praxisList = praxisRepository.findAll(builder.build(),pageable);
        List<PraxisTo> praxisToList = new ArrayList<>();

        if (praxisList != null) {
            for (Praxis praxis : praxisList) {
                boolean isFavorite = false;
                if (user != null) {
                    List<FavoriteRelation> list = favoriteRelationRepository.findByUserAndPraxis(user, praxis);
                    if (list != null && !list.isEmpty()) {
                        isFavorite = true;
                    }
                }
                praxisToList.add(new PraxisTo(praxis, isFavorite));
            }
        }

        result.put("praxisList", praxisToList);
        result.put("tatolPage", praxisList == null ? 0 : praxisList.getTotalPages());
        return result;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean addFavorite(Praxis praxis, User user) {
        //UserStatistic userStatistic = userStatisticRepository.findByUser(user);


        List<FavoriteRelation> list = favoriteRelationRepository.findByUserAndPraxis(user, praxis);
        if (list != null && !list.isEmpty()) {
            return false;
        }

        favoriteRelationRepository.save(new FavoriteRelation(user, praxis));
        user.addFavoriteCount();
        userRepository.save(user);
        return true;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean cancelFavorite(Praxis praxis, User user) {
        List<FavoriteRelation> list = favoriteRelationRepository.findByUserAndPraxis(user, praxis);
        if (list != null && !list.isEmpty()) {
            favoriteRelationRepository.delete(list);
        }
        user.decreaseFavoriteCount();
        userRepository.save(user);
        return true;
    }

    @Override
    public Map<String, Object> listFavorite(Pageable pageable, User user) {
        Page<FavoriteRelation> favoriteRelations;
        Map<String, Object> result = new HashMap<>();

        /*Sort sort = new Sort(Sort.Direction.DESC, "id");
        pageable.getSort().and(sort);*/

        favoriteRelations = favoriteRelationRepository.findByUser(user, pageable);
        List<PraxisTo> praxisToList = new ArrayList<>();
        if (favoriteRelations != null) {
            for (FavoriteRelation favoriteRelation : favoriteRelations) {
                praxisToList.add(new PraxisTo(favoriteRelation.getPraxis(), true));
            }
        }
        result.put("praxisList", praxisToList);
        result.put("tatolPage", favoriteRelations.getTotalPages());
        return result;
    }
}
