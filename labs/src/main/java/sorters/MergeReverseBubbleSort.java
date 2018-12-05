package sorters;

public class MergeReverseBubbleSort extends AbstractSorter {
    @Override
    public void sort(int[] array) {
        AbstractSorter abstractSorter = new ReverseBubbleSort();
        MergeSort mergeSort = new MergeSort();
        mergeSort.divArray(array, abstractSorter);
    }
}
