package es.ucm.fdi.gaia.recolibry.implementations.mahout.cfUser;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import javax.annotation.Nullable;
import java.util.Collection;

public class MahoutUserNeighborhood implements UserNeighborhood {

    private UserNeighborhood userNeighborhood;

    @Inject
    public MahoutUserNeighborhood(@Named("TypeNeighborhood") String type, @Named("N-Users") int n, @Named("Threshold") @Nullable double threshold, UserSimilarity similarity, DataModel model) {

        try {

            switch (type) {
                case "NearestN":
                    userNeighborhood = new NearestNUserNeighborhood(n, similarity, model);
                    break;
                case "Threshold":
                    userNeighborhood = new ThresholdUserNeighborhood(threshold, similarity, model);
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
