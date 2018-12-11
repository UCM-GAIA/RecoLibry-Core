package es.ucm.fdi.gaia.recolibry.examples.test1;

import com.google.inject.AbstractModule;
import es.ucm.fdi.gaia.recolibry.RecommenderSystemFactory;
import es.ucm.fdi.gaia.recolibry.api.RecommenderAlgorithm;
import es.ucm.fdi.gaia.recolibry.api.RecommenderResult;
import es.ucm.fdi.gaia.recolibry.implementations.jcolibri.QueryJColibri;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    
	public static void main(String[] args){
		// RecommenderAlgorithm algorithm = new RecommenderJColibri();
		AbstractModule configuration = new MovieRecSysConfiguration();
		RecommenderSystemFactory factory = new RecommenderSystemFactory();
		factory.makeRecommender(configuration);
		RecommenderAlgorithm algorithm = factory.getRecommender().getAlgorithm(); 
		
		
		// Prepare Query
		QueryJColibri query = new QueryJColibri();
		MovieCase c = new MovieCase(0, null, new String[]{"Adventure", "Children", "Fantasy"});
		query.setDescription(c);
		
		algorithm.init();
		List<RecommenderResult> results = algorithm.recommend(query);
		algorithm.close();
		
		for(RecommenderResult r : results)
			System.out.println(r);
		
    }
}
