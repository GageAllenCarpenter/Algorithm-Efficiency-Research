package algorithms;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getName() {
        MergeSort mergeSort = new MergeSort();
        assertEquals("MergeSort", mergeSort.getName());
    }

    @Test
    void sort() {
        MergeSort mergeSort = new MergeSort();
        List<String> sorted = new ArrayList<>();
        List<String> actual = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1000; j++) {
                final String uniqueInstance = new String(String.valueOf(1000 + j));
                actual.add(uniqueInstance);
                sorted.add(uniqueInstance);
            }
        }
        Collections.shuffle(actual);
        Collections.sort(sorted);
        mergeSort.sort(actual);
        assertEquals(sorted, actual);
    }
}