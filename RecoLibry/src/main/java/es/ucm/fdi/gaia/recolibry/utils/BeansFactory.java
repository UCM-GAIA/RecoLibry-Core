package es.ucm.fdi.gaia.recolibry.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

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

}
