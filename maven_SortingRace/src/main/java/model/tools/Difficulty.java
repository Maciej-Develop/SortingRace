package model.tools;

public enum Difficulty {
    BABY_DIFFICULTY(0, 10), VERY_EASY(0, 100), EASY(0, 1000), NORMAL(0, 10000), HARD(0, 100000), VERY_HARD(0, 1000000);

    private final int min;

    private final int max;

    Difficulty(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMax() {
        return max;
    }

    @Override
    public String toString() {
        return this.name() + " (" + this.min + "," + this.max + ")";
    }
}
