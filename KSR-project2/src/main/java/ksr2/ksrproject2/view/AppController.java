package ksr2.ksrproject2.view;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ksr2.ksrproject2.view.model.SingleSubjectSummaryDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    @FXML
    private TableView<SingleSubjectSummaryDTO> summaryTable;
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
    @FXML
    private void initSummaryTableColumns() {
        // Create new columns
        TableColumn<SingleSubjectSummaryDTO, String> summaryColumn = new TableColumn<>("Podsumowanie");
        summaryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTextValue()));
        summaryColumn.setPrefWidth(500);

        TableColumn<SingleSubjectSummaryDTO, Double> goodnessOfSummaryColumn = new TableColumn<>("T");
        goodnessOfSummaryColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getGoodnessOfSummary()).asObject());
        goodnessOfSummaryColumn.setCellFactory(column -> new RoundedTableCell<>());

        TableColumn<SingleSubjectSummaryDTO, Double> degreeOfTruthColumn = new TableColumn<>("T1");
        degreeOfTruthColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getDegreeOfTruth_T1()).asObject());
        degreeOfTruthColumn.setCellFactory(column -> new RoundedTableCell<>());

        TableColumn<SingleSubjectSummaryDTO, Double> degreeOfImprecisionColumn = new TableColumn<>("T2");
        degreeOfImprecisionColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getDegreeOfImprecision_T2()).asObject());
        degreeOfImprecisionColumn.setCellFactory(column -> new RoundedTableCell<>());

        TableColumn<SingleSubjectSummaryDTO, Double> degreeOfCoveringColumn = new TableColumn<>("T3");
        degreeOfCoveringColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getDegreeOfCovering_T3()).asObject());
        degreeOfCoveringColumn.setCellFactory(column -> new RoundedTableCell<>());

        TableColumn<SingleSubjectSummaryDTO, Double> degreeOfAppropriatenessColumn = new TableColumn<>("T4");
        degreeOfAppropriatenessColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getDegreeOfAppropriateness_T4()).asObject());
        degreeOfAppropriatenessColumn.setCellFactory(column -> new RoundedTableCell<>());

        TableColumn<SingleSubjectSummaryDTO, Double> degreeOfSummaryColumn = new TableColumn<>("T5");
        degreeOfSummaryColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getDegreeOfSummary_T5()).asObject());
        degreeOfSummaryColumn.setCellFactory(column -> new RoundedTableCell<>());

        TableColumn<SingleSubjectSummaryDTO, Double> degreeOfQuantifierImprecisionColumn = new TableColumn<>("T6");
        degreeOfQuantifierImprecisionColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getDegreeOfQuantifierImprecision_T6()).asObject());
        degreeOfQuantifierImprecisionColumn.setCellFactory(column -> new RoundedTableCell<>());

        TableColumn<SingleSubjectSummaryDTO, Double> degreeOfQuantifierCardinalityColumn = new TableColumn<>("T7");
        degreeOfQuantifierCardinalityColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getDegreeOfQuantifierCardinality_T7()).asObject());
        degreeOfQuantifierCardinalityColumn.setCellFactory(column -> new RoundedTableCell<>());

        TableColumn<SingleSubjectSummaryDTO, Double> degreeOfSummarizerCardinalityColumn = new TableColumn<>("T8");
        degreeOfSummarizerCardinalityColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getDegreeOfSummarizerCardinality_T8()).asObject());
        degreeOfSummarizerCardinalityColumn.setCellFactory(column -> new RoundedTableCell<>());

        TableColumn<SingleSubjectSummaryDTO, Double> degreeOfQualifierImprecisionColumn = new TableColumn<>("T9");
        degreeOfQualifierImprecisionColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getDegreeOfQualifierImprecision_T9()).asObject());
        degreeOfQualifierImprecisionColumn.setCellFactory(column -> new RoundedTableCell<>());

        TableColumn<SingleSubjectSummaryDTO, Double> degreeOfQualifierCardinalityColumn = new TableColumn<>("T10");
        degreeOfQualifierCardinalityColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getDegreeOfQualifierCardinality_T10()).asObject());
        degreeOfQualifierCardinalityColumn.setCellFactory(column -> new RoundedTableCell<>());

        TableColumn<SingleSubjectSummaryDTO, Double> lengthOfQualifierColumn = new TableColumn<>("T11");
        lengthOfQualifierColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getLengthOfQualifier_T11()).asObject());
        lengthOfQualifierColumn.setCellFactory(column -> new RoundedTableCell<>());

        // Add the columns to the TableView
        summaryTable.getColumns().addAll(
                summaryColumn,
                goodnessOfSummaryColumn,
                degreeOfTruthColumn,
                degreeOfImprecisionColumn,
                degreeOfCoveringColumn,
                degreeOfAppropriatenessColumn,
                degreeOfSummaryColumn,
                degreeOfQuantifierImprecisionColumn,
                degreeOfQuantifierCardinalityColumn,
                degreeOfSummarizerCardinalityColumn,
                degreeOfQualifierImprecisionColumn,
                degreeOfQualifierCardinalityColumn,
                lengthOfQualifierColumn
        );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        qualifierChoiceBox.getItems().addAll("squat-strenght-level");
        quantifierChoiceBox.getItems().addAll("squat-strenght-level");
        summarizerChoiceBox.getItems().addAll("squat-strenght-level");
        sortByChoiceBox.getItems().addAll("squat-strenght-level");
        initSummaryTableColumns();


    }
    public static class RoundedTableCell<S, T> extends TableCell<S, T> {
        @Override
        protected void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);

            if (item == null || empty) {
                setText(null);
            } else {
                if (item instanceof Double) {
                    double value = (double) item;
                    if (value < 0.01 && value != 0) {
                        setText(">0.00");
                    } else if (value > 0.99 && value != 1) {
                        setText("<1.00");
                    } else {
                        setText(String.format("%.2f", value));
                    }
                } else {
                    setText(item.toString());
                }
            }
        }
    }
}