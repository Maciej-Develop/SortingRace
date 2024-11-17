package model;

import dp.Observable;
import model.SortAlgorithms.Sort;
import model.tools.Type;

public class Job extends Observable implements Runnable{

    private final Generator generator;

    private final Type type;

    public Job(Generator generator, Type type){
        this.generator = generator;
        this.type = type;
    }

    @Override
    public void run() {
        int[] tab = generator.generate();
        long start = System.currentTimeMillis();
        long op = Sort.sort(type,tab);
        System.out.println(Thread.currentThread().getName() + " Tab sorted");
        long end = System.currentTimeMillis();
        this.notifyObservers(new Update(type,tab.length,op,end-start));
    }
}
