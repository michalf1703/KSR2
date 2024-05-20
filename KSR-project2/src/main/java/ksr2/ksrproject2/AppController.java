package ksr2.ksrproject2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;

public class AppController {

    @FXML
    private ChoiceBox<String> qualifierChoiceBox;

    @FXML
    private ChoiceBox<String> quantifierChoiceBox;

    @FXML
    private ChoiceBox<String> summarizerChoiceBox;

    @FXML
    private Button advancedUserPanelButton;

    @FXML ChoiceBox<String> sortByChoiceBox;


    @FXML
    protected void initialize() {
        quantifierChoiceBox.getItems().addAll("Almost none", "Some", "About half", "Many", "Almost all", "Far below 1000", "Around 2000", "From 3000 to 6000", "Around 7000", "Much more than 8000");
        qualifierChoiceBox.getItems().addAll("junior", "senior", "master", "lightweight", "middleweight", "heavyweight", "superheavyweight", "less than 3 times bodyweight score in squat", "about 3 times bodyweight score in squat", "between 2 and 4 times bodyweight score in squat","about 4 times bodyweight score in squat", "more than 4.5 times bodyweight score in squat"  );
        summarizerChoiceBox.getItems().addAll("less than 3 times bodyweight score in squat", "weight", "squat-strenght-level", "high", "very high");
        sortByChoiceBox.getItems().addAll("T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8", "T9", "T10");

    }

    @FXML
    void onAdvancedUserPanelButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editPanel-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onMultiSubjectButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("multiView.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}