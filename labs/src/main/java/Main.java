import reflection.Reflection;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[10];

        Reflection reflection = new Reflection();
        reflection.print(array);
    }
}
