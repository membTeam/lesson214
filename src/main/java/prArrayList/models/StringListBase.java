package prArrayList.models;

import lombok.Getter;
import static prArrayList.models.StringListService.*;


abstract public class StringListBase implements StringListAPI {
    private final int DEFAULT_CAPACITY = 20;

    @Getter private String[] arrString;
    @Getter private int size;
    @Getter private int capacity;

    private final String[] ARR_CLASS_NAME = new String[]{"StringList", "StringListExt"};

    public StringListBase() {
        arrString = new String[DEFAULT_CAPACITY];
        size = 0;
    }

    public void setingArrString(Class<?> className, String[] arrString ) {
        /*if (!verifyClassName(className)) {
            runException(ACCESS_IS_CLOSED);
        }
        this.arrString = arrString;*/
    }

    public void settingSize(Class<?> className, int size) {
        /*if (!verifyClassName(className)) {
            runException(ACCESS_IS_CLOSED);
        }
        this.size = size;*/
    }

    @Override
    abstract public String add(String str);

    @Override
    abstract public String add(int index, String str);

    @Override
    abstract public int add(String[] arrAdd);

    @Override
    abstract public int add(int index, String[] arrAdd);

    @Override
    abstract public void offsetRight(int numOffSetRight, int indexStart);

    @Override
    abstract public void offsetRight(int indexOffSet);

    @Override
    abstract public void offsetLeft(int indexOffSet);

    @Override
    abstract public String append(String str);

    @Override
    abstract public String set(int index, String str);

    @Override
    abstract public String remove(String str);

    @Override
    abstract public String remove(int index);

    @Override
    abstract public boolean contains(String str);

    @Override
    abstract public int indexOf(String str);

    @Override
    abstract public int lastIndexOf(String str);

    @Override
    abstract public String get(int index);

    @Override
    abstract public String[] getClone(String[] otherStringArray);

    @Override
    abstract public String[] getClone();

    @Override
    abstract public boolean equals(String[] otherStringArray);

    @Override
    abstract public String[] sortArrayString(String[] strList);

    @Override
    abstract public boolean isEmpty();

    @Override
    abstract public void clear();

    @Override
    abstract public String[] toArray();
}
