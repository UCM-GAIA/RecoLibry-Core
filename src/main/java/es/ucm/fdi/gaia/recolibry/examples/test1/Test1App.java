package es.ucm.fdi.gaia.recolibry.examples.test1;

import es.ucm.fdi.gaia.recolibry.api.RecommenderSystemFactory;
import es.ucm.fdi.gaia.recolibry.api.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 */
public class Test1App {
    
	public static void main(String[] args){

        // Define the Recommender System Configuration
        RecSysConfiguration configuration = new MovieRecSysConfiguration();

        // Make a new instance of Recommender System
		RecommenderSystemFactory factory = new RecommenderSystemFactory();
		factory.makeRecommender(configuration);

        RecommenderSystem recSys = factory.getRecommender();

        // Get a Query used by Recommender System
        Query query = recSys.getQuery();

        // Insert data into query object
		List<String> genres = new ArrayList<>();
		String[] genresArray = new String[]{"Adventure", "Children", "Fantasy"};
		for(int i=0; i < genresArray.length; i++)
			genres.add(genresArray[i]);

		MovieCase c = new MovieCase(0, null, genres);
		query.setBean(c);

        // Execute a recommendation using this query
		recSys.initRecommender();
		List<RecommenderResult> results = recSys.recommend(query);

        // Close recommender system
		recSys.closeRecommender();

        // Print the result of recommender system
		for(RecommenderResult r : results)
			System.out.println(r);
		
    }
}
