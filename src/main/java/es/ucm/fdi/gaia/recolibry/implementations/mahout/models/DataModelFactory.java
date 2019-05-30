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
package es.ucm.fdi.gaia.recolibry.implementations.mahout.models;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.mahout.cf.taste.model.DataModel;

import java.io.File;
import java.io.IOException;

/**
 * Class to configure the {@link DataModel} for mahout's algorithms. In this
 * version, it creates {@link MahoutFileDataModel} objects.
 *
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 */
public class DataModelFactory {

    private DataModel model;

    /**
     * Factory constructor. It needs the path of a CSV file and the delimiter.
     * @param path path where is saved the CSV file.
     * @param delimiterRegex delimiter that separates each data in the CSV file.
     * @throws IOException
     */
    @Inject
    public DataModelFactory(@Named("source") String path, @Named("delimiter") String delimiterRegex) throws IOException {

        File f = new File(path);

        model = new MahoutFileDataModel(f, delimiterRegex);

    }

    /**
     * Method to get the model build.
     * @return
     */
    public DataModel getModel() {
        return model;
    }
}
