package algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * A Java program for implementation of Merge sort
 * Referenced from: <a href="https://www.geeksforgeeks.org/merge-sort/">Geeks for Geeks</a>
 * And later modified to fit the requirements of the project
 * @author <a href="https://www.linkedin.com/in/gage-carpenter-07750a174/">Gage Carpenter</a>
 */
public class MergeSort extends SortAlgorithm {

    private long complexityCount = 0;

    /**
     * Sorts the list using merge sort.
     *
     * @param list The list to be sorted.
     * @return The number of comparisons made.
     */
    @Override
    public <T extends Comparable<T>> long sort(List<T> list) {
        return mergeSort(list);
    }

    /**
     * Analyzes the list and splits it into smaller lists until the list is of size 1.
     * Then it merges the lists back together in ascending order.
     *
     * @param list The list to be sorted.
     * @return The complexity count is being returned.
     */
    private <T extends Comparable<T>> long mergeSort(List<T> list) {
        //If list is empty; no need to do anything
        if (list.size() <= 1) {
            return complexityCount;
        }

        //Split the array in half in two parts
        List<T> first = new ArrayList<>(list.subList(0, list.size() / 2));
        List<T> second = new ArrayList<>(list.subList(first.size(), list.size()));

        complexityCount++;
        //Sort each half recursively
        mergeSort(first);
        mergeSort(second);
        //Merge both halves together, overwriting to original array
        complexityCount++;
        merge(first, second, list);
        return complexityCount;
    }

    /**
     * Merges the two lists together in ascending order.
     *     * @param first The first list to merge
     * @param second The second half of the array to be merged.
     * @param result The list that will contain the merged elements.
     */
    private <T extends Comparable<T>> void merge(List<T> first, List<T> second, List<T> result) {
        //Index Position in first array - starting with first element
        int iFirst = 0;

        //Index Position in second array - starting with first element
        int iSecond = 0;

        //Index Position in merged array - starting with first position
        int iMerged = 0;

        //Compare elements at iFirst and iSecond,
        //and move smaller element at iMerged
        while (iMerged < result.size()) {
            boolean pickFirst;
            if (iFirst >= first.size()) {
                pickFirst = false;
            } else {
                pickFirst = iSecond >= second.size() || first.get(iFirst).compareTo(second.get(iSecond)) <= 0;

            }
            if (pickFirst) {
                result.set(iMerged, first.get(iFirst));
                iFirst++;
            } else {
                result.set(iMerged, second.get(iSecond));
                iSecond++;
            }
            iMerged++;
        }
    }

    // Driver method
    public static void main(String args[])
    {
        driver(new MergeSort());
    }
}