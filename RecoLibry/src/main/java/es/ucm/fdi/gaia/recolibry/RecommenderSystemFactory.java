package es.ucm.fdi.gaia.recolibry;

import com.google.inject.Guice;
import com.google.inject.Injector;

import es.ucm.fdi.gaia.recolibry.api.RecommenderSystem;

public class RecommenderSystemFactory {
	
	private RecommenderSystem recommender;
	
	public RecommenderSystemFactory() {}
	
	public void makeRecommender() {
		Injector injector = Guice.createInjector(new RecommenderSystemConfiguration());
		recommender = injector.getInstance(RecommenderSystem.class);
	}
	
	public void makeRecommenderByJson(String file) {
		
	}
	
	public RecommenderSystem getRecommender() {
		return recommender;
	}

}
