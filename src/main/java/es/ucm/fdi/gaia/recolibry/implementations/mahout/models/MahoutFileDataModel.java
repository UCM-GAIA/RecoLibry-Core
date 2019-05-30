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

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;

import java.io.File;
import java.io.IOException;

/**
 * Class to implement a DataModel based on a CSV file.
 *
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 */
public class MahoutFileDataModel extends FileDataModel {

    /**
     * Method to build a new {@link MahoutFileDataModel} instance.
     * @param dataFile file that contains all data.
     * @param delimiterRegex delimiter that separates each value in the file.
     * @throws IOException
     */
    public MahoutFileDataModel(File dataFile, String delimiterRegex) throws IOException {
        super(dataFile, delimiterRegex);
    }

}
