package es.ucm.fdi.gaia.recolibry.implementations.mahout.cfItem;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import es.ucm.fdi.gaia.recolibry.api.Query;
import es.ucm.fdi.gaia.recolibry.api.RecommenderAlgorithm;
import es.ucm.fdi.gaia.recolibry.api.RecommenderResult;
import es.ucm.fdi.gaia.recolibry.implementations.mahout.cfUser.MahoutCFUserQuery;
import es.ucm.fdi.gaia.recolibry.implementations.mahout.models.DataModelFactory;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import java.util.ArrayList;
import java.util.List;

public class RecommenderMahoutCFItem implements RecommenderAlgorithm {

    private DataModel dataModel;
    private ItemSimilarity itemSimilarity;
    private int numResults;
    private ItemBasedRecommender recommender;

    @Inject
    public RecommenderMahoutCFItem(DataModelFactory dataModel, ItemSimilarity itemSimilarity, @Named("numResults") int numResults){
        this.dataModel = dataModel.getModel();
        this.itemSimilarity = itemSimilarity;
        this.numResults = numResults;
    }

    @Override
    public boolean init() {
        recommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);
        return true;
    }

    @Override
    public List<RecommenderResult> recommend(Query query) {
        List<RecommenderResult> results = null;

        try {
            List<RecommendedItem> resultsMahout = recommender.recommend(((MahoutCFItemQuery) query).getItemId(), numResults, null, false);

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
