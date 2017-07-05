package com.woshua.structure.praxis.service;

import com.woshua.structure.maptree.domain.MapTree;
import com.woshua.structure.praxis.domain.Praxis;
import com.woshua.structure.user.domain.User;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * Created by Acer on 2017/3/28.
 */
public interface PraxisService {

    public Map<String, Object> listByParams(Pageable pageable, MapTree grade, MapTree decipline, MapTree type, int difficult, User user);

    public boolean addFavorite(Praxis praxis, User user);

    public boolean cancelFavorite(Praxis praxis, User user);

    public Map<String, Object> listFavorite(Pageable pageable, User user);
}
