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

import es.ucm.fdi.gaia.recolibry.api.Query;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MahoutCFUserQuery implements Query {

    private Long userId;

    @Override
    public void initialize() {
        userId = new Long(0);
    }

    @Override
    public List<String> getAttributesNames() {
        Field[] fields = this.getClass().getDeclaredFields();

        List<String> result = new ArrayList<>();
        for(Field f: fields){
            result.add(f.getName() + "(" + f.getType().getSimpleName() + ")");
        }

        return result;
    }

    @Override
    public void setAttributeValue(String attributeName, Object value) {
        try {
            Field field = this.getClass().getDeclaredField(attributeName);
            field.set(this, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object getAttributeValue(String attribute) {
        try {
            Field field = this.getClass().getDeclaredField(attribute);
            return field.get(this);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void setUserId(Object userId) {
        this.userId = (Long) userId;
    }

    public long getUserId() {
        return userId;
    }
}
