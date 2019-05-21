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
package es.ucm.fdi.gaia.recolibry.implementations.jcolibri;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRQuery;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;
import es.ucm.fdi.gaia.recolibry.api.Query;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that defines the query of recommender systems to
 * use in CBR Systems of jColibri.
 *
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 */
public class QueryJColibri extends CBRQuery implements Query {

    private Class<?> beanClassName;

    /**
     * Set the class that defines the bean object used as query.
     * @param beanClass name of the package and class of the bean class.
     */
    @Inject
    public QueryJColibri(@Named("BeanClass") String beanClass) {
        try {
            this.beanClassName = Class.forName(beanClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize() {
        try {

            Object beanObject = beanClassName.newInstance();
            this.setDescription((CaseComponent) beanObject);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getAttributesNames() {
        Field[] fields = beanClassName.getDeclaredFields();

        List<String> result;
        result = new ArrayList<>();
        for(Field f: fields){
            result.add(f.getName() + "(" + f.getType().getSimpleName() + ")");
        }

        return result;
    }

    @Override
    public void setAttributeValue(String attributeName, Object value) {
        try {
            Field field = beanClassName.getDeclaredField(attributeName);
            field.set(this.getDescription(), value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getAttributeValue(String attribute) {

        try {
            Field field = beanClassName.getDeclaredField(attribute);
            return field.get(this.getDescription());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
