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
package es.ucm.fdi.gaia.recolibry.implementations.mahout.cfUser;

import com.google.inject.name.Named;
import es.ucm.fdi.gaia.recolibry.api.Query;
import es.ucm.fdi.gaia.recolibry.api.RecommenderAlgorithm;
import es.ucm.fdi.gaia.recolibry.api.RecommenderResult;
import es.ucm.fdi.gaia.recolibry.implementations.mahout.models.DataModelFactory;
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
    public RecommenderMahoutCFUser(DataModelFactory dataModel, UserNeighborhood neighborhood, UserSimilarity userSimilarity, @Named("numResults") int numResults){
        this.dataModel = dataModel.getModel();
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
            List<RecommendedItem> resultsMahout = recommender.recommend(((MahoutCFUserQuery) query).getUserId(), numResults, null, false);

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
