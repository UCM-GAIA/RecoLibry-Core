package es.ucm.fdi.gaia.recolibry.api;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Class to build a instance of a recommender system based on a
 * recommender system configuration.
 *
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 */
public class RecommenderSystemFactory {
	
	private RecommenderSystem recommender;

	/**
	 * Method to build a recommender system using an object of {@link RecSysConfiguration}.
	 * @param recommenderConfiguration object that contains the recommender system configuration.
	 */
	public void makeRecommender(RecSysConfiguration recommenderConfiguration) {
		Injector injector = Guice.createInjector(recommenderConfiguration);
		recommender = injector.getInstance(RecommenderSystem.class);
	}

	/**
	 * Method to build a recommender system using a configuration file.
	 * @param jsonFile path where is the recommender system configuration file.
	 */
	public void makeRecommenderByJson(String jsonFile) {
		JsonRecSysConfiguration configuration = new JsonRecSysConfiguration(jsonFile);
		Injector injector = Guice.createInjector(configuration);
		recommender = injector.getInstance(RecommenderSystem.class);
	}

	/**
	 * Method to get the recommender system instance.
	 * @return instance of the recommender system configured.
	 */
	public RecommenderSystem getRecommender() {
		return recommender;
	}

}
