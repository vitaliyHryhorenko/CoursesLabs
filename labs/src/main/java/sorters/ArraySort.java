package sorters;

import java.util.Arrays;

public class ArraySort extends AbstractSorter {
    @Override
    public void sort(int[] array) {
        Arrays.sort(array);
    }
}
