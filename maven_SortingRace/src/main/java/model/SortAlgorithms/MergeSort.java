package model.SortAlgorithms;

public class MergeSort {

    public static long sort(int[] a, int n) {
        long op = 0;
        if (n < 2) {
            return op;
        }
        int mid = n / 2;
        op++;
        int[] l = new int[mid];
        op++;
        int[] r = new int[n - mid];
        op++;

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
            op++;
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
            op++;
        }
        op += sort(l, mid);
        op += sort(r, n - mid);

        op += merge(a, l, r, mid, n - mid);
        return op;
    }

    private static long merge(int[] a, int[] l, int[] r, int left, int right) {
        long op = 0;
        int i = 0, j = 0, k = 0;
        op += 3;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
            op++;
        }
        while (i < left) {
            a[k++] = l[i++];
            op++;
        }
        while (j < right) {
            a[k++] = r[j++];
            op++;
        }
        return op;
    }
}
