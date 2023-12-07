package prArrayList.stringList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import prArrayList.exceptions.ErrStringListException;
import prArrayList.models.StringList;
import static org.junit.jupiter.api.Assertions.*;

import static prArrayList.stringList.ParametrizedMethods.*;

public class StringListTest {

    private StringList stringList;

    private void initialDefault() {
        for (var str : STRING_ARR_DEFAULT){
            stringList.add(str);
        }
    }

    @BeforeEach
    private void setUp() {
        stringList = new StringList();
    }

    // --------------------------- start test methods

    @ParameterizedTest
    @MethodSource(PATH_PARAMETIZED_METHOD + "parametersMethodSet")
    public void updateByMethodSet(ParamsForMethodSet param) {
        initialDefault();

        if (param.index() > stringList.getSize() || param.index() < 0) {
            assertThrows(ErrStringListException.class, () -> stringList.set(param.index(), param.str()));
        } else {
            var resSet = stringList.set(param.index(), param.str());

            assertEquals(param.str(), resSet);
        }
    }

    @ParameterizedTest
    @MethodSource(PATH_PARAMETIZED_METHOD + "errayForEqualsArray")
    public void verifyEqualsArray(ParamsForArray param) {
        initialDefault();

        assertEquals(param.isEqual(), stringList.equals(param.arrParams()));
    }


    @Test
    public void sortArrayString() {
        initialDefault();
        var res = stringList.sortArrayString(null);
        assertEquals(stringList.getSize(), res.length);
    }

    @Test
    public void getClone() {
        initialDefault();

        var resClone = stringList.getClone();

        assertEquals(stringList.getSize(), resClone.length);
    }

    @ParameterizedTest
    @MethodSource(PATH_PARAMETIZED_METHOD + "indexOf")
    public void removeByString(String str) {
        initialDefault();

        if (str.isBlank() || str.equals("100000")) {
            assertThrows(ErrStringListException.class, ()-> stringList.remove(str));
        } else {
            var sizeBeforRemove = stringList.getSize();
            var resRemove = stringList.remove(str);

            assertEquals(sizeBeforRemove - 1, stringList.getSize());
            assertEquals(resRemove, str);
            assertFalse(stringList.contains(str));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 100000, -1, 0})
    public void removeByIndex(int indexRemove) {
        initialDefault();

        if (indexRemove > stringList.getSize() || indexRemove < 0) {
            assertThrows(ErrStringListException.class, () -> stringList.remove(indexRemove));
        } else {
            var strBeforRemove = stringList.get(indexRemove);
            var sizeBeforRemove = stringList.getSize();

            var resRemove = stringList.remove(indexRemove);

            assertEquals(strBeforRemove, resRemove);
            var exists = stringList.contains(strBeforRemove);
            if (!exists) {
                assertEquals(-1, stringList.indexOf(strBeforRemove));
            }

            assertEquals(sizeBeforRemove -1, stringList.getSize());
        }
    }

    @ParameterizedTest
    @ValueSource(ints = { 2, 100000, 0, 3, 5, -1 })
    public void addArrString(int indexAdd) {
        initialDefault();

        var lastIndex = stringList.getSize() - 1;

        if (indexAdd > lastIndex || indexAdd < 0) {
            assertThrows(ErrStringListException.class, () -> stringList.add(indexAdd, STRING_ARR_ADD));
        } else {
            var strDataForIndexAdd = stringList.get(indexAdd);
            var sizeBeforAdd = stringList.getSize();

            var resAdd = stringList.add(indexAdd, STRING_ARR_ADD);
            var newSize = stringList.getSize();

            assertEquals(resAdd, newSize - sizeBeforAdd);

            assertEquals(strDataForIndexAdd, stringList.get(indexAdd + STRING_ARR_ADD.length));
            assertEquals(STRING_ARR_ADD[0], stringList.get(indexAdd));
            assertEquals(STRING_ARR_ADD[1], stringList.get(indexAdd + 1));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -10, 2, 3, 5, 100000 })
    public void offsetRight(int indexStart) {
        initialDefault();
        var startSize = stringList.getSize() - 1;

        if (indexStart > startSize) {
            assertThrows(ErrStringListException.class, () -> stringList.offsetRight(2, indexStart));
        } else {
            stringList.offsetRight(2, startSize-1);
        }
    }

    @Test
    public void offsetRight() {
        initialDefault();

        var size = stringList.getSize();
        stringList.offsetRight(3);

        assertEquals(size+1, stringList.getSize());
        assertEquals("empty", stringList.get(3));

    }

    @Test
    public void offsetLeft() {
        initialDefault();
        var size = stringList.getSize();

        stringList.offsetLeft(3);
        assertEquals(size-1, stringList.getSize());
    }

    @Test
    public void toArray() {
        initialDefault();

        var sizeStringList = stringList.getSize();
        var listRes = stringList.toArray();

        assertEquals(sizeStringList, listRes.length);
    }

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

        if (str.equals("100000")) {
            assertEquals(false, stringList.contains(str));
        } else if (str.isBlank()) {
            assertThrows(ErrStringListException.class, ()-> stringList.contains(str));
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

        if (str.equals("100000")) {
            assertEquals(-1, stringList.indexOf(str));
        } else if (str.isBlank()) {
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
