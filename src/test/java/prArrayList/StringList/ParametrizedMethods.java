package prArrayList.StringList;

import java.util.stream.Stream;

public class ParametrizedMethods {

    public static Stream<String> appendTestExt() {
        return Stream.of("   ", "101");
    }

    public static Stream<String> appendTest() {
        return Stream.of(" ");
    }

    public static Stream<String> indexOf() {
        return Stream.of("100", "100000", " ");
    }

    public static Stream<String> lastIndexOf() {
        return Stream.of("103", "100000", " ");
    }

    public static Stream<Integer> getFindByIndex() {
        return Stream.of(-1, 100000, 2);
    }


}
