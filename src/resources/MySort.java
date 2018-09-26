/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Administrator
 */
public class MySort {

    /**
     * Effektivität grade = 1;
     * My own version of an array sortation, very slow 
     * @param array
     * @return sorted array
     */
    public static double[] meSortDoubleArray(double[] array) {
        double temp = 0;
        int y = array.length;
        for (int i = 0; i < array.length; i++) {
            for (int x = i + 1; x < array.length; x++) {
                if (array[i] > array[x]) {
                    temp = array[x];
                    array[x] = array[i];
                    array[i] = temp;
                }

            }

        }
        return array;
    }

    /**
     * Effektivität grade = 1;
     * My own version of an array sortation, very slow
     * @param array int []array
     * @return sorted int[]array
     */
    public static int[] meSortIntArray(int[] array) {
        int temp = 0;

        for (int i = 0; i < array.length; i++) {
            for (int x = i + 1; x < array.length; x++) {
                if (array[i] > array[x]) {
                    temp = array[x];
                    array[x] = array[i];
                    array[i] = temp;

                }

            }

        }
        return array;
    }

    public static Object[] meSortObjectArray(Object[] array) {
        Object temp;

        for (int i = 0; i < array.length; i++) {
            for (int x = i + 1; x < array.length; x++) {
                if (((Comparable) array[i]).compareTo(array[x]) > 0) {
                    temp = array[x];
                    array[x] = array[i];
                    array[i] = temp;

                }

            }

        }
        return array;
    }

    /**
     * My Sortation - sortating Objects, implementing Comparator
     * @param array
     * @param comp
     * @return
     */
    public static Object[] meSortObjectArrayComparator(Object[] array, Comparator comp) {
        Object temp;

        for (int i = 0; i < array.length; i++) {
            for (int x = i + 1; x < array.length; x++) {
                if (comp.compare(array[i], array[x]) > 0) {
                    temp = array[x];
                    array[x] = array[i];
                    array[i] = temp;

                }

            }

        }
        return array;
    }

    /**
     * Effektivität grade = 1;
     *Sorterar en ArrayList av typen Integer växande
     * @param list
     * @return
     */
    public static ArrayList<Integer> sortIntArrayList(ArrayList<Integer> list) {
        int x = list.size();
        int f = 0;
//        int[]array = new int[list.size()];
        ArrayList<Integer> newList = new ArrayList<Integer>();
        int minst = list.get(0);
        int minst2 = 0;
        for (int i = 0; i < x; i++) {
            for (int y = 0; y < x; y++) {
                if (list.get(y) < minst) {
                    minst2 = minst;
                    minst = list.get(y);

                }


            }
            for (int p = 0; p < x; p++) {
                if (list.get(p) == minst) {
                    newList.add(minst);
                    list.remove(p);
                    minst = Integer.MAX_VALUE;
                    x--;
                    i--;
                    break;
                }
            }

        }
        return newList;
    }

    /**
     * Swapar 2 object i en Object[]array
     * @param array
     * @param i
     * @param j
     */
    public static void swap2Objects(Object[] array, int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Object Array sortation with bubbleSort
     * @param array
     * @return
     */
    public static Object[] BubbleSortObject(Object[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                Comparable comp = (Comparable) array[j - 1];
                if (comp.compareTo(array[j]) > 0) {
                    swap2Objects(array, j, j - 1);
                }
            }
            System.out.println("");
        }

        return array;

    }

    /**
     * Effektivität grade = 3;
     * En ganska bra sorterings metod för mindre mäng elementer
     * @param array double[]array
     * @return
     */
    public static double[] insertionSortAvt(double[] array) {

        for (int i = 1; i < array.length; i++) {
            for (int j = i; (j > 0) && (array[j - 1] < array[j]); j--) {
                double temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;
            }
        }
        return array;
    }

