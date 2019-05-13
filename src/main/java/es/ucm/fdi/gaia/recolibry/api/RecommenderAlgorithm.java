/**
 * RecoLibry-Core Source Code
 * Copyright (C) 2019  Jose L. Jorro-Aragoneses
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
