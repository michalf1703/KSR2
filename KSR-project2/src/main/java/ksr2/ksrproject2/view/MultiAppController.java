package ksr2.ksrproject2.view;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import ksr2.ksrproject2.logic.model.PowerliftingResult;
import ksr2.ksrproject2.logic.summarization.AbsoluteQuantifier;
import ksr2.ksrproject2.logic.summarization.Label;
import ksr2.ksrproject2.logic.summarization.LinguisticVariable;
import ksr2.ksrproject2.logic.summarization.Quantifier;
import ksr2.ksrproject2.logic.summarization.forms.multiForms.*;
import ksr2.ksrproject2.logic.summarization.forms.singleForms.SingleSubjectSummary;
import ksr2.ksrproject2.view.model.MultiSubjectSummaryDTO;
import ksr2.ksrproject2.view.model.SubjectType;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import static ksr2.ksrproject2.view.model.SubjectType.*;

public class MultiAppController implements Initializable {
    @FXML
    private ComboBox<SubjectType> firstSubject_CB;
    @FXML
    private ComboBox<SubjectType> secondSubject_CB;

    @FXML
    private TreeView<String> summarizersTreeView;
    @FXML
    private TreeView<String> qualifiersTreeView;
    @FXML
    private TableView<MultiSubjectSummaryDTO> summaryTable;

