package sorters;

public class MergeReverseBubbleSort extends AbstractSorter {
    @Override
    @AnnotationSorter(name = "Merge Reverse Bubble Sort")
    public void sort(int[] array) {
        AbstractSorter abstractSorter = new ReverseBubbleSort();
        MergeSort mergeSort = new MergeSort();
        mergeSort.divAndMerge(array, abstractSorter);
    }
}
