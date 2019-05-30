package es.ucm.fdi.gaia.recolibry.implementations.mahout.models;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.mahout.cf.taste.model.DataModel;

import java.io.File;
import java.io.IOException;

public class DataModelFactory {

    private DataModel model;

    @Inject
    public DataModelFactory(@Named("source") String path, @Named("delimiter") String delimiterRegex) throws IOException {

        File f = new File(path);

        model = new MahoutFileDataModel(f, delimiterRegex);

    }

    public DataModel getModel() {
        return model;
    }
}
