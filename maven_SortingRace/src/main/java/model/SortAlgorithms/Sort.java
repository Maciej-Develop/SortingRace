package model.SortAlgorithms;

import model.tools.Type;

public class Sort {

    public static long sort(Type type,int [] tab) {
        if (type == Type.BUBBLE) {
            return BubbleSort.sort(tab);
        } else {
            return MergeSort.sort(tab,tab.length);
        }
    }
}
