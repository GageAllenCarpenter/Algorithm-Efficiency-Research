package generator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringGeneratorTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void generateStringList() {
        StringGenerator stringGenerator = new StringGenerator();
        assertEquals(10, stringGenerator.generateStringList(10,10).size());
    }

    @Test
    void readwriteToFile() {
        StringGenerator stringGenerator = new StringGenerator();
        List<String> stringList = stringGenerator.generateStringList(10,10);
        stringGenerator.writeToFile(stringList, "src/main/resources/Ten.json");
        List<String> stringList1 = stringGenerator.readFromFile("src/main/resources/Ten.json");
        assertEquals(stringList, stringList1);
    }

    @Test
    void presort() {
        StringGenerator stringGenerator = new StringGenerator();
        List<String> list = stringGenerator.generateStringList(10,10);
        stringGenerator.writeToFile(list, "src/main/resources/Ten.json");
        assertEquals(list, stringGenerator.presort("src/main/resources/Ten.json", DegreeSorted.UNSORTED));
        stringGenerator.writeToFile(list, "src/main/resources/Ten.json");
        Collections.sort(list);
        assertEquals(list,stringGenerator.presort("src/main/resources/Ten.json", DegreeSorted.SORTED));
    }
}