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

/**
 * Class that contains the result of a recommendation. Its minimum attributes
 * are the recommended object, for example a movie, and a recommended Value,
 * for example a predicted rating.
 *
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 */
public class RecommenderResult {
	
	private Object item;
	private double recommendedValue;

	/**
	 * Build a {@link RecommenderResult} based on a recommended object and
	 * its recommended value.
	 * @param item recommended object.
	 * @param recommendedValue recommended value.
	 */
	public RecommenderResult(Object item, double recommendedValue) {
		super();
		this.item = item;
		this.recommendedValue = recommendedValue;
	}

	/**
	 * Return the recommended object from the recommender system.
	 * @return recommended object.
	 */
	public Object getItem() {
		return item;
	}

	/**
	 * Return the recommended value of the object.
	 * @return recommended value.
	 */
	public double getRecommendedValue() {
		return recommendedValue;
	}

	@Override
	public String toString() {
		return "RecommenderResult [item=" + item + ", recommendedValue=" + recommendedValue + "]";
	}

}
