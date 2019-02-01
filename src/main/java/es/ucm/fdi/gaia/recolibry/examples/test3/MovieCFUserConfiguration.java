package es.ucm.fdi.gaia.recolibry.examples.test3;

import com.google.inject.name.Names;
import es.ucm.fdi.gaia.recolibry.api.Query;
import es.ucm.fdi.gaia.recolibry.api.RecSysConfiguration;
import es.ucm.fdi.gaia.recolibry.api.RecommenderAlgorithm;
import es.ucm.fdi.gaia.recolibry.implementations.mahout.MahoutUserNeighborhood;
import es.ucm.fdi.gaia.recolibry.implementations.mahout.MahoutUserSimilarity;
import es.ucm.fdi.gaia.recolibry.implementations.mahout.algorithms.RecommenderMahoutCFUser;
import es.ucm.fdi.gaia.recolibry.implementations.mahout.models.MahoutFileDataModel;
import es.ucm.fdi.gaia.recolibry.implementations.mahout.queries.MahoutCFUserQuery;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;

public class MovieCFUserConfiguration extends RecSysConfiguration {

    @Override
    protected void generateClass() {}

    @Override
    protected void configure() {

        //Step 1: Set File Model
        File file = new File(System.getProperty("user.dir") + "/csv/ratings.csv");

        bind(File.class).toInstance(file);
        bind(String.class).annotatedWith(Names.named("delimiter")).toInstance(",");

        //Step 2: Select Similarity (Euclidean)
        bind(String.class).annotatedWith(Names.named("TypeUserSimilarity")).toInstance("Pearson");
        bind(DataModel.class).to(MahoutFileDataModel.class);

        //Step 3: Select Neighborhood
        bind(String.class).annotatedWith(Names.named("TypeNeighborhood")).toInstance("NearestN");
        bind(Integer.class).annotatedWith(Names.named("N-Users")).toInstance(5);
        bind(Double.class).annotatedWith(Names.named("Threshold")).toInstance(0.0);
        bind(UserSimilarity.class).to(MahoutUserSimilarity.class);
        //bind(DataModel.class).to(MahoutFileDataModel.class);

        //Step 4: Configure Algorithm
        //bind(DataModel.class).to(MahoutFileDataModel.class);
        bind(UserNeighborhood.class).to(MahoutUserNeighborhood.class);
        bind(UserSimilarity.class).to(MahoutUserSimilarity.class);
        bind(Integer.class).annotatedWith(Names.named("numResults")).toInstance(15);

        // Step 5: Inject the recommender algorithm and its corresponding query
        bind(RecommenderAlgorithm.class).to(RecommenderMahoutCFUser.class);
        bind(Query.class).to(MahoutCFUserQuery.class);

    }

}
