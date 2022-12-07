package algorithms;

import java.util.List;

/**
 * A Java program for implementation of Insertion sort
 * Referenced from: <a href="https://www.geeksforgeeks.org/insertion-sort/">Geeks for Geeks</a>
 * And later modified to fit the requirements of the project
 * @author <a href="https://www.linkedin.com/in/gage-carpenter-07750a174/">Gage Carpenter</a>
 */
public class InsertionSort extends SortAlgorithm {

    /**
     * Takes a list of type T, where T is a comparable type, and returns the number of times the list is
     * compared to another element in the list. The list is sorted in ascending order using the insertion sort algorithm.
     *
     * @param list The list to be sorted
     * @return The number of times the while loop is executed.
     */
    @Override
    public <T extends Comparable<T>> long sort(List<T> list) {
        long complexityCount = 0;
        int n = list.size();
        for(int i = 1; i < n; i++){
            T key = list.get(i);
            int j = i - 1;
            while(j >= 0 && list.get(j).compareTo(key) > 0){
                complexityCount++;
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
        return complexityCount;
    }


    // Driver method
    public static void main(String args[])
    {
        driver(new InsertionSort());
    }
}