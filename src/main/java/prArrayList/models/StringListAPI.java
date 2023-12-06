package prArrayList.models;

public interface StringListAPI {
    String add(String str);
    String add(int index, String str);
    int add(String[] arrAdd);
    int add(int index, String[] arrAdd);
    void offsetRight(int numOffSetRight, int indexStart);
    void offsetRight(int indexOffSet);
    void offsetLeft(int indexOffSet);
    String append(String str);
    String set(int index, String str);
    String remove(String str);
    String remove(int index);
    boolean contains(String str);
    int indexOf(String str);
    int lastIndexOf(String str);
    String get(int index);
    String[] getClone(String[] otherStringArray);
    String[] getClone();
    boolean equals(String[] otherStringArray);
    String[] sortArrayString(String[] strList);
    boolean isEmpty();
    void clear();
    String[] toArray();
}
