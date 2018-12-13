package es.ucm.fdi.gaia.recolibry.examples.test2;

import com.google.inject.AbstractModule;
import es.ucm.fdi.gaia.recolibry.RecommenderSystemFactory;
import es.ucm.fdi.gaia.recolibry.api.Query;
import es.ucm.fdi.gaia.recolibry.api.RecommenderAlgorithm;
import es.ucm.fdi.gaia.recolibry.api.RecommenderResult;
import es.ucm.fdi.gaia.recolibry.implementations.jcolibri.QueryJColibri;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test2App {

    public static void main(String[] args) {

        AbstractModule configuration = new VideosRecSysConfiguration();
        RecommenderSystemFactory factory = new RecommenderSystemFactory();
        factory.makeRecommender(configuration);

        RecommenderAlgorithm algorithm = factory.getRecommender().getAlgorithm();

        // Prepare Query
        QueryJColibri query = new QueryJColibri();

        List<Integer> features = new ArrayList<>();
        int[] faturesArray = new int[]{1,0,0,0,0,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};

        for(int i=0; i< faturesArray.length; i++)
            features.add(faturesArray[i]);

        VideoDescription v = new VideoDescription(1, "Higiene corporal", features);
        query.setDescription(v);

        algorithm.init();
        List<RecommenderResult> results = algorithm.recommend(query);
        algorithm.close();

        for(RecommenderResult r : results)
            System.out.println(r);
    }

}
