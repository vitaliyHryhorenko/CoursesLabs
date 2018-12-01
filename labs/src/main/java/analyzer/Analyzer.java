package analyzer;


import sorters.AbstractSorter;

public class Analyzer {

    public int analyzeSort(int[] array, AbstractSorter abstractSorter) {
        long before = System.nanoTime();
        abstractSorter.sort(array);
        long after = System.nanoTime();
        return (int)(after - before);
    }

}