    /**
     * Effektivität grade =3;
     * En ganska bra sorterings metod för mindre mäng elementer
     * @param array en int[]array
     * @return
     */
    public static int[] insertionSortAvt(int[] array) {

        for (int i = 1; i < array.length; i++) {
            for (int j = i; (j > 0) && (array[j - 1] < array[j]); j--) {
                int temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;
            }
        }
        return array;
    }

    /**
     * Effektivität grade = 3;
     * En ganska bra sorterings metod för mindre mäng elementer
     * @param array double[]array
     * @return
     */
    public static double[] insertionSortVäxande(double[] array) {

        for (int i = 1; i < array.length; i++) {
            for (int j = i; (j > 0) && (array[j - 1] > array[j]); j--) { // array[j-1]>array[j]) - det är
                double temp = array[j];                    // tecknet > som gör det växande eller
                array[j] = array[j - 1];                    // avtagande
                array[j - 1] = temp;
            }
        }
        return array;
    }

    /**
     * Sortering av Object array med insertionSort
     * @param array
     * @return
     */
    public static Object[] insertionSortVäxande(Object[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; (j > 0) && (((Comparable) array[j - 1]).compareTo(array[j]) > 0); j--) { // array[j-1]>array[j]) - det är
                swap2Objects(array, j, j - 1);                    // tecknet > som gör det växande eller
            }
        }
        return array;
    }

    /**
     * Object Array sortation med implimenterat Comparator
     * @param array
     * @param comp
     * @return
     */
    public static Object[] insertionSortVäxande(Object[] array, Comparator comp) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; (j > 0) && (comp.compare(array[j], array[j - 1]) < 0); j--) { // array[j-1]>array[j]) - det är
                swap2Objects(array, j, j - 1);                    // tecknet > som gör det växande eller
            }
        }
        return array;
    }

    /**
     * Effektivität grade = 5/5, Modern sortation method
     * @param array en int[]Array
     */
    public static int[] mergesort(int[] array) {
        int[] temp = new int[array.length];
        mergesort(array, 0, array.length, temp);
        temp = null;
        return array;
    }

    private static void mergesort(int[] array, int start, int n, int[] temp) {
        int n1, n2;
        if (n > 1) {
            n1 = n / 2;
            n2 = n - n1;
            mergesort(array, start, n1, temp);
            mergesort(array, start + n1, n2, temp);
            merge(array, start, n1, n2, temp);
        }
    }

    private static void merge(int[] array, int start, int n1, int n2, int[] temp) {
        int kopierade = 0, markör1 = 0, markör2 = n1, slut = n1 + n2;
        while ((markör1 < n1) && (markör2 < slut)) {
            if (array[start + markör1] < array[start + markör2]) {
                temp[kopierade] = array[start + markör1];
                markör1++;
            } else {
                temp[kopierade] = array[start + markör2];
                markör2++;
            }
            kopierade++;
        }
        while (markör1 < n1) {
            temp[kopierade] = array[start + markör1];
            markör1++;
            kopierade++;
        }
        while (markör2 < slut) {
            temp[kopierade] = array[start + markör2];
            markör2++;
            kopierade++;
        }
        for (int i = 0; i < n1 + n2; i++) {
            array[start + i] = temp[i];
        }
    }

    /**
     *
     * @param array
     * @return
     */
    public static double[] mergesort(double[] array) {
        double[] temp = new double[array.length];
        mergesort(array, 0, array.length, temp);
        temp = null;
        return array;
    }

    private static void mergesort(double[] array, int start, int n, double[] temp) {
        int n1, n2;
        if (n > 1) {
            n1 = n / 2;
            n2 = n - n1;
            mergesort(array, start, n1, temp);
            mergesort(array, start + n1, n2, temp);
            merge(array, start, n1, n2, temp);
        }
    }

    private static void merge(double[] array, int start, int n1, int n2, double[] temp) {
        int kopierade = 0, markör1 = 0, markör2 = n1, slut = n1 + n2;
        while ((markör1 < n1) && (markör2 < slut)) {
            if (array[start + markör1] < array[start + markör2]) {
                temp[kopierade] = array[start + markör1];
                markör1++;
            } else {
                temp[kopierade] = array[start + markör2];
                markör2++;
            }
            kopierade++;
        }
        while (markör1 < n1) {
            temp[kopierade] = array[start + markör1];
            markör1++;
            kopierade++;
        }
        while (markör2 < slut) {
            temp[kopierade] = array[start + markör2];
            markör2++;
            kopierade++;
        }
        for (int i = 0; i < n1 + n2; i++) {
            array[start + i] = temp[i];
        }
    }

    /**
     * Effiktivität 5/5
     * MergeSort med Object[]Array
     * @param array
     * @return
     */
    public static Object[] mergesort(Object[] array) {
        Object[] temp = new Object[array.length];
        mergesort(array, 0, array.length, temp);
        temp = null;
        return array;
    }

    /**
     *
     * @param array
     * @param start
     * @param n
     * @param temp
     */
    private static void mergesort(Object[] array, int start, int n, Object[] temp) {
        int n1, n2;
        if (n > 1) {
            n1 = n / 2;
            n2 = n - n1;
            mergesort(array, start, n1, temp);
            mergesort(array, start + n1, n2, temp);
            merge(array, start, n1, n2, temp);
        }
    }

    private static void merge(Object[] array, int start, int n1, int n2, Object[] temp) {
        int kopierade = 0, markör1 = 0, markör2 = n1, slut = n1 + n2;
        while ((markör1 < n1) && (markör2 < slut)) {
            Comparable comp = (Comparable) array[start + markör1];
            if (comp.compareTo(array[start + markör2]) < 0) {
                temp[kopierade] = array[start + markör1];
                markör1++;
            } else {
                temp[kopierade] = array[start + markör2];
                markör2++;
            }
            kopierade++;
        }
        while (markör1 < n1) {
            temp[kopierade] = array[start + markör1];
            markör1++;
            kopierade++;
        }
        while (markör2 < slut) {
            temp[kopierade] = array[start + markör2];
            markör2++;
            kopierade++;
        }
        for (int i = 0; i < n1 + n2; i++) {
            array[start + i] = temp[i];
        }
    }

    /**
     * MergeSort med implimenterad Comparator, sorterar växande
     * @param array
     * @return
     */
    public static Object[] mergesortComparator(Object[] array, Comparator c) {
        Object[] temp = new Object[array.length];
        mergesortComparator(array, 0, array.length, temp, c);
        temp = null;
        return array;
    }

    /**
     *
     * @param array
     * @param start
     * @param n
     * @param temp
     * @param c
     */
    private static void mergesortComparator(Object[] array, int start, int n, Object[] temp, Comparator c) {

        int n1, n2;
        if (n > 1) {
            n1 = n / 2;
            n2 = n - n1;
            mergesortComparator(array, start, n1, temp, c);
            mergesortComparator(array, start + n1, n2, temp, c);
            merge(array, start, n1, n2, temp, c);
        }
    }

    private static void merge(Object[] array, int start, int n1, int n2, Object[] temp, Comparator comp) {
        int kopierade = 0, markör1 = 0, markör2 = n1, slut = n1 + n2;
        while ((markör1 < n1) && (markör2 < slut)) {
            if (comp.compare(array[start + markör1], array[start + markör2]) < 0) {
                temp[kopierade] = array[start + markör1];
                markör1++;
            } else {
                temp[kopierade] = array[start + markör2];
                markör2++;
            }
            kopierade++;
        }
        while (markör1 < n1) {
            temp[kopierade] = array[start + markör1];
            markör1++;
            kopierade++;
        }
        while (markör2 < slut) {
            temp[kopierade] = array[start + markör2];
            markör2++;
            kopierade++;
        }
        for (int i = 0; i < n1 + n2; i++) {
            array[start + i] = temp[i];
        }
    }
}
