package es.ucm.fdi.gaia.recolibry.api;

import java.util.List;

import com.google.inject.Inject;

/**
 * This is the main class to make recommender systems.
 * 
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 */
public class RecommenderSystem {

	private RecommenderAlgorithm algorithm;
	private Query query;
	
	@Inject
	public RecommenderSystem(RecommenderAlgorithm algorithm, Query query) {
		super();
		this.algorithm = algorithm;
		this.query = query;
	}

	public RecommenderAlgorithm getAlgorithm() {
		return algorithm;
	}

	public Query getQuery() {
		return query;
	}
	
	public List<RecommenderResult> recommend(Query query) {
		return algorithm.recommend(query);
	}
	
}
