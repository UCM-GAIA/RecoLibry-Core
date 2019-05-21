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
package es.ucm.fdi.gaia.recolibry.api;

import java.util.List;

/**
 * Interface to define methods necessary in a {@link Query} object.
 *
 * @author Jose L. Jorro-Aragoneses
 * @version 2.0
 */
public interface Query {

    /**
     * Method to build an empty bean object.
     */
    void initialize();

    /**
     * Method to obtain the name of all attributes contained in the bean object.
     * @return list of names.
     */
    List<String> getAttributesNames();

    /**
     * Method to add a value to an attribute of the query bean.
     * @param attributeName name of the attribute to set the value.
     * @param value value to set in the bean's attribute.
     */
    void setAttributeValue(String attributeName, Object value);

    /**
     * Method to get the value asigned in an bean's attribute.
     * @param attribute name of the attribute to get the value.
     * @return value that contains the attribute.
     */
    Object getAttributeValue(String attribute);
}
