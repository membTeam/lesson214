package prArrayList.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import prArrayList.exceptions.ErrOverflowException;

import java.util.Random;

@Repository
public class StringList {

    @Getter
    private String[] arrString;

    @Getter
    @Setter
    private int capacity = 20;

    @Getter
    private double fillFactor = 0.75;

    @Getter
    private int size = 0;

    private record RecodMinValue(int index, String data){};

    private boolean usingFillFactor = false;

    public StringList() {
        this.arrString = new String[this.capacity];
    }

    public StringList(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        }

        this.usingFillFactor = true;

        this.arrString = new String[this.capacity];
        loadingInitialData(0);
    }

    public StringList(int capacity, double fillFactor) {
        if (capacity > 0) {
            this.capacity = capacity;
        }
        if (fillFactor > 0) {
            this.fillFactor = fillFactor;
        }

        this.usingFillFactor = true;

        this.arrString = new String[this.capacity];
        loadingInitialData(0);
    }

    private void loadingInitialData(int indexStart) {
        for (int index = indexStart; index < capacity; index++) {
            arrString[index] = "";
            size++;
        }
    }

    public void loadInitialRandomData(int endIndex) {
        if (endIndex > capacity - 1) {
            return;
        }
        int start = 1000;
        int end = 10000;
        var random = new Random();

        for (int index = 0; index < endIndex; index++) {
            int number = random.nextInt((end - start) + 1) + start;
            arrString[index] = "string: " + number;
        }
    }

    public RecodMinValue getMinData() {
        int indexMin = -1;
        String strMin = "";

        for(int index = 0; index < size; index++){
            if (strMin.isBlank()) {
                strMin = arrString[index];
                indexMin = index;
            } else if (strMin.compareTo(arrString[index]) > 0) {
                strMin = arrString[index];
                indexMin = index;
            }
        }

        return new RecodMinValue(indexMin, strMin);
    }

    public String[] sortString() {
        int in, out;

        String[] result = arrString;
        /*var recordMinValueData = getMinData();

        for (int index = recordMinValueData.index; index > 0; index--) {
            result[index] = result[index - 1];
        }
        result[0] = recordMinValueData.data;*/

        for(out = 1; out < size; out++) {
            String temp = result[out];
            in = out;
            while(in > 0 && result[in-1].compareTo(temp) > 0 ) {
                result[in] = result[in-1];
                --in;
            }

            result[in] = temp;
        }

        return result;
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

        arrString[size] = item;
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
            arrString[arrIndex] = items[index];
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

        for (int index = 0; index < capacity; index++) {
            var temp = index > (size -1) ? "" : arrString[index];
            newArrString[index] = temp;
        }

        arrString = newArrString;
    }
}
