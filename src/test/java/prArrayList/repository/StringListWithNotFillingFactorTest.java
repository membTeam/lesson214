package prArrayList.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import prArrayList.exceptions.ErrOverflowException;
import prArrayList.models.StringList;

public class StringListWithNotFillingFactorTest {

    private StringList stringList;

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

        assertThrows(ErrOverflowException.class, () -> stringList.append("String " + 1000));
    }

    @Test
    public void reconfigurationForNewCapacity() {
        var capacity = stringList.getCapacity() + 10;
        stringList.reconfigurationForNewCapacity(10);

        assertEquals(capacity, stringList.getCapacity());
    }

}
