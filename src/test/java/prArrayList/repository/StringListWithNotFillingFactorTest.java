package prArrayList.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import prArrayList.exceptions.ErrOverflowException;
import prArrayList.models.StringList;

import java.util.Random;

public class StringListWithNotFillingFactorTest {

    private StringList stringList;

    private void initDefault() {
        stringList.append("100");
        stringList.append("101");
        stringList.append("23");
        stringList.append("101");
        stringList.append("1040");
    }

    @BeforeEach
    private void setUp() {
        stringList = new StringList();
    }

    @Test
    public void appendOnlyOneString() {
        stringList.append("String 1");
        var size = stringList.getSize();

        assertEquals(1, size);
    }

    @Test
    public void appendAnyString() {

        for (int number = 1, index = 0; index < stringList.getCapacity(); index++, number++) {
            stringList.append("String " + number);
        }

        assertEquals(20, stringList.getSize());
    }

    @Test
    public void appendStringMoreCapacity() {

        for (int index = 0; index < stringList.getCapacity(); index++) {
            stringList.append("String " + (index + 100));
        }

        assertThrows(ErrOverflowException.class, () -> stringList.append("String"));
    }

    @Test
    public void loadInitialRandomData() {
        stringList.loadInitialRandomData(5);

        int size = stringList.getSize();
        assertEquals(null, stringList.get(size) );

        assertNotNull(stringList.get(size-1));
    }

    @Test
    public void loadInitialRandomDataWithIndexMoreCapacity() {
        assertThrows(ErrOverflowException.class, () -> stringList.loadInitialRandomData(100));
    }

    @Test
    public void sortString() {
        int lastIndexInsert = 10;
        int minRand = 100;
        int diff = 200 - minRand;
        Random random = new Random();

        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int index = 0; index < lastIndexInsert; index++) {
            int numRund = random.nextInt(diff + 1) + minRand;
            if (max < numRund) {
                max = numRund;
            }
            if (min > numRund) {
                min = numRund;
            }

            stringList.append("string " + numRund);
        }

        var strMinValue = "string " + min;
        var strMaxValue = "string " + max;
        var sortedStringList = stringList.sortArrayString(stringList.toArray());

        var strMinValueFromSorted = sortedStringList[0];
        var strMaxValueFromSorted = sortedStringList[sortedStringList.length -1];

        assertEquals(strMinValue, strMinValueFromSorted);
        assertEquals(strMaxValue, strMaxValueFromSorted);
    }

    @Test
    public void sortStringWithNoParameter() {
        stringList.append("100");
        stringList.append("99");
        stringList.append("101");
        stringList.append("255");

        var resSortedString = stringList.sortArrayString();

        assertEquals("100", resSortedString[0]);
        assertEquals("99", resSortedString[resSortedString.length - 1]);
    }

    @Test
    public void equals() {
        var arrString = new String[4];
        arrString[0] = "100";
        arrString[1] = "101";
        arrString[2] = "155";
        arrString[3] = "230";

        var arrOther = stringList.getClone(arrString);

        assertTrue(stringList.equals(arrString, arrOther));
    }

    @Test
    public void notEquals() {
        var arrString = new String[4];
        arrString[0] = "100";
        arrString[1] = "101";
        arrString[2] = "155";
        arrString[3] = "230";

        var arrOther = stringList.getClone(arrString);
        arrOther[3] = "231";

        assertFalse(stringList.equals(arrString, arrOther));
    }

    @Test
    public void equalsFromStingList() {
        stringList.append("100");
        stringList.append("101");
        stringList.append("155");
        stringList.append("230");

        var arrOther = stringList.getClone(stringList.toArray());

        assertTrue(stringList.equals(arrOther));
    }

    @Test
    public void set() {
        stringList.loadInitialRandomData(5);

        var index = stringList.getSize() -1;
        var strSet = "New data";
        stringList.set(index, strSet);

        assertEquals(strSet, stringList.get(index));
    }

    @Test
    public void contains() {
        stringList.append("100");
        stringList.append("101");
        stringList.append("23");

        assertTrue(stringList.contains("23"));
    }

    @Test
    public void lastIndex() {
        initDefault();

        assertEquals(3, stringList.lastIndex("101"));
    }

    @Test
    public void offsetLeft() {
        initDefault();

        stringList.offsetLeft(1);
        assertEquals(4, stringList.getSize());
    }

    @Test
    public void offsetRight() {
        initDefault();

        stringList.offsetRight(1);
        assertEquals(6, stringList.getSize());
        assertEquals("empty", stringList.get(1));
    }

    @Test
    public void remove() {
        stringList.append("100");
        stringList.append("101");
        stringList.append("23");
        stringList.append("101");
        stringList.append("1040");

        stringList.remove(1);

        assertEquals(4, stringList.getSize());
        assertEquals("23", stringList.get(1));

    }

    @Test
    public void addForIndex() {
        initDefault();

        var strInsert = "new data";
        stringList.add(1, strInsert);

        assertEquals(6, stringList.getSize());
        assertEquals(strInsert, stringList.get(1));
    }

    @Test
    public void add() {
        initDefault();

        var strAdd = "any data";
        var sizeBefor = stringList.getSize();

        stringList.add(strAdd);

        var resultAdd = stringList.get(stringList.getSize() - 1);

        var sizeAfter = stringList.getSize();

        assertEquals(sizeAfter, sizeBefor + 1);
        assertEquals(strAdd, stringList.get(sizeAfter -1));
    }

}
