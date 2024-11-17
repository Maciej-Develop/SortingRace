package view;

import controller.Controller;
import dp.Observable;
import dp.Observer;
import model.Update;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class View implements Observer {

    private final SortingController sortingController;

    public View(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sort.fxml"));
            Parent root = loader.load();
            this.sortingController = loader.getController();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Sorting Race");
            stage.show();
        } catch (IOException e) {
            System.err.println("Problem while loading a fxml file");
            throw new RuntimeException(e);
        }
    }

    public void addHandler(Controller controller) {
        this.sortingController.addHandler(controller);
    }

    @Override
    public void update(Observable observable, Object arg) {
        if (arg instanceof Update) {
            sortingController.update((Update) arg);
        } else if (arg instanceof String[]) {
            sortingController.updateTime((String[]) arg);
        }
    }
}
