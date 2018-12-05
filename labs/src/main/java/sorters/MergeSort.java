package sorters;

import thread.SorterThread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {

    public void divArray(int[] array, AbstractSorter abstractSorter) {

        List<int[]> arrayList = new ArrayList<>();

        int countProc = Runtime.getRuntime().availableProcessors();
        doDivArray(array, countProc, arrayList);

        List<Thread> threads = new ArrayList<>();

        for (int[] i : arrayList) {
            Thread thread = new Thread(new SorterThread(i, abstractSorter));
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (arrayList.size() != 1) {
            mergeArray(arrayList, arrayList.get(0), arrayList.get(1));
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = arrayList.get(0)[i];
        }

    }

    public void doDivArray(int[] array, int countProc, List<int[]> arrayList) {

        int first = 0;
        int last = array.length / countProc;

        for (int i = 0; i < countProc - 1; i++) {
            arrayList.add(Arrays.copyOfRange(array, first, last));

            first += array.length / countProc;
            last += array.length / countProc;
        }

        arrayList.add(Arrays.copyOfRange(array, first, array.length));
    }

    public void mergeArray(List<int[]> arrayList, int[] array1, int[] array2) {

        int[] array = new int[array1.length + array2.length];

        for (int i = 0, k = 0, j = 0; i < array.length; i++) {

            if (k < array2.length && j < array1.length) {
                if (array1[j] < array2[k]) {
                    array[i] = array1[j];
                    j++;
                } else {
                    array[i] = array2[k];
                    k++;
                }
            } else if (j == array1.length) {
                array[i] = array2[k];
                k++;
            } else {
                array[i] = array1[j];
                j++;
            }
        }
        arrayList.add(array);
        arrayList.remove(array1);
        arrayList.remove(array2);
    }
}