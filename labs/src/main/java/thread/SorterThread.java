package thread;

import sorters.AbstractSorter;

public class SorterThread implements Runnable{
    private int[] array;
    private AbstractSorter abstractSorter;

    public SorterThread(int[] array, AbstractSorter abstractSorter) {
        this.array = array;
        this.abstractSorter = abstractSorter;
    }

    @Override
    public void run() {
        abstractSorter.sort(array);
    }
}
