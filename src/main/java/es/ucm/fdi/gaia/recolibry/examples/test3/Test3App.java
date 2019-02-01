package es.ucm.fdi.gaia.recolibry.examples.test3;

import es.ucm.fdi.gaia.recolibry.api.*;
import es.ucm.fdi.gaia.recolibry.implementations.mahout.queries.MahoutCFUserQuery;

import java.util.List;

public class Test3App {

    public static void main(String[] args) {

        // Define the Recommender System Configuration
        RecSysConfiguration configuration = new MovieCFUserConfiguration();

        // Make a new instance of Recommender System
        RecommenderSystemFactory factory = new RecommenderSystemFactory();
        factory.makeRecommender(configuration);

        RecommenderSystem recSys = factory.getRecommender();

        // Get a Query used by Recommender System
        Query query = recSys.getQuery();

        // Insert data into query object
        long user = 414;
        query.setBean(user);

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
