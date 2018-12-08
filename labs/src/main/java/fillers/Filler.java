package fillers;

import java.util.Random;

public class Filler {

    static Random random = new Random();

    @AnnotationFiller(name="Array Random Filler")
    public static void arrayRandomFiller(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(array.length) + 1;
        }
    }

    @AnnotationFiller(name="Sorted Filler")
    public static void sortedFiller(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
    }

    @AnnotationFiller(name="Back Sorted Filler")
    public static void backSortedFiller(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array.length - i;
        }
    }

    @AnnotationFiller(name="Sorted Filler X")
    public static void sortedFillerX(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            array[i] = i + 1;
        }
        array[array.length - 1] = random.nextInt(10) + 1;
    }

}
