package es.ucm.fdi.gaia.recolibry;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Connector;
import es.ucm.fdi.gaia.recolibry.api.Query;
import es.ucm.fdi.gaia.recolibry.api.RecommenderAlgorithm;
import es.ucm.fdi.gaia.recolibry.implementations.jcolibri.CSVConnector;
import es.ucm.fdi.gaia.recolibry.implementations.jcolibri.QueryJColibri;
import es.ucm.fdi.gaia.recolibry.implementations.jcolibri.RecommenderJColibri;

/**
 * Esta clase es la encargada de preparar la configuración del recomendador
 * @author jljorro
 *
 */
public class RecommenderSystemConfiguration extends AbstractModule {

	@Override
	protected void configure() {
		// Configuración del CSV Connector
		bind(String.class).annotatedWith(Names.named("fileName")).toInstance("src/main/resources/movies.csv");
		bind(Boolean.class).annotatedWith(Names.named("existTitleRow")).toInstance(true);
		
		// Configuración de RecommenderJColibri
		bind(Connector.class).to(CSVConnector.class);
		bind(Integer.class).toInstance(10);
		
		// Configuración del sistema recomendador
		bind(RecommenderAlgorithm.class).to(RecommenderJColibri.class);
		bind(Query.class).to(QueryJColibri.class);
	}
	
}
