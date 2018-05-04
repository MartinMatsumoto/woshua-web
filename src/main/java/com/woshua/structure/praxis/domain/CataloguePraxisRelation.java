package com.woshua.structure.praxis.domain;

import com.woshua.structure.catalogue.domain.Catalogue;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity(name = "catalogue_praxis")
public class CataloguePraxisRelation implements Serializable {

    @ManyToOne
    @JoinColumn(name = "catalogue_id")
    @Id
    private Catalogue catalogue;

    @ManyToOne
    @JoinColumn(name = "praxis_id")
    @Id
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
