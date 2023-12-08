package prArrayList.models;

public interface StringListAPI {

    String add(String str);
    String add(int index, String str);
    int add(String[] arrAdd);
    int add(int index, String[] arrAdd);
    String append(String str);

    void offsetRight(int numOffSetRight, int indexStart);
    void offsetLeft(int indexOffSet);
    void offsetRight(int indexOffSet);

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
