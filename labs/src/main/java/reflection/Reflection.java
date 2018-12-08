package reflection;

import analyzer.Analyzer;
import fillers.AnnotationFiller;
import fillers.Filler;
import org.reflections.Reflections;
import sorters.AbstractSorter;
import sorters.AnnotationSorter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
//import java.lang.reflect.Field;

public class Reflection {


    public List<Method> getMethods() {

        Filler filler = new Filler();
        Class aClass = filler.getClass();


        Method[] methods = aClass.getDeclaredMethods();
        List<Method> fillerMethodList = new ArrayList<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(AnnotationFiller.class)) {
                fillerMethodList.add(method);
            }
        }
        return fillerMethodList;
    }

    public List<String> getValueAnnotationFiller() {
        List<Method> methods = getMethods();
        List<String> annotationsValue = new ArrayList<>();

        for (Method method :methods) {
            annotationsValue.add(method.getAnnotation(AnnotationFiller.class).name());
        }
        return annotationsValue;
    }

    public void doFiller(Method method, int[] array) {
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

        for (Class c : subTypes) {
            if (!(Modifier.isAbstract(c.getModifiers()))) {
                classList.add(c);
            }
        }
        return classList;
    }

    public List<String> getValueAnnotationSorter() {
        List<Class> classList = getSubTypes();
        List<String> annotationsValue = new ArrayList<>();
        List<Method> methods = new ArrayList<>();

        for (Class c : classList) {
            try {
                annotationsValue.add(c.getMethod("sort", int[].class).getAnnotation(AnnotationSorter.class).name());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }


        return annotationsValue;
    }



    //do sorting and analysis
    public int doSort(Class c, int[] array) {
        Analyzer analyzer = new Analyzer();
        Class clazz = null;
        try {
            clazz = Class.forName(c.getName());
            try {
                return analyzer.analyzeSort(array, (AbstractSorter) clazz.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException | SecurityException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
