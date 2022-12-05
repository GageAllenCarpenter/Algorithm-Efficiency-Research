package generator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DegreeSortedTest {

    @Test
    void values() {
        for (DegreeSorted degreeSorted : DegreeSorted.values()) {
            assertNotNull(degreeSorted);
        }
    }
}