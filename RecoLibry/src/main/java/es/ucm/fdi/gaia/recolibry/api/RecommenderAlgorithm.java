package es.ucm.fdi.gaia.recolibry.api;

import java.util.List;

/**
 * This interface specifies the main structure of all recommender
 * algorithm used in RecoLibry. This interface should be implemented
 * when a new algorithm is introduced in RecoLibry.
 * 
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 */
public interface RecommenderAlgorithm {

	/**
	 * Method to initialize a recommender algorithm. For example, if
	 * the algorithm has a training step, it is implemented in this method.
	 * @return true when the algorithm is initialized correctly, false in other case.
	 */
	boolean init();
	
	/**
	 * Method to calculate a recommendation in the algorithm implemented.
	 * @param query Query that contains the information to make a recomendation.
	 * @return List of elements recommended by the algorithm.
	 */
	List<RecommenderResult> recommend(Query query);
	
	/**
	 * Method to finish and close a recommender algorithm. For example, if
	 * the algorithm needs to save a new model, it is implemented in this method.
	 * @return true when the algorithm is finished and closed correctly, false in other case.
	 */
	boolean close();
}
