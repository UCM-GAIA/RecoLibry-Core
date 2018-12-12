package es.ucm.fdi.gaia.recolibry.implementations.jcolibri;

import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRCase;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseBaseFilter;
import es.ucm.fdi.gaia.jcolibri.cbrcore.Connector;
import es.ucm.fdi.gaia.jcolibri.exception.InitializingException;
import es.ucm.fdi.gaia.recolibry.utils.BeansFactory;

import java.net.URL;
import java.util.Collection;

public class CSVConnectorNew implements Connector {

    private String filePath;
    private BeansFactory beansFactory;

    public CSVConnectorNew(BeansFactory beansFactory, String filePath) {
        this.filePath = filePath;
        this.beansFactory = beansFactory;
    }

    @Override
    public void initFromXMLfile(URL file) throws InitializingException {

    }

    @Override
    public void close() {

    }

    @Override
    public void storeCases(Collection<CBRCase> cases) {

    }

    @Override
    public void deleteCases(Collection<CBRCase> cases) {

    }

    @Override
    public Collection<CBRCase> retrieveAllCases() {
        return null;
    }

    @Override
    public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter filter) {
        return null;
    }
}
