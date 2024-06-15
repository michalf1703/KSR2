package ksr2.ksrproject2.view;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import ksr2.ksrproject2.logic.summarization.*;
import ksr2.ksrproject2.logic.summarization.forms.singleForms.FirstFormSingleSubjectSummary;
import ksr2.ksrproject2.logic.summarization.forms.singleForms.SecondFormSingleSubjectSummary;
import ksr2.ksrproject2.logic.summarization.forms.singleForms.SingleSubjectSummary;
import ksr2.ksrproject2.view.model.SingleSubjectSummaryDTO;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AppController implements Initializable {
    @FXML
    private TableView<SingleSubjectSummaryDTO> summaryTable;

    @FXML
    private TreeView<String> summarizersTreeView, qualifiersTreeView;

    @FXML
    private TextField numberOfSummariesField;

    @FXML ChoiceBox<String> sortByChoiceBox;

    @FXML
    private TextField weightT1TF,weightT2TF, weightT3TF, weightT4TF, weightT5TF, weightT6TF, weightT7TF, weightT8TF, weightT9TF, weightT10TF, weightT11TF;

    private final List<SingleSubjectSummary> summaries = new ArrayList<>();


    private void fillQualifiersTreeView() {
        CheckBoxTreeItem<String> root = new CheckBoxTreeItem<>("Qualifiers");
        root.setExpanded(true);
        for (LinguisticVariable var : Data.linguisticVariables) {
            CheckBoxTreeItem<String> variableTreeItem = new CheckBoxTreeItem<>(var.getName());
            variableTreeItem.setExpanded(true);
            for (ksr2.ksrproject2.logic.summarization.Label label : var.getLabels()) {
                CheckBoxTreeItem<String> labelTreeItem = new CheckBoxTreeItem<>(label.getName());
                variableTreeItem.getChildren().add(labelTreeItem);
            }
            root.getChildren().add(variableTreeItem);
        }
        qualifiersTreeView.setRoot(root);
        qualifiersTreeView.setCellFactory(CheckBoxTreeCell.forTreeView());
    }

    private void fillSummarizersTreeView() {
        CheckBoxTreeItem<String> root = new CheckBoxTreeItem<>("Summarizers");
        root.setExpanded(true);
        for (LinguisticVariable var : Data.linguisticVariables) {
            CheckBoxTreeItem<String> variableTreeItem = new CheckBoxTreeItem<>(var.getName());
            variableTreeItem.setExpanded(true);
            for (ksr2.ksrproject2.logic.summarization.Label label : var.getLabels()) {
                CheckBoxTreeItem<String> labelTreeItem = new CheckBoxTreeItem<>(label.getName());
                variableTreeItem.getChildren().add(labelTreeItem);
            }
            root.getChildren().add(variableTreeItem);
        }
        summarizersTreeView.setRoot(root);
        summarizersTreeView.setCellFactory(CheckBoxTreeCell.forTreeView());
    }
    @FXML
    private void initSummaryTableColumns() {
        // Create new columns
        TableColumn<SingleSubjectSummaryDTO, String> summaryColumn = new TableColumn<>("Summarization");
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
        Data.initData();
        sortByChoiceBox.getItems().addAll("T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8", "T9", "T10", "T11", "mainT");
        sortByChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> sortBtn_onAction());
        fillQualifiersTreeView();
        fillSummarizersTreeView();
        initSummaryTableColumns();
        fillWeights();
    }
    @FXML
    private void sortBtn_onAction() {
        String selectedMeasure = sortByChoiceBox.getValue();
        switch (selectedMeasure) {
            case "T1":
                summaries.sort(Comparator.comparing(SingleSubjectSummary::getDegreeOfTruth_T1).reversed());
                break;
            case "T2":
                summaries.sort(Comparator.comparing(SingleSubjectSummary::getDegreeOfImprecision_T2).reversed());
                break;
            case "T3":
                summaries.sort(Comparator.comparing(SingleSubjectSummary::getDegreeOfCovering_T3).reversed());
                break;
            case "T4":
                summaries.sort(Comparator.comparing(SingleSubjectSummary::getDegreeOfAppropriateness_T4).reversed());
                break;
            case "T5":
                summaries.sort(Comparator.comparing(SingleSubjectSummary::getDegreeOfSummary_T5).reversed());
                break;
            case "T6":
                summaries.sort(Comparator.comparing(SingleSubjectSummary::getDegreeOfQuantifierImprecision_T6).reversed());
                break;
            case "T7":
                summaries.sort(Comparator.comparing(SingleSubjectSummary::getDegreeOfQuantifierCardinality_T7).reversed());
                break;
            case "T8":
                summaries.sort(Comparator.comparing(SingleSubjectSummary::getDegreeOfSummarizerCardinality_T8).reversed());
                break;
            case "T9":
                summaries.sort(Comparator.comparing(SingleSubjectSummary::getDegreeOfQualifierImprecision_T9).reversed());
                break;
            case "T10":
                summaries.sort(Comparator.comparing(SingleSubjectSummary::getDegreeOfQualifierCardinality_T10).reversed());
                break;
            case "T11":
                summaries.sort(Comparator.comparing(SingleSubjectSummary::getLengthOfQualifier_T11).reversed());
                break;
            case "mainT":
                summaries.sort(Comparator.comparing(SingleSubjectSummary::getMainSummaryMeasure).reversed());
                break;
        }
        fillSummaryTable();
    }
    private MeasureWeights retrieveWeights() {
        HashMap<String, Double> map = new HashMap<>();
        map.put("T1", Double.valueOf(weightT1TF.textProperty().get()));
        map.put("T2", Double.valueOf(weightT2TF.textProperty().get()));
        map.put("T3", Double.valueOf(weightT3TF.textProperty().get()));
        map.put("T4", Double.valueOf(weightT4TF.textProperty().get()));
        map.put("T5", Double.valueOf(weightT5TF.textProperty().get()));
        map.put("T6", Double.valueOf(weightT6TF.textProperty().get()));
        map.put("T7", Double.valueOf(weightT7TF.textProperty().get()));
        map.put("T8", Double.valueOf(weightT8TF.textProperty().get()));
        map.put("T9", Double.valueOf(weightT9TF.textProperty().get()));
        map.put("T10", Double.valueOf(weightT10TF.textProperty().get()));
        map.put("T11", Double.valueOf(weightT11TF.textProperty().get()));
        return new MeasureWeights(map);
    }
    @FXML
    private void saveToFileBtn_onAction() {
        try (FileWriter writer = new FileWriter("summaries.txt")) {
            for (SingleSubjectSummary summary : summaries) {
                writer.write(summary.toString() + " ");
                writer.write("[T1: " + summary.getDegreeOfTruth_T1() +"] ");
                writer.write("[T2: " + summary.getDegreeOfImprecision_T2() + "] ");
                writer.write("[T3: " + summary.getDegreeOfCovering_T3() + "] ");
                writer.write("[T4: " + summary.getDegreeOfAppropriateness_T4() + "] ");
                writer.write("[T5: " + summary.getDegreeOfSummary_T5() + "] ");
                writer.write("[T6: " + summary.getDegreeOfQuantifierImprecision_T6() + "] ");
                writer.write("[T7: " + summary.getDegreeOfQuantifierCardinality_T7() + "] ");
                writer.write("[T8: " + summary.getDegreeOfSummarizerCardinality_T8() + "] ");
                writer.write("[T9: " + summary.getDegreeOfQualifierImprecision_T9() + "] ");
                writer.write("[T10: " + summary.getDegreeOfQualifierCardinality_T10() + "] ");
                writer.write("[T11: " + summary.getLengthOfQualifier_T11() + "] ");
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void generateSummaryBtn_onAction() {
        Data.measureWeights = retrieveWeights();
        if (!Data.measureWeights.areCorrect()) {
            throw new RuntimeException("Measure weights are incorrect");
        }

        Set<String> temp1 = new HashSet<>();
        Set<String> temp2 = new HashSet<>();
        ObservableSet<String> checkedQualifiers = FXCollections.observableSet(temp1);
        ObservableSet<String> checkedSummarizers = FXCollections.observableSet(temp2);
        findCheckedItems((CheckBoxTreeItem<?>) qualifiersTreeView.getRoot(), checkedQualifiers);
        findCheckedItems((CheckBoxTreeItem<?>) summarizersTreeView.getRoot(), checkedSummarizers);
        summaries.clear();
        List<ksr2.ksrproject2.logic.summarization.Label> qualifiers = new ArrayList<>();
        List<ksr2.ksrproject2.logic.summarization.Label> summarizers = new ArrayList<>();

        for (String chosenOption : temp1) {
            String[] names = chosenOption.split(";");
            qualifiers.add(findLabel(names[0], names[1]));
        }
        for (String chosenOption : temp2) {
            String[] names = chosenOption.split(";");
            summarizers.add(findLabel(names[0], names[1]));
        }

        if (qualifiers.size() == 0) {
            List<Quantifier> quantifiers = new ArrayList<>();
            quantifiers.addAll(Data.relativeQuantifiers);
            quantifiers.addAll(Data.absoluteQuantifiers);
            generateSummariesFirstForm(quantifiers, qualifiers, summarizers);
        } else {
            List<Quantifier> quantifiers = new ArrayList<>(Data.relativeQuantifiers);
            generateSummariesSecondForm(quantifiers, qualifiers, summarizers);
        }

        fillSummaryTable();
    }

    private void generateSummariesFirstForm(List<Quantifier> quantifiers, List<ksr2.ksrproject2.logic.summarization.Label> qualifiers,
                                            List<ksr2.ksrproject2.logic.summarization.Label> summarizers) {
        for (int i = 1; i < summarizers.size() + 1; i++) {
            for (int j = 0; j < summarizers.size(); j++) {
                List<ksr2.ksrproject2.logic.summarization.Label> tempSumList = new ArrayList<>();
                if (j + i - 1 < summarizers.size()) {
                    for (int k = j; k < j + i; k++) {
                        tempSumList.add(summarizers.get(k));
                    }
                }
                if (tempSumList.size() == i) {
                    for (Quantifier quantifier : quantifiers) {
                        if (quantifier instanceof AbsoluteQuantifier && qualifiers.size() > 0) {
                            continue;
                        }
                        SingleSubjectSummary singleSubjectSummary;
                        if (qualifiers.size() == 0) {
                            singleSubjectSummary = new FirstFormSingleSubjectSummary(Data.measureWeights, quantifier, tempSumList, Data.powerliftingResults);
                        } else if (quantifier instanceof RelativeQuantifier) {
                            singleSubjectSummary = new SecondFormSingleSubjectSummary(Data.measureWeights, (RelativeQuantifier) quantifier, qualifiers, tempSumList, Data.powerliftingResults);
                        } else {
                            continue;
                        }
                        summaries.add(singleSubjectSummary);
                    }
                }
            }

            if (i == 2 && summarizers.size() == 3) {
                List<ksr2.ksrproject2.logic.summarization.Label> tempSumList = new ArrayList<>();
                tempSumList.add(summarizers.get(0));
                tempSumList.add(summarizers.get(2));
                for (Quantifier quantifier : quantifiers) {
                    if (quantifier instanceof AbsoluteQuantifier && qualifiers.size() > 0) {
                        continue;
                    }
                    SingleSubjectSummary singleSubjectSummary;
                    if (qualifiers.size() > 0) {
                        singleSubjectSummary = new FirstFormSingleSubjectSummary(Data.measureWeights, quantifier, tempSumList, Data.powerliftingResults);
                    } else if (quantifier instanceof RelativeQuantifier) {
                        singleSubjectSummary = new SecondFormSingleSubjectSummary(Data.measureWeights, (RelativeQuantifier) quantifier, qualifiers, tempSumList, Data.powerliftingResults);
                    } else {
                        continue;
                    }
                    summaries.add(singleSubjectSummary);
                }
            }
        }
    }

    private void generateSummariesSecondForm(List<Quantifier> quantifiers, List<ksr2.ksrproject2.logic.summarization.Label> qualifiers,
                                             List<ksr2.ksrproject2.logic.summarization.Label> summarizers) {
        for (int i = 1; i < qualifiers.size() + 1; i++) {
            for (int j = 0; j < qualifiers.size(); j++) {
                List<ksr2.ksrproject2.logic.summarization.Label> tempQuaList = new ArrayList<>();
                if (j + i - 1 < qualifiers.size()) {
                    for (int k = j; k < j + i; k++) {
                        tempQuaList.add(qualifiers.get(k));
                    }
                }
                if (tempQuaList.size() == i) {
                    generateSummariesFirstForm(quantifiers, tempQuaList, summarizers);
                }
            }
            if (i == 2 && qualifiers.size() == 3) {
                List<ksr2.ksrproject2.logic.summarization.Label> tempQuaList = new ArrayList<>();
                tempQuaList.add(qualifiers.get(0));
                tempQuaList.add(qualifiers.get(2));
                generateSummariesFirstForm(quantifiers, tempQuaList, summarizers);
            }
        }
    }

    private void findCheckedItems(CheckBoxTreeItem<?> item, ObservableSet<String> checkedItems) {
        if (item.isSelected()) {
            if (item.getParent() != null && item.getChildren().size() == 0) {
                checkedItems.add(item.getParent().getValue().toString() + ";" + item.getValue().toString());
            }
        }
        for (TreeItem<?> child : item.getChildren()) {
            findCheckedItems((CheckBoxTreeItem<?>) child, checkedItems);
        }
    }

    private ksr2.ksrproject2.logic.summarization.Label findLabel(String variableName, String labelName) {
        for (LinguisticVariable var : Data.linguisticVariables) {
            if (var.getName().equals(variableName)) {
                for (ksr2.ksrproject2.logic.summarization.Label label : var.getLabels()) {
                    if (label.getName().equals(labelName)) {
                        return label;
                    }
                }
            }
        }
        return null;
    }
    private void fillWeights() {
        weightT1TF.textProperty().set(String.valueOf(Data.measureWeights.getWeights().get("T1")));
        weightT2TF.textProperty().set(String.valueOf(Data.measureWeights.getWeights().get("T2")));
        weightT3TF.textProperty().set(String.valueOf(Data.measureWeights.getWeights().get("T3")));
        weightT4TF.textProperty().set(String.valueOf(Data.measureWeights.getWeights().get("T4")));
        weightT5TF.textProperty().set(String.valueOf(Data.measureWeights.getWeights().get("T5")));
        weightT6TF.textProperty().set(String.valueOf(Data.measureWeights.getWeights().get("T6")));
        weightT7TF.textProperty().set(String.valueOf(Data.measureWeights.getWeights().get("T7")));
        weightT8TF.textProperty().set(String.valueOf(Data.measureWeights.getWeights().get("T8")));
        weightT9TF.textProperty().set(String.valueOf(Data.measureWeights.getWeights().get("T9")));
        weightT10TF.textProperty().set(String.valueOf(Data.measureWeights.getWeights().get("T10")));
        weightT11TF.textProperty().set(String.valueOf(Data.measureWeights.getWeights().get("T11")));
    }

    private void fillSummaryTable() {
        ArrayList<SingleSubjectSummaryDTO> summariesDTO = new ArrayList<>();
        int numberOfSummaries = summaries.size();
        if (!numberOfSummariesField.getText().isEmpty()) {
            numberOfSummaries = Integer.parseInt(numberOfSummariesField.getText());
        }

        for (int i = 0; i < numberOfSummaries && i < summaries.size(); i++) {
            SingleSubjectSummary summary = summaries.get(i);
            summariesDTO.add(new SingleSubjectSummaryDTO(
                    summary.toString(),
                    summary.getMainSummaryMeasure(),
                    summary.getDegreeOfTruth_T1(),
                    summary.getDegreeOfImprecision_T2(),
                    summary.getDegreeOfCovering_T3(),
                    summary.getDegreeOfAppropriateness_T4(),
                    summary.getDegreeOfSummary_T5(),
                    summary.getDegreeOfQuantifierImprecision_T6(),
                    summary.getDegreeOfQuantifierCardinality_T7(),
                    summary.getDegreeOfSummarizerCardinality_T8(),
                    summary.getDegreeOfQualifierImprecision_T9(),
                    summary.getDegreeOfQualifierCardinality_T10(),
                    summary.getLengthOfQualifier_T11()
            ));
        }

        summaryTable.getItems().clear();
        ObservableList<SingleSubjectSummaryDTO> summaryList = FXCollections.observableArrayList();
        summaryList.addAll(summariesDTO);
        summaryTable.setItems(summaryList);
    }
    @FXML
    private void refresh() {
        fillQualifiersTreeView();
        fillSummarizersTreeView();
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