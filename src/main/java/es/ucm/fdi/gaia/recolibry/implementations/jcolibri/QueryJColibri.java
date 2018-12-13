package es.ucm.fdi.gaia.recolibry.implementations.jcolibri;

import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRQuery;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;
import es.ucm.fdi.gaia.recolibry.api.Query;
import es.ucm.fdi.gaia.recolibry.utils.BeansFactory;

public class QueryJColibri extends CBRQuery implements Query {

    @Override
    public void setBean(Object bean) {
        this.setDescription((CaseComponent) bean);
    }
}
