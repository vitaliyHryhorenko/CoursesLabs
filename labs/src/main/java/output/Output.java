package output;

import reflection.Reflection;

import java.lang.reflect.Method;
import java.util.List;

public class Output {

    public void doPrint(String methodName, String className, int timeSort) {
        System.out.println(methodName + " + " + className + " = " + timeSort);
    }

    public void print(int[] array) {
        Reflection reflection = new Reflection();

        List<Class> classList = reflection.getSubTypes();
        List<Method> fillerMethodList = reflection.getAnnotation();

        for (Method method:fillerMethodList) {

            for (Class c : classList) {
                reflection.doFiller(method, array);
                doPrint(method.getName(), c.getSimpleName(), reflection.doSort(c, array));
            }
            System.out.println();
        }


    }
}
