package ksr2.ksrproject2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditPanelView {

    @FXML
    private Button retrunButton;

    @FXML
    private ChoiceBox<String> editObject;
    @FXML
    private TextField nameTextField;

    @FXML
    private ChoiceBox<String> functionType;

    @FXML
    private ChoiceBox<String> linguistigVariableChoiceBox;


    @FXML
    protected void initialize() {
        editObject.getItems().addAll("Qualifier", "Quantifier", "Summarizer");
        functionType.getItems().addAll("Triangular", "Trapezoidal", "Gaussian");
        linguistigVariableChoiceBox.getItems().addAll("squat-strenght-level");

    }
    @FXML
    void onReturnToMainPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("app-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Tutaj umieść metody obsługi zdarzeń dla przycisków i innych elementów interfejsu użytkownika
}