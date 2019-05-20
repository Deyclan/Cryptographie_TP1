package test;

import utils.TupleComparator;
import utils.Tuple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TupleTest {

    public static void main(String[] args) {
        List<Tuple<String, Integer>> list = new ArrayList<>();
        Tuple<String, Integer> tuple1 = new Tuple<>("a", 1);
        Tuple<String, Integer> tuple2 = new Tuple<>("b", 2);
        Tuple<String, Integer> tuple3 = new Tuple<>("c", 3);
        Tuple<String, Integer> tuple4 = new Tuple<>("d", 4);
        Tuple<String, Integer> tuple5 = new Tuple<>("e", 5);

        list.add(tuple4);
        list.add(tuple1);
        list.add(tuple5);
        list.add(tuple3);
        list.add(tuple2);

        printList(list);

        TupleComparator tupleComparator = new TupleComparator();
        Collections.sort(list, tupleComparator);

        printList(list);
    }

    static void printList(List<Tuple<String, Integer>> list){
        for (Tuple tuple: list) {
            System.out.println("Key : " + tuple.getKey() + " | " + "Value : " + tuple.getValue());
        }
        System.out.println();
    }
}
