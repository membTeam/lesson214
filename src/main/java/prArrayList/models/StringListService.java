package prArrayList.models;

import lombok.SneakyThrows;
import prArrayList.exceptions.ErrStringListException;

public class StringListService {

    public static final String INDEX_ERR = "Индекс за пределом допустимого значения";
    public static final String DATA_OVERFLOW = "Переполнение списка";
    public static final String NO_DATA_TO_PROCESS = "Нет данных для обработки";
    public static final String DATA_IS_BLANK = "В строке нет данных";


    public static void verifyIndex(int index) {
        if (index < 0) {
            runException(INDEX_ERR);
        }
    }

    public static void verifyIndexGrup(int startIndex) {
        if ( startIndex < 0 ) {
            runException(INDEX_ERR);
        }
    }

    @SneakyThrows(ErrStringListException.class)
    public static void runException(String err) {
        throw new ErrStringListException(err);
    }
}
