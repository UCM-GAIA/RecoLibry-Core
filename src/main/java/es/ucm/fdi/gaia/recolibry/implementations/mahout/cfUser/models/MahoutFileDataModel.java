package es.ucm.fdi.gaia.recolibry.implementations.mahout.cfUser.models;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;

import java.io.File;
import java.io.IOException;

public class MahoutFileDataModel extends FileDataModel {

    @Inject
    public MahoutFileDataModel(File dataFile, @Named("delimiter") String delimiterRegex) throws IOException {
        super(dataFile, delimiterRegex);
    }

}