    private final List<MultiSubjectSummary> summaries = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Data.initData();
        fillQualifiersTreeView();
        fillSummarizersTreeView();
        initSummaryTableColumns();
        firstSubject_CB.getItems().addAll(List.of(Weight_120_plus, Weight_80_120));
        secondSubject_CB.getItems().addAll(List.of(Weight_120_plus, Weight_80_120));
        firstSubject_CB.setOnAction(event -> {
            SubjectType selectedValue = firstSubject_CB.getValue();
            if (selectedValue.equals(Weight_80_120)) {
                secondSubject_CB.setValue(Weight_120_plus);
            } else if (selectedValue.equals(Weight_120_plus)) {
                secondSubject_CB.setValue(Weight_80_120);
            }
        });
        secondSubject_CB.setOnAction(event -> {
            SubjectType selectedValue = secondSubject_CB.getValue();
            if (selectedValue.equals(Weight_80_120)) {
                firstSubject_CB.setValue(Weight_120_plus);
            } else if (selectedValue.equals(Weight_120_plus)) {
                firstSubject_CB.setValue(Weight_80_120);
            }
        });
    }

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
    public void initSummaryTableColumns() {
        TableColumn<MultiSubjectSummaryDTO, Integer> formNumber = new TableColumn<>("Number of form");
        formNumber.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getFormNumber()).asObject());
        formNumber.setCellFactory(column -> new CenteredTableCell<>());
        formNumber.setPrefWidth(100);

        TableColumn<MultiSubjectSummaryDTO, String> summaryColumn = new TableColumn<>("Summary");
        summaryColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getTextValue()));
        summaryColumn.setPrefWidth(1200);

        TableColumn<MultiSubjectSummaryDTO, Double> degreeOfTruthColumn = new TableColumn<>("T1");
        degreeOfTruthColumn.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getDegreeOfTruth_T1()).asObject());
        degreeOfTruthColumn.setCellFactory(column -> new AppController.RoundedTableCell<>());

        summaryTable.getColumns().addAll(
                formNumber,
                summaryColumn,
                degreeOfTruthColumn
        );
    }
    public void generateButton_OnAction() {
        Set<String> temp1 = new HashSet<>();
        Set<String> temp3 = new HashSet<>();
        ObservableSet<String> checkedQuantifiers1 = FXCollections.observableSet(temp1);
        ObservableSet<String> checkedSummarizers = FXCollections.observableSet(temp3);
        findCheckedItems((CheckBoxTreeItem<?>) qualifiersTreeView.getRoot(), checkedQuantifiers1);
        findCheckedItems((CheckBoxTreeItem<?>) summarizersTreeView.getRoot(), checkedSummarizers);
        if (firstSubject_CB.getValue() == null) {
            throw new RuntimeException("Subject 1 is not selected");
        }
        if (checkedQuantifiers1.size() > 3) {
            throw new RuntimeException("Wrong number of qualifiers. You can choose up to 3 qualifiers");
        }
        if (checkedSummarizers.size() > 3 || checkedSummarizers.size() == 0) {
            throw new RuntimeException("Wrong number of summarizers. You can choose from 1 to 3 summarizers");
        }
        summaries.clear();
        List<Label> qualifiers1 = new ArrayList<>();
        List<Label> summarizers = new ArrayList<>();

        for (String chosenOption : temp1) {
            String[] names = chosenOption.split(";");
            qualifiers1.add(findLabel(names[0], names[1]));
        }
        for (String chosenOption : temp3) {
            String[] names = chosenOption.split(";");
            summarizers.add(findLabel(names[0], names[1]));
        }

        ArrayList<Quantifier> quantifiers = new ArrayList<>();
        quantifiers.addAll(Data.absoluteQuantifiers);
        quantifiers.addAll(Data.relativeQuantifiers);
        generateSummariesMultiObject(quantifiers, qualifiers1, summarizers);
        fillSummaryTable();
    }
    private void fillSummaryTable() {
        summaries.sort(Comparator.comparing(MultiSubjectSummary::getDegreeOfTruthT1).reversed());
        ArrayList<MultiSubjectSummaryDTO> summariesDTO = new ArrayList<>();
        for (MultiSubjectSummary summary : summaries) {
            int formNumber;
            if (summary.getClass() == FirstFormMultiSubjectSummary.class) {
                formNumber = 1;
            } else if (summary.getClass() == SecondFormMultiSubjectSummary.class) {
                formNumber = 2;
            } else if (summary.getClass() == ThirdFormMultiSubjectSummary.class) {
                formNumber = 3;
            } else {
                formNumber = 4;
            }
            summariesDTO.add(new MultiSubjectSummaryDTO(
                    formNumber,
                    summary.toString(),
                    summary.getDegreeOfTruthT1()
            ));
        }
        summaryTable.getItems().clear();
        ObservableList<MultiSubjectSummaryDTO> summaryList = FXCollections.observableArrayList();
        summaryList.addAll(summariesDTO);
        summaryTable.setItems(summaryList);
    }

    @FXML
    private void refresh() {
        fillQualifiersTreeView();
        fillSummarizersTreeView();
    }

    private void generateSummariesMultiObject(List<Quantifier> quantifiers, List<Label> qualifiers,
                                              List<Label> summarizers) {
        List<PowerliftingResult> objects1;
        List<PowerliftingResult> objects2;

        if (firstSubject_CB.getValue().equals(Weight_120_plus)) {
            objects1 = Data.powerliftingResults.stream().filter(powerliftingResult -> powerliftingResult.getAgeCategory().equals(Weight_120_plus.getDbName())).collect(Collectors.toList());
            objects2 = Data.powerliftingResults.stream().filter(powerliftingResult -> powerliftingResult.getAgeCategory().equals(Weight_80_120.getDbName())).collect(Collectors.toList());
        } else {
            objects1 = Data.powerliftingResults.stream().filter(powerliftingResult -> powerliftingResult.getAgeCategory().equals(Weight_80_120.getDbName())).collect(Collectors.toList());
            objects2 = Data.powerliftingResults.stream().filter(powerliftingResult -> powerliftingResult.getAgeCategory().equals(Weight_120_plus.getDbName())).collect(Collectors.toList());
        }

        for (int i = 1; i < summarizers.size() + 1; i++) {
            for (int j = 0; j < summarizers.size(); j++) {
                List<Label> tempSumList = new ArrayList<>();
                if (j + i - 1 < summarizers.size()) {
                    for (int k = j; k < j + i; k++) {
                        tempSumList.add(summarizers.get(k));
                    }
                }
                if (tempSumList.size() == i) {
                    MultiSubjectSummary summary2 = new FourthFormMultiSubjectSummary(tempSumList, objects1, objects2);
                    summaries.add(summary2);
                    for (Quantifier quantifier : quantifiers) {
                        if (quantifier.getClass().equals(AbsoluteQuantifier.class)) {
                            continue;
                        }
                        MultiSubjectSummary summary1 = new FirstFormMultiSubjectSummary(quantifier, tempSumList, objects1, objects2);
                        summaries.add(summary1);
                        generateSecondAndThirdForm(quantifier, qualifiers, tempSumList, objects1, objects2);
                    }
                }
            }
            if (i == 2 && summarizers.size() == 3) {
                List<Label> tempSumList = new ArrayList<>();
                tempSumList.add(summarizers.get(0));
                tempSumList.add(summarizers.get(2));
                MultiSubjectSummary summary2 = new FourthFormMultiSubjectSummary(tempSumList, objects1, objects2);
                summaries.add(summary2);
                for (Quantifier quantifier : quantifiers) {
                    if (quantifier.getClass().equals(AbsoluteQuantifier.class)) {
                        continue;
                    }
                    MultiSubjectSummary summary1 = new FirstFormMultiSubjectSummary(quantifier, tempSumList, objects1, objects2);
                    summaries.add(summary1);
                    generateSecondAndThirdForm(quantifier, qualifiers, tempSumList, objects1, objects2);
                }
            }
        }
    }





    private void generateSecondAndThirdForm(Quantifier quantifier, List<Label> qualifiers,
                                            List<Label> tempSumList, List<PowerliftingResult> objects1,
                                            List<PowerliftingResult> objects2) {
        for (int i = 1; i < qualifiers.size() + 1; i++) {
            for (int j = 0; j < qualifiers.size(); j++) {
                List<Label> tempQuaList = new ArrayList<>();
                if (j + i - 1 < qualifiers.size()) {
                    for (int k = j; k < j + i; k++) {
                        tempQuaList.add(qualifiers.get(k));
                    }
                }
                if (tempQuaList.size() == i) {
                    MultiSubjectSummary summary1 = new SecondFormMultiSubjectSummary(quantifier, tempSumList, tempQuaList, objects1, objects2);
                    MultiSubjectSummary summary2 = new ThirdFormMultiSubjectSummary(quantifier, tempSumList, tempQuaList, objects1, objects2);
                    summaries.add(summary1);
                    summaries.add(summary2);
                }
            }
            if (i == 2 && qualifiers.size() == 3) {
                List<Label> tempQuaList = new ArrayList<>();
                tempQuaList.add(qualifiers.get(0));
                tempQuaList.add(qualifiers.get(2));
                MultiSubjectSummary summary1 = new SecondFormMultiSubjectSummary(quantifier, tempSumList, tempQuaList, objects1, objects2);
                MultiSubjectSummary summary2 = new ThirdFormMultiSubjectSummary(quantifier, tempSumList, tempQuaList, objects1, objects2);
                summaries.add(summary1);
                summaries.add(summary2);
            }
        }
    }



    @FXML
    void backToMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ksr2/ksrproject2/app-view.fxml"));
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
    void goToAdvancedUserPanel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ksr2/ksrproject2/editPanel-view.fxml"));
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
    void saveSummaryToFile(ActionEvent event) {
        summaries.sort(Comparator.comparing(MultiSubjectSummary::getDegreeOfTruthT1).reversed());
        try (FileWriter writer = new FileWriter("summaries-multi.txt")) {
            for (MultiSubjectSummary summary : summaries) {
                writer.write(summary.toString() + " ");
                writer.write("[T1: " + summary.getDegreeOfTruthT1() +"] ");
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private Label findLabel(String variableName, String labelName) {
        for (LinguisticVariable var : Data.linguisticVariables) {
            if (var.getName().equals(variableName)) {
                for (Label label : var.getLabels()) {
                    if (label.getName().equals(labelName)) {
                        return label;
                    }
                }
            }
        }
        return null;
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

    static class CenteredTableCell<T, S> extends TextFieldTableCell<T, S> {
        public CenteredTableCell() {
            super();
            setAlignment(Pos.CENTER);
        }
    }

}
