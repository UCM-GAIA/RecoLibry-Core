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
package es.ucm.fdi.gaia.recolibry.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 */
public class BeansFactory {

    private Class<?> clazz;

    public BeansFactory(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Object getBean() {
        return null;
    }

    public Object getBeanWithParameters(List<Object> parameters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class[] classesArray = new Class[parameters.size()];
        for(int i = 0; i < classesArray.length; i++)
            classesArray[i] = parameters.get(i).getClass();

        Constructor constructor = clazz.getConstructor(classesArray);
        Object beanObject = constructor.newInstance(parameters.toArray());
        return beanObject;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
