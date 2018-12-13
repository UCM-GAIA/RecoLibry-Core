package es.ucm.fdi.gaia.recolibry.examples.test2;

import com.google.inject.AbstractModule;
import es.ucm.fdi.gaia.recolibry.RecommenderSystemFactory;
import es.ucm.fdi.gaia.recolibry.api.Query;
import es.ucm.fdi.gaia.recolibry.api.RecommenderAlgorithm;
import es.ucm.fdi.gaia.recolibry.api.RecommenderResult;
import es.ucm.fdi.gaia.recolibry.api.RecommenderSystem;
import es.ucm.fdi.gaia.recolibry.implementations.jcolibri.QueryJColibri;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test2App {

    public static void main(String[] args) {
        // Define the Recommender System Configuration
        AbstractModule configuration = new VideosRecSysConfiguration();

        // Make a new instance of Recommender System
        RecommenderSystemFactory factory = new RecommenderSystemFactory();
        factory.makeRecommender(configuration);

        RecommenderSystem recSys = factory.getRecommender();

        // Get a Query used by Recommender System
        Query query = recSys.getQuery();

        // Insert data into query object
        List<Integer> features = new ArrayList<>();
        int[] faturesArray = new int[]{1,0,0,0,0,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        for(int i=0; i< faturesArray.length; i++)
            features.add(faturesArray[i]);

        VideoDescription v = new VideoDescription(1, "Higiene corporal", features);

        ((QueryJColibri) query).setDescription(v);

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
