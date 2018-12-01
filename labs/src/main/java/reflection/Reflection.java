package reflection;

import analyzer.Analyzer;
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

    public  void doFiller(Method method, int[] array) {
        try {
            method.invoke(new Filler(), array);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    //get sub types AbstractSorter class
    public List<Class> getSubTypes() {
        List<Class> classList = new ArrayList<>();

        Reflections reflections = new Reflections("sorters");
        Set<Class<? extends AbstractSorter>> subTypes = reflections.getSubTypesOf(AbstractSorter.class);

        for (Class c:subTypes) {
            Class clazz = null;
            try {
                clazz = Class.forName(c.getName());
                if(!(Modifier.isAbstract(c.getModifiers()))) {
                    classList.add(c);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return classList;
    }

    //do sorting and analysis
    public void doSort(Class c, int[] array) {
        Analyzer analyzer = new Analyzer();
        Class clazz = null;
        try {
            clazz = Class.forName(c.getName());
            try {
                analyzer.analyzeSort(array, (AbstractSorter) clazz.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException | SecurityException e) {
            e.printStackTrace();
        }
    }



}
