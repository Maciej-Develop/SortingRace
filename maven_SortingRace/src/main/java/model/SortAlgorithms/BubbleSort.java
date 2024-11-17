package model.SortAlgorithms;

public class BubbleSort {

    public static long sort(int[] arr) {
        long op = 0;
        if (arr.length < 2) {
            return op;
        }
        int i = 0, n = arr.length;
        op += 2;
        boolean swapNeeded = true;
        op++;
        while (i < n - 1 && swapNeeded) {
            swapNeeded = false;
            op++;
            for (int j = 1; j < n - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j - 1];
                    op++;
                    arr[j - 1] = arr[j];
                    op++;
                    arr[j] = temp;
                    op++;
                    swapNeeded = true;
                    op++;
                }
            }
            if (!swapNeeded) {
                break;
            }
            i++;
        }
        return op;
    }
}
