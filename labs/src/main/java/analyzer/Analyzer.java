package analyzer;


import sorters.AbstractSorter;

public class Analyzer {

    public int analyzeSort(int[] array, AbstractSorter abstractSorter) {
        long before = System.currentTimeMillis();
        abstractSorter.sort(array);
        long after = System.currentTimeMillis();
        return (int)(after - before);
    }

}
