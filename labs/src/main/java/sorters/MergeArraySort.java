package sorters;

public class MergeArraySort extends AbstractSorter {
    @Override
    public void sort(int[] array) {
        AbstractSorter abstractSorter = new ArraySort();
        MergeSort mergeSort = new MergeSort();
        mergeSort.divAndMerge(array, abstractSorter);
    }
}
