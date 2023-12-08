package prArrayList.models;

import lombok.Getter;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static prArrayList.models.StringListService.*;

public class StringListImpl implements StringListAPI {

    private final double FILLFACTOR_DEFAULT = 0.75;
    private final int CAPACITY_DEFAULT = 20;

    private String[] arrString;

    @Getter private int capacity = CAPACITY_DEFAULT;

    @Getter
    private int size;

    @Getter private boolean fixedCapacity = true;
    private double fillFactor = FILLFACTOR_DEFAULT;

    private int getIndexByString(String str, boolean maxIndex) {
        verifyData(str);

        int result;
        try (IntStream resIndex = IntStream.range(0, size - 1)) {
            resIndex
                    .filter(index -> arrString[index].equals(str))
                    .map(index -> index);

            if (maxIndex) {
                result = resIndex.max().isEmpty() ? -1 : resIndex.max().getAsInt();
            } else {
                result = resIndex.min().isEmpty() ? -1 : resIndex.min().getAsInt();
            }
        }

//        IntStream resIndex = IntStream.range(0, size - 1)
//                .filter(index -> arrString[index].equals(str))
//                .map(index -> index);

        return result;
    }

    // ------------------------------------------

    private void initialDefault() {
        arrString = new String[capacity];
        size = 0;
    }

    private void ifNecessaryExpand(int sizeAdd) {
        if (!fixedCapacity) {
            int newCapacity = size + sizeAdd;
            double newFillFactor = (newCapacity / (double) capacity);

            if (newFillFactor > fillFactor) {
                var bufIndex = new Object(){  public int index = 0;  };

                var newArray = new String[newCapacity];
                Stream.of(arrString).limit(size).forEach(str ->
                        newArray[bufIndex.index] = arrString[bufIndex.index++]);

                arrString = newArray;
                capacity = newCapacity;
            }
        }
    }

    private void verifyData(String str) {
        if (str == null || str.isBlank()) {
            runException("Нет данных для поиска");
        }
    }

    public void verifyIndex(int index) {
        if (index < 0 || (fixedCapacity && (index >= capacity ))) {
            runException("Индекс за пределом допустимого значения");
        }
    }

    public void verifyIndexGrup(int sizeAdd, int startIndex) {
        if (size == capacity
                || (fixedCapacity && (size + sizeAdd) >= capacity)) {
            runException("Переполнение списка");
        } else if (startIndex < 0 || startIndex >= capacity ) {
            runException("Индекс за пределом допустимого значения");
        }
    }

    public StringListImpl() {
        initialDefault();
    }

    public StringListImpl(int capacity) {
        this.capacity = capacity;
        initialDefault();
    }

    public StringListImpl(int capacity, boolean fixedCapacity) {
        this.capacity = capacity;
        this.fixedCapacity = fixedCapacity;
        initialDefault();
    }

    // --------------------- start methods Override

    @Override
    public String add(String str) {
        return append(str);
    }

    @Override
    public String add(int index, String str) {
        verifyData(str);
        verifyIndex(index);

        ifNecessaryExpand(1);

        offsetRight(index);
        arrString[index] = str;

        return arrString[index];
    }

    public int add(int index, String[] arrAdd) {

        verifyIndexGrup(arrAdd.length, index);
        ifNecessaryExpand(arrAdd.length);

        var startIndex = index;
        var numOffSetRight = arrAdd.length;
        var endIndex = startIndex + numOffSetRight;

        offsetRight(numOffSetRight, startIndex);

        for (var indexArr = 0; startIndex < endIndex; startIndex++, indexArr++) {
            arrString[startIndex] = arrAdd[indexArr];
        }

        return numOffSetRight;
    }

    public int add(String[] arrAdd) {
        verifyIndexGrup(arrAdd.length, 0);
        ifNecessaryExpand(arrAdd.length);

        for (var index = 0; index < arrAdd.length; index++) {
            var strAdd = arrAdd[index];
            verifyData(strAdd);
        }

        var resultAdd = 0;
        for (var index = 0; index < arrAdd.length; index++) {
            var strAdd = arrAdd[index];
            arrString[size++] = strAdd;
            resultAdd++;
        }

        return resultAdd;
    }

    @Override
    public String append(String str) {
        verifyData(str);
        verifyIndex(size);

        ifNecessaryExpand(1);

        arrString[size++] = str;

        return str;
    }

    @Override
    public String set(int index, String str) {
        verifyIndex(index);

        return (arrString[index] = str);
    }

    @Override
    public String remove(String str) {
        verifyData(str);

        var index = indexOf(str);
        if (index < 0) {
            runException("Нет данных");
        }

        return remove(index);
    }

    @Override
    public String remove(int index) {
        verifyIndex(index);

        var resultRemove = get(index);
        offsetLeft(index);

        return resultRemove;
    }

    @Override
    public boolean contains(String str) {
        return  indexOf(str) > -1;
    }

    @Override
    public int indexOf(String str) {

        verifyData(str);

        var resIndex = IntStream.range(0, size-1)
                .filter(index-> arrString[index].equals(str))
                .map(index-> index)
                .min();

        return resIndex.isEmpty() ? -1 : resIndex.getAsInt();
    }

    @Override
    public int lastIndexOf(String str) {

        verifyData(str);

        var resIndex = IntStream.range(0, size-1)
                .filter(index-> arrString[index].equals(str))
                .map(index-> index)
                .max();

        return resIndex.isEmpty() ? -1 : resIndex.getAsInt();
    }

    @Override
    public String get(int index) {
        verifyIndex(index);

        return arrString[index];
    }

    @Override
    public String[] getClone() {
        return getClone(arrString);
    }

    @Override
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

    @Override
    public String[] sortArrayString(String[] strList) {
        int in, out;

        String[] resultArrString = strList == null ? getClone(arrString) : strList;

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

    @Override
    public boolean equals(String[] otherStringArr) {
        var resultFalse = false;

        var arrStringClone = getClone(arrString);
        var otherStringArrClone = getClone(otherStringArr);

        if (arrStringClone.length != otherStringArrClone.length) {
            return resultFalse;
        }

        var strListCloneSorted = sortArrayString(arrStringClone);
        var otherListArrSorted = sortArrayString(otherStringArrClone);

        for (var index = 0; index < strListCloneSorted.length; index++) {
            if (!strListCloneSorted[index].equals(otherListArrSorted[index])) {
                return resultFalse;
            }
        }

        return !resultFalse;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        initialDefault();
    }

    @Override
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

    @Override
    public void offsetLeft(int indexOffSet) {
        verifyIndex(indexOffSet);

        for (var index = indexOffSet; index < size - 1; index++) {
            arrString[index] = arrString[index + 1];
        }

        size--;
    }

    @Override
    public void offsetRight(int numOffSetRight, int indexStart) {
        verifyIndexGrup(numOffSetRight, indexStart);

        var baseIndex = size -1;
        for (var numberAdd = 0; numberAdd < numOffSetRight; numberAdd++) {
            append("empty");
        }

        var lastIndexSet = size-1;
        var indexEnd = lastIndexSet + numOffSetRight;

        for (; baseIndex >= indexStart; baseIndex--, lastIndexSet-- ) {
            arrString[lastIndexSet] = arrString[baseIndex];
        }
    }

    @Override
    public void offsetRight(int indexOffSet) {
        if ( size == capacity || indexOffSet > size -1) {
            runException("переполнение массива");
        }

        append("empty");
        for (var index = size -1; index >= indexOffSet; index--) {
            arrString[index] = arrString[index - 1];
        }

        arrString[indexOffSet] = "empty";
    }

}
