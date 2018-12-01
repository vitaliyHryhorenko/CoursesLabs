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
import java.lang.reflect.Modifier;
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

    //get sub types AbstractSorter class
    public List<Class> getSubTypes() {
        List<Class> classList = new ArrayList<>();

        Reflections reflections = new Reflections("sorters");
        Set<Class<? extends AbstractSorter>> subTypes = reflections.getSubTypesOf(AbstractSorter.class);

        for (Class c:subTypes) {
            classList.add(c);
        }
        return classList;
    }

    //get all method "sort" in sub types AbstractSorter class
    public void getHeirClass(int[] array) {
        List<Method> sorterMethodList = new ArrayList<>();

        List<Class> subTypes = getSubTypes();
        for (Class c:subTypes) {
            Class clazz = null;
            try {
                clazz = Class.forName(c.getName());
                if(!(Modifier.isAbstract(c.getModifiers()))) {
//                    sorterMethodList.add(clazz.getMethod("sort", int[].class));
                    Method method = clazz.getMethod("sort", int[].class);

                    try {
                        Object obj = clazz.newInstance();
                        method.invoke(obj, array);

                    } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    public void doReflection(int[] array) {
        List<Method> fillerMethodList = getAnnotation();
//        List<Method> sorterMethodList = getHeirClass(array);

//        List<Class> subTypes = getSubTypes();

//        Class clazz = AbstractSorter.class;
//        Set<Class<? extends AbstractSorter>> subTypes =

        for (Method m1:fillerMethodList) {
            try {
                m1.invoke(new Filler(), array);
                getHeirClass(array);
                for (int i:array) {
                    System.out.print(i + " ");
                }
                System.out.println();
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }


    }
}
