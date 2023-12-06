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
        if (index >= size || index < 0) {
            runException("Интекс за пределом допустимого значения");
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
        return indexOf(str) > -1;
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

        if (indexResult < 0) {
            runException("Нет данных");
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
    public boolean equals(StringListTemp otherList) {
        return false;
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
        }

        append("empty");
        for (var index = size -1; index >= indexOffSet; index--) {
            arrString[index] = arrString[index - 1];
        }

        arrString[indexOffSet] = "empty";
    }

}
