package view;

import controller.Controller;
import model.Update;
import model.tools.Difficulty;
import model.tools.Type;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;


public class SortingController {

    @FXML
    private Spinner<Integer> threadSpinner;

    @FXML
    private ChoiceBox<Type> sortChoice;

    @FXML
    private ChoiceBox<Difficulty> configurationChoice;

    @FXML
    private Button start;

    @FXML
    private Label leftStatus;

    @FXML
    private Label rightStatus;

    @FXML
    private TableView<Update> table;

    @FXML
    private TableColumn<Update, Type> nameCol;

    @FXML
    private TableColumn<Update, Integer> sizeCol;

    @FXML
    private TableColumn<Update, Long> swapCol;

    @FXML
    private TableColumn<Update, Long> durationCol;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private LineChart<Integer, Long> chart;

    private XYChart.Series<Integer, Long> merge;

    private XYChart.Series<Integer, Long> bubble;

    private LocalDateTime begin;


    public void initialize() {
        threadSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1));

        sortChoice.getItems().addAll(Type.values());
        sortChoice.getSelectionModel().selectFirst();

        for (Difficulty d : Difficulty.values()) {
            configurationChoice.getItems().add(d);
        }
        configurationChoice.getSelectionModel().selectFirst();

        nameCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        swapCol.setCellValueFactory(new PropertyValueFactory<>("action"));
        durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));

        merge = new XYChart.Series<>();
        merge.setName("Merge Sort");
        bubble = new XYChart.Series<>();
        bubble.setName("Bubble Sort");
        chart.getData().add(bubble);
        chart.getData().add(merge);

        leftStatus.setText("Threads actifs : " + Thread.activeCount());
    }

    public void addHandler(Controller controller) {
        start.setOnAction(event -> {
            begin = LocalDateTime.now();
            progressBar.setProgress(0.0);
            rightStatus.setText("");
            Type type = sortChoice.getSelectionModel().getSelectedItem();
            if (type == Type.BUBBLE) {
                bubble.getData().clear();
            } else {
                merge.getData().clear();
            }
            controller.start(
                    threadSpinner.getValue(),
                    type,
                    configurationChoice.getSelectionModel().getSelectedItem()
            );
            leftStatus.setText("Threads actifs : " + Thread.activeCount());
        });
    }

    @FXML
    public void quitItemAction() {
        System.exit(0);
    }

    public void update(Update update) {
        Platform.runLater(() -> {
            table.getItems().add(update);
            if (update.getType() == Type.BUBBLE) {
                bubble.getData().add(new XYChart.Data<>(update.getSize(), update.getAction()));
            } else {
                merge.getData().add(new XYChart.Data<>(update.getSize(), update.getAction()));
            }
            progressBar.setProgress(progressBar.getProgress() + 0.1);
        });
    }

    public void updateTime(String[] times) {
        Platform.runLater(() -> {
            rightStatus.setText("Début : " + times[0] + " | Fin : " + times[1] + " | Durée : " + times[2] + " ms");
        });
    }

}
