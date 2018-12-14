package es.ucm.fdi.gaia.recolibry.examples.test2;

import es.ucm.fdi.gaia.jcolibri.exception.NoApplicableSimilarityFunctionException;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

import java.util.List;

/**
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 */
public class FeaturesSimilarity implements LocalSimilarityFunction {

    @Override
    public double compute(Object caseObject, Object queryObject) throws NoApplicableSimilarityFunctionException {

        List<Integer> caseFeatures = (List<Integer>) caseObject;
        List<Integer> queryFeatures = (List<Integer>) queryObject;

        double similarity = 0.0;

        for(int i=0; i < caseFeatures.size(); i++)
            similarity += (caseFeatures.get(i).equals(queryFeatures.get(i))? 1: 0);

        return similarity / caseFeatures.size();
    }

    @Override
    public boolean isApplicable(Object caseObject, Object queryObject) {
        if (caseObject != null && queryObject != null &&
                caseObject instanceof List && queryObject instanceof List)
            return true;
        else
            return false;
    }

}
