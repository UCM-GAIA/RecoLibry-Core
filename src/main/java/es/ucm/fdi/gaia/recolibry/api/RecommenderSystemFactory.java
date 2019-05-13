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
