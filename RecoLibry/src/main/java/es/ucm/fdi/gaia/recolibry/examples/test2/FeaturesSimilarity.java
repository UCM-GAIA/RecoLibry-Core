package es.ucm.fdi.gaia.recolibry.examples.test2;

import es.ucm.fdi.gaia.jcolibri.exception.NoApplicableSimilarityFunctionException;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

public class FeaturesSimilarity implements LocalSimilarityFunction {

    @Override
    public double compute(Object caseObject, Object queryObject) throws NoApplicableSimilarityFunctionException {

        int[] caseFeatures = (int[]) caseObject;
        int[] queryFeatures = (int[]) queryObject;

        double similarity = 0.0;

        for(int i=0; i < caseFeatures.length; i++)
            similarity += caseFeatures[i] * queryFeatures[i];

        return similarity / caseFeatures.length;
    }

    @Override
    public boolean isApplicable(Object caseObject, Object queryObject) {
        if (caseObject != null && queryObject != null &&
                caseObject instanceof int[] && queryObject instanceof int[])
            return true;
        else
            return false;
    }

}
