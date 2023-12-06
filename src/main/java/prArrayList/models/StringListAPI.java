package prArrayList.models;

public interface StringListAPI {
    String add(String str);
    String add(int index, String str);
    String append(String str);
    String set(int index, String str);
    String remove(String str);
    String remove(int index);
    boolean contains(String str);
    int indexOf(String str);
    int lastIndexOf(String str);
    String get(int index);
    boolean equals(StringListTemp otherList);
    boolean isEmpty();
    void clear();
    String[] toArray();
}
