package com.woshua.structure.catalogue.service;

import com.woshua.structure.catalogue.domain.Catalogue;
import com.woshua.structure.maptree.domain.MapTree;

import java.util.List;

/**
 * Created by Acer on 2017/7/3.
 */
public interface CatalogueService {

    public List<Catalogue> listCatalogue(MapTree grade, MapTree major, Catalogue parent);

    public List<Catalogue> listCatalogueInit();
}
