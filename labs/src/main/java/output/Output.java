package output;

import reflection.Reflection;

import java.lang.reflect.Method;
import java.util.List;

public class Output {

    public void doPrint(int[] array) {
        for (int i:array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public void print(int[] array) {
        Reflection reflection = new Reflection();

        List<Class> classList = reflection.getSubTypes();
        List<Method> fillerMethodList = reflection.getAnnotation();


        for (Method method:fillerMethodList) {
            for (Class c : classList) {
                reflection.doFiller(method, array);

                doPrint(array);

                reflection.doSort(c, array);

                doPrint(array);

                System.out.println();
            }
        }


    }
}
