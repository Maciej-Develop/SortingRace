package model;

import dp.Observable;
import dp.Observer;
import model.tools.Difficulty;
import model.tools.Type;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Model extends Observable implements Observer {

    private ExecutorService pool;

    private int progress;

    private long start;

    public void start(int threads, Type type, Difficulty difficulty) {
        Generator generator = new Generator(difficulty.getMax());
        progress = 0;
        if (pool != null) {
            pool.shutdown();
        }
        pool = Executors.newFixedThreadPool(threads);
        start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            Job job = new Job(generator, type);
            job.addObserver(this);
            pool.execute(job);
        }
    }

    private String[] times() {
        String[] s = new String[3];
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss::SS");
        s[0] = format.format(new Date(start));
        s[1] = format.format(new Date(System.currentTimeMillis()));
        s[2] = String.valueOf(System.currentTimeMillis() - start);
        return s;
    }

    @Override
    public void update(Observable observable, Object arg) {
        this.notifyObservers(arg);
        progress++;
        if (progress == 10) {
            this.notifyObservers(times());
        }
    }
}
