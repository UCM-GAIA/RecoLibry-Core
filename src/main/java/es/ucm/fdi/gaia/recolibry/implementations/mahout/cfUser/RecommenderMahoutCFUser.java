package es.ucm.fdi.gaia.recolibry.implementations.mahout.cfUser.algorithms;

import com.google.inject.name.Named;
import es.ucm.fdi.gaia.recolibry.api.Query;
import es.ucm.fdi.gaia.recolibry.api.RecommenderAlgorithm;
import es.ucm.fdi.gaia.recolibry.api.RecommenderResult;
import es.ucm.fdi.gaia.recolibry.implementations.mahout.cfUser.MahoutCFUserQuery;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a generic user recommender system using Mahout framework.
 *
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 */
public class RecommenderMahoutCFUser implements RecommenderAlgorithm {

    private DataModel dataModel;
    private UserNeighborhood neighborhood;
    private UserSimilarity userSimilarity;
    private int numResults;
    private UserBasedRecommender recommender;

    @Inject
    public RecommenderMahoutCFUser(DataModel dataModel, UserNeighborhood neighborhood, UserSimilarity userSimilarity, @Named("numResults") int numResults){
        this.dataModel = dataModel;
        this.neighborhood = neighborhood;
        this.userSimilarity = userSimilarity;
        this.numResults = numResults;
    }

    @Override
    public boolean init() {
        recommender = new GenericUserBasedRecommender(dataModel, neighborhood, userSimilarity);
        return true;
    }

    @Override
    public List<RecommenderResult> recommend(Query query) {
        List<RecommenderResult> results = null;

        try {
            List<RecommendedItem> resultsMahout = recommender.recommend(((MahoutCFUserQuery) query).getBean(), numResults, null, false);

            results = new ArrayList<>();

            for(RecommendedItem r : resultsMahout) {
                RecommenderResult res = new RecommenderResult(r.getItemID(), r.getValue());
                results.add(res);
            }

        } catch (TasteException e) {
            e.printStackTrace();
        }

        return results;
    }

    @Override
    public boolean close() {
        return false;
    }
}
