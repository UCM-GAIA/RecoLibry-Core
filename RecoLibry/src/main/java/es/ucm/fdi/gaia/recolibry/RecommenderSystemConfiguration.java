package es.ucm.fdi.gaia.recolibry;

import com.google.inject.AbstractModule;

import es.ucm.fdi.gaia.recolibry.api.Query;
import es.ucm.fdi.gaia.recolibry.api.RecommenderAlgorithm;
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
		bind(RecommenderAlgorithm.class).to(RecommenderJColibri.class);
		bind(Query.class).to(QueryJColibri.class);
	}
	
}
