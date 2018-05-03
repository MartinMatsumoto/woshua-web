package com.woshua.structure.praxis.domain;

import com.woshua.structure.catalogue.domain.Catalogue;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "praxis")
public class CataloguePraxisRelation {

    @ManyToOne
    @JoinColumn(name = "catalogue_id")
    private Catalogue catalogue;

    @ManyToOne
    @JoinColumn(name = "praxis_id")
    private Praxis praxis;

    public Catalogue getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(Catalogue catalogue) {
        this.catalogue = catalogue;
    }

    public Praxis getPraxis() {
        return praxis;
    }

    public void setPraxis(Praxis praxis) {
        this.praxis = praxis;
    }
}
