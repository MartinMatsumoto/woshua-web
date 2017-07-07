package com.woshua.structure.catalogue.service;

import com.woshua.structure.catalogue.domain.Catalogue;
import com.woshua.structure.catalogue.repository.CatalogueRepository;
import com.woshua.structure.catalogue.specification.CatalogueSpecification;
import com.woshua.structure.maptree.domain.MapTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Acer on 2017/7/3.
 */
@Service("catalogueService")
public class CatalogueServiceImpl implements CatalogueService {

    @Autowired
    private CatalogueRepository catalogueRepository;

    @Override
    public List<Catalogue> listCatalogue(MapTree grade, MapTree major, Catalogue parent) {
        Catalogue catalogue = new Catalogue();
        catalogue.setParent(parent);
        catalogue.setGrade(grade);
        catalogue.setMajor(major);

        List<Catalogue> catalogues = catalogueRepository.findAll(new CatalogueSpecification(catalogue));
        return catalogues;
    }
}
