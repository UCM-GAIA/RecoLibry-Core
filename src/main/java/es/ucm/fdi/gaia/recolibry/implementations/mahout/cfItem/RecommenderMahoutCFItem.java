/**
 * RecoLibry-Core Source Code
 * Copyright (C) 2019  Jose L. Jorro-Aragoneses
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package es.ucm.fdi.gaia.recolibry.implementations.mahout.cfItem;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import es.ucm.fdi.gaia.recolibry.api.Query;
import es.ucm.fdi.gaia.recolibry.api.RecommenderAlgorithm;
import es.ucm.fdi.gaia.recolibry.api.RecommenderResult;
import es.ucm.fdi.gaia.recolibry.implementations.mahout.models.DataModelFactory;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a generic item recommender system using Mahout framework.
 *
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 */
public class RecommenderMahoutCFItem implements RecommenderAlgorithm {

    private DataModel dataModel;
    private ItemSimilarity itemSimilarity;
    private int numResults;
    private ItemBasedRecommender recommender;

    /**
     * Method to build the recommender system.
     * @param dataModel data model that saves all information used by the recommender system.
     * @param itemSimilarity similarity function configure to the recommender system.
     * @param numResults maximum number of results returned by the recommender system.
     */
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
