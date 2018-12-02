package sorters;

import java.util.ArrayList;
import java.util.List;

public class MergeSort{

    public List<int[]> divArray(int[] array) {
        List<int[]> arrayList = new ArrayList<>();

        int[] array1 = new int[array.length/2];
        int[] array2 = new int[array.length-array1.length];

        for (int i = 0; i < array1.length; i++) {
            array1[i] = array[i];
        }
        for (int i = 0, j = array1.length; i < array2.length; i++, j++) {
            array2[i] = array[j];
        }

        arrayList.add(array1);
        arrayList.add(array2);

        return arrayList;

    }

    public void mergeArray(int[] array, int[] array1, int[] array2) {
        for (int i = 0, k = 0, j = 0; i < array.length; i++) {

            if (k < array2.length && j < array1.length) {
                if (array1[j] < array2[k]) {
                    array[i] = array1[j];
                    j++;
                } else {
                    array[i] = array2[k];
                    k++;
                }
            } else if (j == array1.length){
                array[i] = array2[k];
                k++;
            } else {
                array[i] = array1[j];
                j++;
            }
        }
    }
}
