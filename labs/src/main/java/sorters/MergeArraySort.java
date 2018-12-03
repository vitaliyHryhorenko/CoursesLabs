package sorters;

public class MergeArraySort extends AbstractSorter {
    @Override
    public void sort(int[] array) {
        AbstractSorter abstractSorter = new ArraySort();
        new MergeSort().divArray(array, abstractSorter);
    }
}
