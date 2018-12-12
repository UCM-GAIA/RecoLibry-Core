package es.ucm.fdi.gaia.recolibry;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import es.ucm.fdi.gaia.recolibry.api.Query;
import es.ucm.fdi.gaia.recolibry.api.RecommenderSystem;

public class RecommenderSystemFactory {
	
	private RecommenderSystem recommender;
	
	public RecommenderSystemFactory() {}
	
	public void makeRecommender(AbstractModule recommenderConfiguration) {
		Injector injector = Guice.createInjector(recommenderConfiguration);
		recommender = injector.getInstance(RecommenderSystem.class);
	}
	
	public void makeRecommenderByJson(String file) {
		
	}

	public Query getQuery() {
		return null;
	}
	
	public RecommenderSystem getRecommender() {
		return recommender;
	}

}
