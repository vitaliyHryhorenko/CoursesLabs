package sorters;

public class MergeBubbleSort extends AbstractSorter {
    @Override
    public void sort(int[] array) {
        AbstractSorter abstractSorter = new BubbleSort();
        MergeSort mergeSort = new MergeSort();
        mergeSort.divArray(array, abstractSorter);
    }
}
