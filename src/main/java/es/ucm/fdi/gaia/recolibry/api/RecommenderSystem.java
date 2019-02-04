package es.ucm.fdi.gaia.recolibry.api;

import com.google.inject.Inject;

import java.util.List;

/**
 * This is the main class to make recommender systems. It have to
 * contain an algorithm and a query to works.
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

	/**
	 * Return a query object.
	 * @return
	 */
	public Query getQuery() {
		return query;
	}

	/**
	 * It initializes the algorithm to start working.
	 */
	public void initRecommender() {
		algorithm.init();
	}

	/**
	 * Based on a query, it returns a list of recommendations.
	 * @param query object with the query values.
	 * @return list of recommended objects from the algorithm.
	 */
	public List<RecommenderResult> recommend(Query query) {
		return algorithm.recommend(query);
	}

	/**
	 * It finishes the recommender algorithm.
	 */
	public void closeRecommender() {
		algorithm.close();
	}
	
}
