package sorters;

public class MergeQuickSort extends AbstractSorter {
    @Override
    @AnnotationSorter(name = "Merge Quick Sort")
    public void sort(int[] array) {
        AbstractSorter abstractSorter = new QuickSort();
        MergeSort mergeSort = new MergeSort();
        mergeSort.divAndMerge(array, abstractSorter);

    }
}
