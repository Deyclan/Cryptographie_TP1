package utils;

import java.util.Comparator;

public class TupleComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Tuple tuple1 = (Tuple) o1;
        Tuple tuple2 = (Tuple) o2;
        return tuple1.getValue().compareTo(tuple2.getValue());
    }
}
