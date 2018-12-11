package es.ucm.fdi.gaia.recolibry.examples.test2;

import com.google.inject.AbstractModule;
import es.ucm.fdi.gaia.recolibry.RecommenderSystemFactory;
import es.ucm.fdi.gaia.recolibry.api.RecommenderAlgorithm;
import es.ucm.fdi.gaia.recolibry.api.RecommenderResult;
import es.ucm.fdi.gaia.recolibry.implementations.jcolibri.QueryJColibri;

import java.util.List;

public class App {

    public static void main(String[] args) {

        System.out.println(System.getProperty("user.dir"));

        AbstractModule configuration = new VideosRecSysConfiguration();
        RecommenderSystemFactory factory = new RecommenderSystemFactory();
        factory.makeRecommender(configuration);

        RecommenderAlgorithm algorithm = factory.getRecommender().getAlgorithm();

        // Prepare Query
        QueryJColibri query = new QueryJColibri();
        VideoDescription v = new VideoDescription(0, new Integer[]{1,0,0,0,0,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1}, "extremidades");
        query.setDescription(v);

        algorithm.init();
        List<RecommenderResult> results = algorithm.recommend(query);
        algorithm.close();

        for(RecommenderResult r : results)
            System.out.println(r);
    }

}
