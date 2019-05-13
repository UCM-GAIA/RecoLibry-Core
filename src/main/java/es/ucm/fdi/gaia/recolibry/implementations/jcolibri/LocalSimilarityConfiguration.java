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
package es.ucm.fdi.gaia.recolibry.implementations.jcolibri;

import com.google.inject.Inject;

import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

/**
 * Class to define the local similarity function of a specific
 * attribute in a class. It is used in recommender systems make
 * with jCOLIBRI.
 * 
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 */
public class LocalSimilarityConfiguration {
	
	private String attributeName;
	private Class<?> clazz;
	private LocalSimilarityFunction similarityFunction;
	private Double weight;

	/**
	 * Constructor of a local similarity configuration.
	 * @param attributeName name of the attribute used in local similarity.
	 * @param clazz class where is the attribute used in local similarity.
	 * @param similarityFunction similarity function used.
	 */
	@Inject
	public LocalSimilarityConfiguration(String attributeName, Class<?> clazz, LocalSimilarityFunction similarityFunction) {
		super();
		this.attributeName = attributeName;
		this.clazz = clazz;
		this.similarityFunction = similarityFunction;
		this.weight = null;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public LocalSimilarityFunction getSimilarityFunction() {
		return similarityFunction;
	}

	/**
	 * Set the attribute weight in the global similarity function.
	 * @param weight
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getWeight() {
		return weight;
	}
}
