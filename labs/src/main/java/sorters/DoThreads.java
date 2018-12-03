package sorters;

public class DoThreads implements Runnable {

    private int[] array;
    private AbstractSorter abstractSorter;

    public DoThreads(int[] array, AbstractSorter abstractSorter) {
        this.array = array;
        this.abstractSorter = abstractSorter;
    }

    @Override
    public void run() {
        abstractSorter.sort(array);
    }
}
