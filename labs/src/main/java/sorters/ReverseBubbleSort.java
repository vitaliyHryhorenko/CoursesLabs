package sorters;

public class ReverseBubbleSort extends BubbleSorter{
    @Override
    @AnnotationSorter(name = "Reverse Bubble Sort")
    public void sort(int[] array) {
        int temp;
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = array.length - 1; j > 0; j--) {
                if (array[j - 1] > array[j]) {
                    temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
    }
}
