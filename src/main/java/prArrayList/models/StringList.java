package prArrayList.models;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import prArrayList.exceptions.ErrStringListException;

public class StringList implements StringListAPI {
    @Getter
    private String[] arrString;
    @Setter
    private int capacity = 20;
    @Getter
    private int size;

    // ------------------------------------------

    @SneakyThrows(ErrStringListException.class)
    private void runException(String err) {
        throw new ErrStringListException(err);
    }

    private void initialDefault() {
        arrString = new String[capacity];
        size = 0;
    }

    private void verifyData(String str, boolean withSize) {
        if (str == null || str.isBlank()) {
            runException("Нет данных для поиска");
        }

        if (withSize && size == capacity) {
            runException("Переполнение списка");
        }
    }

    private void verifyIndex(int index) {
        if (index > size - 1 || index < 0) {
            runException("Интекс за пределом допустимого значения");
        }
    }

    private void verifyIndexGrup(int sizeAdd, int startIndex) {
        if ( size == capacity
                || (size + sizeAdd) > capacity - 1
                || startIndex > size -1
                || startIndex < 0 ) {
            runException("Переполнение списка");
        }
    }

    public StringList() {
        initialDefault();
    }

    public StringList(int capacity) {
        this.capacity = capacity;
        initialDefault();
    }

    // --------------------- start methods Override

    @Override
    public String add(String str) {
        verifyData(str, true);

        return append(str);
    }

    @Override
    public String add(int index, String str) {
        verifyData(str, true);

        offsetRight(index);
        arrString[index] = str;

        return arrString[index];
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

    public int add(int index, String[] arrAdd) {
        verifyIndexGrup(arrAdd.length, index);

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

        for (var index = 0; index < arrAdd.length; index++) {
            var strAdd = arrAdd[index];
            verifyData(strAdd, false);
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
        verifyData(str,false);

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

        verifyData(str, false);

        int indexResult = -1;

        for(var index = 0; index < size; index++)
        {
            if (arrString[index].equals(str)) {
                indexResult = index;
                break;
            }
        }

        return indexResult;
    }

    @Override
    public int lastIndexOf(String str) {
        verifyData(str, false);

        var indexResult = -1;

        for (var index = size - 1; index >= 0; index--) {
            if (arrString[index].equals(str)) {
                indexResult = index;
                break;
            }
        }

        if (indexResult < 0) {
            runException("Нет данных");
        }

        return indexResult;
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
            while(in > 0 && resultArrString[in-1].compareTo(temp) >= 0 ) {
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
