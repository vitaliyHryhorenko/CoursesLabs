package fillers;

import java.util.Random;

public class Filler {

    static Random random = new Random();

    @AnnotationFiller
    public static void arrayRandomFiller(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(array.length) + 1;
        }
    }

    @AnnotationFiller
    public static void sortedFiller(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
    }

    @AnnotationFiller
    public static void backSortedFiller(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array.length - i;
        }
    }

    @AnnotationFiller
    public static void sortedFillerX(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            array[i] = i + 1;
        }
        array[array.length - 1] = random.nextInt(10) + 1;
    }

}
