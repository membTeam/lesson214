package prArrayList.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import prArrayList.exceptions.ErrOverflowException;

import java.util.Arrays;

@Repository
public class StringList {

    @Getter
    private String[] arrString;

    @Getter
    @Setter
    private int capacity = 20;

    @Getter
    private double fillFactor = 0.75;

    @Getter
    private int size = 0;

    @Getter
    private boolean usingFillFactor = false;

    private void runException(String err) {
        throw new ErrOverflowException(err);
    }

    private void initialDefault() {
        arrString = new String[capacity];
    }

    // ------------------------------------

    public StringList() {
        initialDefault();
    }

    public StringList(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        }

        this.usingFillFactor = true;

        this.arrString = new String[this.capacity];
    }

    public StringList(int capacity, double fillFactor) {
        if (capacity > 0) {
            this.capacity = capacity;
        }
        if (fillFactor > 0) {
            this.fillFactor = fillFactor;
        }

        this.usingFillFactor = true;

        this.arrString = new String[this.capacity];
    }

    public void loadInitialRandomData(int endIndex) {

        if (!usingFillFactor && endIndex > (capacity - 1)) {
            runException("Индекс за пределом допустимого значения");
        }

        initialDefault();

        var number = 100;
        for (int index = 0; index <= endIndex; index++) {
            append("string: " + number++);
        }
    }


    /**
     * Учитываются значения не равные null
     * @param strList
     * @return
     */
    public String[] getClone(String[] strList) {

        int capacityClone = 0;
        for (var index = 0; index < strList.length; index++) {
            if (strList[index] != null) {
                capacityClone++;
            }
        }

        var resArrString = new String[capacityClone];

        for (var index = 0; index < strList.length; index++) {
            if (strList[index] == null) {
                continue;
            }

            resArrString[index] = strList[index];
        }

        return resArrString;
    }

    public String[] toArray() {
        if (size == 0) {
            return null;
        }

        var resArrString = new String[size];

        var index = 0;
        while (index < size) {
            resArrString[index] = arrString[index++];
        }

        return resArrString;
    }

    public String[] sortArrayString() {
        return sortArrayString(arrString);
    }

    public String[] sortArrayString(String[] strList) {
        int in, out;

        String[] resultArrString = getClone(strList);
        for(out = 1; out < size; out++) {
            String temp = resultArrString[out];

            if (temp == null) {
                continue;
            }

            in = out;
            while(in > 0 && resultArrString[in-1].compareTo(temp) > 0 ) {
                resultArrString[in] = resultArrString[in-1];
                --in;
            }

            if (in < out) {
                resultArrString[in] = temp;
            }
        }

        return resultArrString;
    }

    public boolean comparisonFillFactor() {
        final int CURR_FILL_FACTOR = (int) ( ((double)size/capacity) * 100);
        final int FILL_FACTOR_DEFAULT = (int) (fillFactor * 100);

        return CURR_FILL_FACTOR - FILL_FACTOR_DEFAULT < 0 ? true : false;
    }

    public String add(String item) {
        return append(item);
    }

    public String append(String item) {
        if (item == null || item.isBlank()) {
            runException("null значение");
        }

        if (!usingFillFactor && size == capacity) {
            runException("Переполнение списка");
        } else if (usingFillFactor && !comparisonFillFactor()) {
            reconfigurationForNewCapacity();
        }

        arrString[size++] = item;

        return item;
    }

    // TODO: реализовать append for string[]
    /*public String[] append(String[] items) {

        int newSize = size + items.length;

        if (!usingFillFactor) {
            if (newSize > capacity) {
                reconfigurationForNewCapacity();
            }
        } else {
            if (newSize > capacity) {
                reconfigurationForNewCapacity();
            } else if (expectedFillFactor(items.length) > fillFactor) {
                //newSize = (int) (newSize * (1.0 + fillFactor));
                reconfigurationForNewCapacity();
            }
        }

        int arrIndex = size;
        for (int index = 0; index < items.length; arrIndex++, index++) {
            arrString[arrIndex] = items[index];
        }

        size = arrIndex + 1;

        return items;
    }*/

    public void reconfigurationForNewCapacity() {

        if (size == 0) {
            return;
        }

        capacity = (int) (size * (1 + fillFactor));
        String[] newArrString = new String[capacity];

        int index = 0;
        for(var item : arrString ){
            newArrString[index++] = item;
        }

        arrString = newArrString;
    }

    public String get(int index) {
        if (index > size) {
            runException("Интекс за пределом допустимого значения");
        }

        return arrString[index];
    }

    public int indexOf(String str) {
        int indexResult = -1;
        for (var item : arrString) {
            indexResult++;

            if (item == null) {
                continue;
            } else if (item.equals(str)) {
                break;
            }
        }

        return indexResult;
    }


    public boolean equals(String[] otherList ) {
        var strList = arrString.clone();

        return equals(strList, otherList);
    }

    public boolean equals(String[] strList, String[] otherList ) {

        var resultFalse = false;

        strList = getClone(strList);
        otherList = getClone(otherList);

        if (strList.length != otherList.length) {
            return resultFalse;
        }

        var strListSorted = sortArrayString(strList);
        var otherListSorted = sortArrayString(otherList);

        for (var index = 0; index < strListSorted.length; index++) {
            if (!strListSorted[index].equals(otherListSorted[index])) {
                return resultFalse;
            }
        }

        return !resultFalse;
    }

    public void clear() {
        initialDefault();
    }

    public boolean isEmpty() {
        return size > 0;
    }

    public boolean contains(String str) {
        return indexOf(str) > -1;
    }

    public String set(int index, String str) {
        if (index > (size - 1)) {
            runException("Индекс за пределами допустимого значения");
        }

        return (arrString[index] = str);
    }

    public int lastIndex(String str) {

        for (var index = size - 1; index >= 0; index--) {
            if (arrString[index].equals(str)) {
                return index;
            }
        }

        return -1;
    }

    public void offsetLeft(int indexOffSet) {
        if ( indexOffSet < 0 || indexOffSet > size - 2) {
            runException("Индекс за пределами допустимого значения");
        }

        for (var index = indexOffSet; index < size - 1; index++) {
            arrString[index] = arrString[index + 1];
        }

        size--;
    }

    public void offsetRight(int indexOffSet) {
        if ( size == capacity || indexOffSet > size -1) {
            runException("переполнение массива");
        } else if (indexOffSet > size -1) {
            runException("Индекс за пределами допустимого значения");
        }

        append("-");
        for (var index = size -1; index >= indexOffSet; index--) {
            arrString[index] = arrString[index - 1];
        }

        arrString[indexOffSet] = "empty";
    }

    public String remove(int indexRemove) {

        var resultRemove = get(indexRemove);

        offsetLeft(indexRemove);

        return resultRemove;
    }

    public String add(int index, String str) {
        offsetRight(index);

        arrString[index] = str;

        return arrString[index];
    }

}
