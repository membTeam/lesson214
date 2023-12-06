package prArrayList.StringList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import prArrayList.exceptions.ErrStringListException;
import prArrayList.models.StringList;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StringListTest {

    private final String PATH_PARAMETIZED_METHOD = "prArrayList.StringList.ParametrizedMethods#";

    private StringList stringList;

    private void initialDefault() {
        for (var str : Stream.of("103", "102", "103", "100", "104", "105").collect(Collectors.toList())) {
            stringList.add(str);
        }
    }

    @BeforeEach
    private void setUp() {
        stringList = new StringList();
    }

    // --------------------------- start test method

    @Test
    public void clear() {

        initialDefault();
        stringList.clear();

        assertEquals(0, stringList.getSize());
    }

    @Test
    public void isEmptyWithExistsData() {
        initialDefault();

        assertEquals(false, stringList.isEmpty());
    }

    @Test
    public void isEmptyWithNoData() {
        assertEquals(true, stringList.isEmpty());
    }

    @Test
    public void sizeWithExistsData() {
        initialDefault();

        assertEquals(6, stringList.getSize());
    }

    @Test
    public void sizeWithNoData() {
        assertEquals(0, stringList.getSize());
    }

    @ParameterizedTest
    @MethodSource(PATH_PARAMETIZED_METHOD + "getFindByIndex")
    public void getFindByIndex(Integer index) {
        initialDefault();

        if (index < 0 || index == 100000) {
            assertThrows(ErrStringListException.class, () -> stringList.get(index));
        } else {
            assertEquals("103", stringList.get(index));
        }
    }

    @ParameterizedTest
    @MethodSource(PATH_PARAMETIZED_METHOD + "indexOf")
    public void contains(String str) {
        initialDefault();

        if (str.equals("100000") || str.isBlank()) {
            assertThrows(ErrStringListException.class, () -> stringList.indexOf(str));
        } else {
            assertEquals(true, stringList.contains(str));
        }
    }

    @ParameterizedTest
    @MethodSource(PATH_PARAMETIZED_METHOD + "lastIndexOf")
    public void lastIndexOf(String str) {
        initialDefault();

        if (str.equals("100000") || str.isBlank() ) {
            assertThrows(ErrStringListException.class, () -> stringList.lastIndexOf(str));
        } else {
            var index = stringList.lastIndexOf(str);
            assertEquals(2, index);
        }
    }

    @ParameterizedTest
    @MethodSource(PATH_PARAMETIZED_METHOD+"indexOf")
    public void indexOf(String str) {
        initialDefault();

        if (str.equals("100000") || str.isBlank()) {
            assertThrows(ErrStringListException.class, () -> stringList.indexOf(str));
        } else {
            var index = stringList.indexOf(str);
            assertEquals(3, index);
        }
    }

    @ParameterizedTest
    @NullSource
    @MethodSource(PATH_PARAMETIZED_METHOD +"appendTest")
    public void appendTest(String string) {
        assertThrows(ErrStringListException.class, () -> stringList.append(string));
    }

    @Test
    public void appendTestExt() {
        initialDefault();

        var strAdd = "900";
        var index = 4;
        var sizeBeforAdd = stringList.getSize();

        stringList.add(index, strAdd);

        var sizeAfterAdd = stringList.getSize();

        assertTrue(sizeBeforAdd>0);
        assertEquals(sizeBeforAdd + 1, sizeAfterAdd);
        assertEquals(strAdd, stringList.get(index));
    }
}
