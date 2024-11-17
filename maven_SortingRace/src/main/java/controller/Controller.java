package controller;

import model.Model;
import model.tools.Difficulty;
import model.tools.Type;
import view.View;

public class Controller {

    private final Model model;

    private final View view;

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
    }

    public void start(int threads,Type type, Difficulty difficulty) {
        model.start(threads,type,difficulty);
    }
}
