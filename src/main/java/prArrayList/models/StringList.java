package prArrayList.models;

import lombok.Getter;


final public class StringList extends StringListBase {

    //@Getter private int size;

    private void initialDefault() {
        String[] newArrStringList = new String[getCapacity()];

        setingArrString(this.getClass(), newArrStringList);
        settingSize(this.getClass(), 0);
    }

    public StringList() {
        initialDefault();
    }

    @Override
    public String add(String str) {
        return null;
    }

    @Override
    public String add(int index, String str) {
        return null;
    }

    @Override
    public int add(String[] arrAdd) {
        return 0;
    }

    @Override
    public int add(int index, String[] arrAdd) {
        return 0;
    }

    @Override
    public void offsetRight(int numOffSetRight, int indexStart) {

    }

    @Override
    public void offsetRight(int indexOffSet) {

    }

    @Override
    public void offsetLeft(int indexOffSet) {

    }

    @Override
    public String append(String str) {
        return null;
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
