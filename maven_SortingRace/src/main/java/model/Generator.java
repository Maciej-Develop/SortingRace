package model;

import java.util.Random;

public class Generator {

    private final int maxLength;

    private int currentLength;

    private final Random r;

    public Generator(int maxLength) {
        this.maxLength = maxLength;
        this.currentLength = maxLength / 10;
        this.r = new Random();
    }

    public synchronized int[] generate() {
        System.out.println(Thread.currentThread().getName() + " tab generated : " + currentLength);
        int[] tab = new int[currentLength];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = r.nextInt(0, 1000);
        }
        currentLength += maxLength / 10;
        return tab;
    }
}
