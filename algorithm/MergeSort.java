package algorithm;

import java.util.ArrayList;
import java.util.Comparator;

public class MergeSort {

    private static int exchangeOperations;
    private static int comparisonOperations;

    private static <B> void merge(ArrayList<B> leftArray, ArrayList<B> rightArray, ArrayList<B> array, Comparator<B> comparator) {

        int i = 0, j = 0;
        while (i + j < array.size()) {
            if (j == rightArray.size() || (i < leftArray.size() && comparator.compare(leftArray.get(i), rightArray.get(j)) > 0)) {
                array.set(i + j, leftArray.get(i++));
                exchangeOperations++;
                comparisonOperations++;
            } else {
                array.set(i + j, rightArray.get(j++));
                comparisonOperations++;
            }
        }
    }

    private static <B> void merge(ArrayList<B> array, Comparator<B> comparator) {
        int n = array.size();
        if (n < 2) {
            return;
        }

        int mid = n / 2;
        ArrayList<B> leftArray = new ArrayList<>(array.subList(0, mid));
        ArrayList<B> rightArray = new ArrayList<>(array.subList(mid, n));

        merge(leftArray, comparator);
        merge(rightArray, comparator);

        merge(leftArray, rightArray, array, comparator);
    }

    public static <B> void mergeSort(ArrayList<B> array, Comparator<B> comparator) {
    	exchangeOperations = 0;
        comparisonOperations = 0;
        long startTime = System.nanoTime();
        merge(array, comparator);
        long workTime = System.nanoTime() - startTime;

        System.out.println("!!!Merge Sort!!!");
    System.out.println("Number of exchanges:" + exchangeOperations);
	System.out.println("Number of comparison operations:" + comparisonOperations);
	System.out.println("Work time:" + workTime + " nanoseconds");
	System.out.println("Result:");
    }
}
