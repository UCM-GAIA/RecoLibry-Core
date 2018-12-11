package es.ucm.fdi.gaia.recolibry.examples.test1;

import com.google.inject.AbstractModule;
import es.ucm.fdi.gaia.recolibry.RecommenderSystemFactory;
import es.ucm.fdi.gaia.recolibry.api.Query;
import es.ucm.fdi.gaia.recolibry.api.RecSysConfiguration;
import es.ucm.fdi.gaia.recolibry.api.RecommenderAlgorithm;
import es.ucm.fdi.gaia.recolibry.api.RecommenderResult;
import es.ucm.fdi.gaia.recolibry.implementations.jcolibri.QueryJColibri;

import java.util.List;

/**
 * Hello world!
 *
 */
public class Test1App {
    
	public static void main(String[] args){

		RecSysConfiguration configuration = new MovieRecSysConfiguration();
		RecommenderSystemFactory factory = new RecommenderSystemFactory();
		factory.makeRecommender(configuration);

		RecommenderAlgorithm algorithm = factory.getRecommender().getAlgorithm();
		
		// Prepare Query
		//TODO - Pensar como devolver la query
		Query query = factory.getQuery();
		/*MovieCase c = new MovieCase(0, null, new String[]{"Adventure", "Children", "Fantasy"});
		query.setDescription(c);*/
		
		algorithm.init();
		List<RecommenderResult> results = algorithm.recommend(query);
		algorithm.close();
		
		for(RecommenderResult r : results)
			System.out.println(r);
		
    }
}
