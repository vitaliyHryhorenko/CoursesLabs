package sorters;

public class MergeReverseBubbleSort extends AbstractSorter {
    @Override
    public void sort(int[] array) {
        AbstractSorter abstractSorter = new ReverseBubbleSort();
        new MergeSort().divArray(array, abstractSorter);
    }
}
