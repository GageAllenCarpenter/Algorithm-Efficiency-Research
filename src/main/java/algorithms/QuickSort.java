package algorithms;

import java.util.List;

/**
 * A Java program for implementation of Quick sort
 * Referenced from: <a href="https://www.geeksforgeeks.org/quick-sort/">Geeks for Geeks</a>
 * And later modified to fit the requirements of the project
 * @author <a href="https://www.linkedin.com/in/gage-carpenter-07750a174/">Gage Carpenter</a>
 */
public class QuickSort extends SortAlgorithm {

    long complexityCount = 0;

    /**
     * Sorts the list using the quick sort algorithm.
     *
     * @param a the list to be sorted
     * @return The complexity count of the quick sort algorithm.
     */
    @Override
    public <T extends Comparable<T>> long sort(List<T> a) {
        quick(a, 0, a.size() - 1);
        return complexityCount;
    }

    /**
     * Takes the last element as pivot, places the pivot element at its correct position in sorted array,
     * and places all smaller (smaller than pivot) to left of pivot and all greater elements to right of pivot
     *
     * @param a the list to be sorted
     * @param i the start index of the array
     * @param j the last index of the array
     */
    private <T extends Comparable<T>> void quick(List<T> a, int i, int j) {
        if (i < j) {
            int l = partition(a, i, j);
            quick(a, i, l);
            quick(a, l + 1, j);
        }
    }

    /**
     * Takes the last element as pivot, places the pivot element at its correct position in sorted array,
     *
     * @param a the list to be sorted
     * @param p the first index of the array
     * @param q the end of the array
     * @return The index of the pivot element.
     */
    private <T extends Comparable<T>> int partition(List<T> a, int p, int q) {
        T x = a.get(p);
        int m = (p + q) / 2;
        if (check(a, p, q, m) || check(a, q, p, m)) {
            x = a.get(m);
        }
        if (check(a, p, m, q) || check(a, m, p, q)) {
            x = a.get(q);
        }
        int i = p - 1;
        int j = q + 1;
        while (true) {
            do {
                i++;
            } while (!(i > q || a.get(i).compareTo(x) >= 0));
            do {
                j--;
            } while (!(j < p || a.get(j).compareTo(x) <= 0));
            if (i < j) {
                swap(a, i, j);
            } else {
                return j;
            }
        }
    }

    /**
     * Checks if the element at index a is less than or equal to the element at index c and if the element at index c is
     * less than or equal to the element at index b
     *
     * @param list The list to be sorted
     * @param a The index of the first element in the sublist
     * @param b the index of the first element in the second half of the array
     * @param c the middle element
     * @return The complexity of the algorithm.
     */
    private <T extends Comparable<T>> boolean check(List<T> list, int a, int b, int c) {
        complexityCount++; // There cannot be any other operations that is performed more frequently than this
        return list.get(a).compareTo(list.get(c)) <= 0 && list.get(c).compareTo(list.get(b)) <= 0;
    }

    /**
     * Swap the elements at indices i and j in the list a
     *
     * @param a the list to be sorted
     * @param i the index of the first element to be swapped
     * @param j the index of the first element to be compared
     */
    private <T extends Comparable<T>> void swap(List<T> a, int i, int j) {
        T x;
        x = a.get(i);
        a.set(i, a.get(j));
        a.set(j, x);
       
    }

    // Driver method
    public static void main(String args[])
    {
        driver(new QuickSort());
    }
}
