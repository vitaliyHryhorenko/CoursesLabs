package sorters;

import java.util.Arrays;


public class ArraySort extends AbstractSorter {
    @Override
    @AnnotationSorter(name = "Array Sort")
    public void sort(int[] array) {
        Arrays.sort(array);
    }
}
