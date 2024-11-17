import controller.Controller;
import model.Model;
import view.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Model model = new Model();
        View view = new View(stage);
        Controller controller = new Controller(model, view);

        model.addObserver(view);

        view.addHandler(controller);
    }
}