package prArrayList.models;

import lombok.SneakyThrows;
import prArrayList.exceptions.ErrStringListException;

public class StringListService {

    @SneakyThrows(ErrStringListException.class)
    public static void runException(String err) {
        throw new ErrStringListException(err);
    }
}
