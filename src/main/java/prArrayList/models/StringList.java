package prArrayList.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import prArrayList.exceptions.ErrOverflowException;

@Repository
public class StringList {

    @Getter
    private String[] arrStringString;

    @Getter
    @Setter
    private int capacity = 20;

    @Getter
    private double fillFactor = 0.75;

    @Getter
    private int size = 0;

    private boolean usingFillFactor = false;

    public StringList() {
        this.arrStringString = new String[this.capacity];
    }

    public StringList(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        }

        this.usingFillFactor = true;

        this.arrStringString = new String[this.capacity];
    }

    public StringList(int capacity, double fillFactor) {
        if (capacity > 0) {
            this.capacity = capacity;
        }
        if (fillFactor > 0) {
            this.fillFactor = fillFactor;
        }

        this.usingFillFactor = true;

        this.arrStringString = new String[this.capacity];
    }

    private double expectedFillFactor(int numAdd) {
        return (size + numAdd)/(double)capacity;
    }

    public String append(String item) {
        if (!usingFillFactor) {
            if (size == capacity) {
                throw new ErrOverflowException("Переполнение списка");
            }
        } else {
            if (expectedFillFactor(1) > fillFactor) {
                int newCapacity = capacity + 1;
                reconfigurationForNewCapacity(newCapacity);
            }
        }

        arrStringString[size] = item;
        size += 1;

        return item;
    }

    public String[] append(String[] items) {

        int newSize = size + items.length;

        if (!usingFillFactor) {
            if (newSize > capacity) {
                reconfigurationForNewCapacity(calculatedCapacity(newSize));
            }
        } else {
            if (newSize > capacity) {
                reconfigurationForNewCapacity(calculatedCapacity(newSize));
            } else if (expectedFillFactor(items.length) > fillFactor) {
                newSize = (int) (newSize * (1.0 + fillFactor));
                reconfigurationForNewCapacity(calculatedCapacity(newSize));
            }
        }

        int arrIndex = size;
        for (int index = 0; index < items.length; arrIndex++, index++) {
            arrStringString[arrIndex] = items[index];
        }

        size = arrIndex + 1;

        return items;
    }

    private int calculatedCapacity(int diffCapacity) {
        return (int) (diffCapacity * (1.0 + fillFactor)) - capacity;
    }

    public void reconfigurationForNewCapacity(int diffCapacity) {

        capacity += diffCapacity;

        String[] newArrString = new String[capacity];

        for (int index = 0; index < size; index++) {
            newArrString[index] = arrStringString[index];
        }

        arrStringString = newArrString;
    }
}
