package sorters;

public class MergeForwardBubbleSort extends AbstractSorter {
    @Override
    @AnnotationSorter(name = "Merge Forward Bubble Sort")
    public void sort(int[] array) {
        AbstractSorter abstractSorter = new ForwardBubbleSort();
        MergeSort mergeSort = new MergeSort();
        mergeSort.divAndMerge(array, abstractSorter);
    }
}
