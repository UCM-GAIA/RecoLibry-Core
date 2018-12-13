package es.ucm.fdi.gaia.recolibry.examples.test1;

import es.ucm.fdi.gaia.jcolibri.exception.NoApplicableSimilarityFunctionException;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

import java.util.List;

public class GenresSimilarity implements LocalSimilarityFunction {

	public double compute(Object caseObject, Object queryObject) throws NoApplicableSimilarityFunctionException {
		List<String> caseGenres = (List<String>) caseObject;
		List<String> queryGenres = (List<String>) queryObject;
		
		double equal = 0;
		double totalGenres = 0;
		
		for(int i=0; i<caseGenres.size(); i++)
			for(int j=0; j<queryGenres.size(); j++)
				if (caseGenres.get(i).equals(queryGenres.get(j)))
					equal++;
		
		totalGenres = caseGenres.size() + queryGenres.size() - equal;
		
		return equal / totalGenres;
	}

	public boolean isApplicable(Object caseObject, Object queryObject) {
		if (caseObject != null && queryObject != null && 
				caseObject instanceof List && queryObject instanceof List)
				return true;
		else
			return false;
	}

}
