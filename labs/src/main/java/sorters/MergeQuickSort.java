package sorters;

public class MergeQuickSort extends AbstractSorter {
    @Override
    public void sort(int[] array) {
        AbstractSorter abstractSorter = new QuickSort();
        new MergeSort().divArray(array, abstractSorter);
    }
}
