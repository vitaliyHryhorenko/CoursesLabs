package sorters;

public class MergeBubbleSort extends AbstractSorter {
    @Override
    public void sort(int[] array) {
        AbstractSorter abstractSorter = new BubbleSort();
        new MergeSort().divArray(array, abstractSorter);
    }
}
