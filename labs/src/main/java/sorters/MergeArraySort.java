package sorters;

import java.util.List;

public class MergeArraySort extends ArraySort {
    @Override
    public void sort(int[] array) {
        MergeSort mergeSort = new MergeSort();

        List<int[]> arrayList = mergeSort.divArray(array);

        int[] array1 = arrayList.get(0);
        int[] array2 = arrayList.get(1);

        super.sort(array1);
        super.sort(array2);

        mergeSort.mergeArray(array, array1, array2);
    }
}
