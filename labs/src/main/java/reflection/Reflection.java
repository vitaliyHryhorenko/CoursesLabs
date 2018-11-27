package reflection;

import fillers.AnnotationFiller;
import fillers.Filler;
import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import sorters.AbstractSorter;
import sorters.ArraySort;
import sorters.BubbleSorter;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
//import java.lang.reflect.Field;

public class Reflection {
    public List<Method> getAnnotation() {

        Filler filler = new Filler();
        Class aClass = filler.getClass();


        Method[] methods = aClass.getDeclaredMethods();
        List<Method> fillerMethodList = new ArrayList<>();

        for (Method method:methods) {
            if(method.isAnnotationPresent(AnnotationFiller.class)) {
                fillerMethodList.add(method);
            }
        }
        return fillerMethodList;
    }

    public List<Method> getHeirClass(int[] array) {
        Reflections reflections = new Reflections("sorters");
        List<Method> sorterMethodList = new ArrayList<>();

        Set<Class<? extends AbstractSorter>> subTypes = reflections.getSubTypesOf(AbstractSorter.class);
        for (Class c:subTypes) {
            Class clazz = null;
            try {
                clazz = Class.forName(c.getName());

                if(reflections.getSubTypesOf(clazz).isEmpty()) {
                    sorterMethodList.add(clazz.getMethod("sort", int[].class));
//                    Method method = clazz.getMethod("sort", new Class[]{int[].class});
//                    try {
//
//                        method.invoke(c, array);
//                    } catch (IllegalAccessException | InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
                }
            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            }
        }
        return sorterMethodList;
    }

    public void print(int[] array) {
        List<Method> fillerMethodList = getAnnotation();
        List<Method> sorterMethodList = getHeirClass(array);

        Class clazz = AbstractSorter.class;

        for (Method m1:fillerMethodList) {
            for (Method m2:sorterMethodList) {
                try {
                    m1.invoke(new Filler(), array);
//                    m2.invoke(clazz, array);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        for (int i:array) {
            System.out.println(i);
        }
    }
}
