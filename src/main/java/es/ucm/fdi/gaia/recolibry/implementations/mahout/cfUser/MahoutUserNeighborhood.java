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

import com.google.inject.Inject;
import com.google.inject.name.Named;
import es.ucm.fdi.gaia.recolibry.implementations.mahout.models.DataModelFactory;
import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import javax.annotation.Nullable;
import java.util.Collection;

/**
 * Class to select which neighborhood algorithms use in a {@link RecommenderMahoutCFUser}. There
 * are two algorithms to select: NearesN (the n most similar users) and Threshold (users with a
 * similarity value mayor than a threshold).
 *
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 */
public class MahoutUserNeighborhood implements UserNeighborhood {

    private UserNeighborhood userNeighborhood;

    /**
     * Method to build a Neighborhood algorithm.
     * @param type select the algorithm type: NearestN or Threshold.
     * @param n number of users to recover. It is used if the algorithm type is NearestN.
     * @param threshold threshold to recover users. It is used if the algorithm type is Threshold.
     * @param similarity similarity function used in the algorithm.
     * @param model data model to apply this algorithm.
     */
    @Inject
    public MahoutUserNeighborhood(@Named("TypeNeighborhood") String type, @Named("N-Users") int n, @Named("Threshold") @Nullable double threshold, UserSimilarity similarity, DataModelFactory model) {

        try {

            switch (type) {
                case "NearestN":
                    userNeighborhood = new NearestNUserNeighborhood(n, similarity, model.getModel());
                    break;
                case "Threshold":
                    userNeighborhood = new ThresholdUserNeighborhood(threshold, similarity, model.getModel());
                    break;
                default:
                    break;
            }

        } catch (TasteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public long[] getUserNeighborhood(long l) throws TasteException {
        return userNeighborhood.getUserNeighborhood(l);
    }

    @Override
    public void refresh(Collection<Refreshable> collection) {
        userNeighborhood.refresh(collection);
    }
}
