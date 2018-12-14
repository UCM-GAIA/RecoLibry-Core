package es.ucm.fdi.gaia.recolibry.api;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 */
public class RecommenderSystemFactory {
	
	private RecommenderSystem recommender;

	public RecommenderSystemFactory() {}
	
	public void makeRecommender(RecSysConfiguration recommenderConfiguration) {
		Injector injector = Guice.createInjector(recommenderConfiguration);
		recommender = injector.getInstance(RecommenderSystem.class);
	}
	
	public void makeRecommenderByJson(String file) {
		
	}

	public RecommenderSystem getRecommender() {
		return recommender;
	}

}
