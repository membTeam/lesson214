package prArrayList.stringListExt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import prArrayList.exceptions.ErrStringListException;
import prArrayList.models.StringListExt;
import prArrayList.models.StringListService;

import static org.junit.jupiter.api.Assertions.*;
import static prArrayList.stringList.ParametrizedMethods.STRING_ARR_DEFAULT;

public class StringListExtTest {

    private StringListExt stringListExt;

    private void initialDefaultForTest() {
        stringListExt.add(STRING_ARR_DEFAULT);
    }

    @BeforeEach
    private void setUp() {
        stringListExt = new StringListExt();
    }

    // ------------------------------------

    @Test
    public void offsetRight(){
        stringListExt = new StringListExt(7);
        stringListExt.add(STRING_ARR_DEFAULT);

        var capacityStatrt = stringListExt.getCapacity();

        stringListExt.offsetRight(1, 1);

        var capacityAfterOffSet = stringListExt.getCapacity();
        assertTrue(capacityStatrt < capacityAfterOffSet );
    }

    @Test
    public void initialForNewArrayString() {
        stringListExt = new StringListExt(20);

        var sizeStart = stringListExt.getCapacity();
        stringListExt.initialForNewArrayString(25);

        assertTrue(sizeStart < stringListExt.getCapacity());
    }

    @Test
    public void initialForNewArrayString_capacityNotModifies() {

        var sizeStart = stringListExt.getCapacity();
        stringListExt.initialForNewArrayString(5);

        assertEquals(sizeStart, stringListExt.getCapacity());
    }

    @Test
    public void verifyForArrDataWithNull() {
        String[] arrString = new String[]{};
        assertThrows(ErrStringListException.class, ()-> stringListExt.verifyForArrData(arrString));
    }

    @Test
    public void verifyForArrDataWithZeroData() {
        assertThrows(ErrStringListException.class, ()-> stringListExt.verifyForArrData(null));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    public void verifyIndex(int index) {

        if (index < 0) {
            assertThrows(ErrStringListException.class, () -> StringListService.verifyIndex(index));
        } else {
            assertDoesNotThrow( () -> StringListService.verifyIndex(index));
        }
    }

}
