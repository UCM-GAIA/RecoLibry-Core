package es.ucm.fdi.gaia.recolibry.examples.test1;

import es.ucm.fdi.gaia.jcolibri.exception.NoApplicableSimilarityFunctionException;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

public class GenresSimilarity implements LocalSimilarityFunction {

	public double compute(Object caseObject, Object queryObject) throws NoApplicableSimilarityFunctionException {
		String[] caseGenres = (String[]) caseObject;
		String[] queryGenres = (String[]) queryObject;
		
		double equal = 0;
		double totalGenres = 0;
		
		for(int i=0; i<caseGenres.length; i++)
			for(int j=0; j<queryGenres.length; j++)
				if (caseGenres[i].equals(queryGenres[j]))
					equal++;
		
		totalGenres = caseGenres.length + queryGenres.length - equal;
		
		return equal / totalGenres;
	}

	public boolean isApplicable(Object caseObject, Object queryObject) {
		if (caseObject != null && queryObject != null && 
				caseObject instanceof String[] && queryObject instanceof String[])
				return true;
		else
			return false;
	}

}
