package prArrayList.models;

import lombok.Getter;
import static prArrayList.models.StringListService.*;

public class StringListExt implements StringListExtAPI {
    private final int CAPACITY_DEFAULT = 20;
    private final double FILLFACTOR_DEFAULT = 0.75;

    private String[] arrString;

    @Getter private int capacity;
    @Getter private int size;
    @Getter private boolean fixedCapacity = true;
    private double fillFactor = FILLFACTOR_DEFAULT;

    private int culculateCapacity(int newCapacity) {
        return (int) (newCapacity * (1 + fillFactor));
    }

    public StringListExt() {
        capacity = CAPACITY_DEFAULT;
        initialDefault();
    }

    public StringListExt(int capacity) {
        fixedCapacity = false;
        this.capacity = capacity;
        initialDefault();
    }

    public StringListExt(int capacity, double fillFactor) {
        fixedCapacity = false;
        this.capacity = capacity;
        this.fillFactor = fillFactor;

        initialDefault();
    }

    private void initialDefault() {
        arrString = new String[capacity];
        size = 0;
    }

    private void verifyData(String str, boolean withSize) {
        if (str == null || str.isBlank()) {
            runException(DATA_IS_BLANK);
        }

        if ( fixedCapacity && withSize && size == capacity) {
            runException(DATA_OVERFLOW);
        }
    }

    // ----------------------------------

    @Override
    public void initialForNewArrayString(int addNumberString) {

        if (fixedCapacity && (size + addNumberString) > capacity -1) {
            runException(DATA_OVERFLOW);
        } else {
            var currFillFactor = (double) ((size + addNumberString) / capacity);
            if (currFillFactor > fillFactor) {
                var newSize = size + addNumberString;
                var newCpacity = culculateCapacity(newSize);

                var newArrayString = new String[newCpacity];

                for (var index = 0; index < size; index++) {
                    newArrayString[index] = arrString[index];
                }

                capacity = newCpacity;
                arrString = newArrayString;
            }
        }
    }

    public void verifyForArrData(String[] arrStr) {
        if (arrStr == null || arrStr.length == 0) {
            runException(NO_DATA_TO_PROCESS);
        }
    }

    @Override
    public String add(String str) {
        verifyData(str, true);

        return append(str);
    }

    @Override
    public String add(int index, String str) {
        return null;
    }

    @Override
    public int add(String[] arrAdd) {
        verifyIndexGrup(arrAdd.length);

        verifyForArrData(arrAdd);

        var resultAdd = 0;
        for (var index = 0; index < arrAdd.length; index++) {
            var strAdd = arrAdd[index];
            arrString[size++] = strAdd;
            resultAdd++;
        }

        return resultAdd;
    }

    @Override
    public int add(int index, String[] arrAdd) {
        return 0;
    }

    @Override
    public void offsetRight(int numOffSetRight, int indexStart) {

        var baseIndex = size -1;
        verifyIndexGrup(indexStart);

        initialForNewArrayString(numOffSetRight);

        var lastIndexSet = size-1;
        var indexEnd = lastIndexSet + numOffSetRight;

        for (; baseIndex >= indexStart; baseIndex--, lastIndexSet-- ) {
            arrString[lastIndexSet] = arrString[baseIndex];
        }
    }

    @Override
    public void offsetRight(int indexOffSet) {

    }

    @Override
    public void offsetLeft(int indexOffSet) {

    }

    @Override
    public String append(String str) {
        verifyData(str, true);

        arrString[size++] = str;

        return str;
    }

    @Override
    public String set(int index, String str) {
        return null;
    }

    @Override
    public String remove(String str) {
        return null;
    }

    @Override
    public String remove(int index) {
        return null;
    }

    @Override
    public boolean contains(String str) {
        return false;
    }

    @Override
    public int indexOf(String str) {
        return 0;
    }

    @Override
    public int lastIndexOf(String str) {
        return 0;
    }

    @Override
    public String get(int index) {
        return null;
    }

    @Override
    public String[] getClone(String[] otherStringArray) {
        return new String[0];
    }

    @Override
    public String[] getClone() {
        return new String[0];
    }

    @Override
    public boolean equals(String[] otherStringArray) {
        return false;
    }

    @Override
    public String[] sortArrayString(String[] strList) {
        return new String[0];
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public String[] toArray() {
        return new String[0];
    }

}
