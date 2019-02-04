package es.ucm.fdi.gaia.recolibry.implementations.mahout.queries;

import es.ucm.fdi.gaia.recolibry.api.Query;

public class MahoutCFUserQuery implements Query {

    private Long bean;

    @Override
    public void setBean(Object bean) {
        this.bean = (Long) bean;
    }

    public long getBean() {
        return bean;
    }
}
