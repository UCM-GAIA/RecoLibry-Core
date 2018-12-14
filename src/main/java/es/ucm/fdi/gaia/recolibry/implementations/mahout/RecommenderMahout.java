package es.ucm.fdi.gaia.recolibry.implementations.mahout;

import es.ucm.fdi.gaia.recolibry.api.Query;
import es.ucm.fdi.gaia.recolibry.api.RecommenderAlgorithm;
import es.ucm.fdi.gaia.recolibry.api.RecommenderResult;
import org.apache.mahout.cf.taste.recommender.Recommender;

import java.util.List;

/**
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 */
public class RecommenderMahout implements RecommenderAlgorithm {

    private Recommender recMahout;

    @Override
    public boolean init() {
        return false;
    }

    @Override
    public List<RecommenderResult> recommend(Query query) {
        return null;
    }

    @Override
    public boolean close() {
        return false;
    }
}
