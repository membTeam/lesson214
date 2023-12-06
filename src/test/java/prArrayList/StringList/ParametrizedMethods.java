package prArrayList.StringList;

import java.util.Arrays;
import java.util.stream.Stream;

public class ParametrizedMethods {

    public static final String[] STRING_ARR = new String[]{"125", "325"};
    public static final String[] STRING_ARR_DEFAULT = new String[]{"103", "102", "103", "100", "104", "105"};
    public static final String[] STRING_ARR_EQUALS = new String[]{"104", "103", "102", "105", "103", "100" };
    public static final String[] STRING_ARR_NOT_EQUALS = new String[]{"104", "103", "102", "105", "103", "300" };
    public static final String[] STRING_ARR_NOT_EQUALS2 = new String[]{"104", "103", "102", "105", "103", "300", "200" };

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
