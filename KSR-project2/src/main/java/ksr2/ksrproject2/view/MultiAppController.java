package ksr2.ksrproject2.view;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.control.cell.TextFieldTableCell;
import ksr2.ksrproject2.logic.summarization.Label;
import ksr2.ksrproject2.logic.summarization.LinguisticVariable;
import ksr2.ksrproject2.logic.summarization.forms.multiForms.MultiSubjectSummary;
import ksr2.ksrproject2.view.model.MultiSubjectSummaryDTO;
import ksr2.ksrproject2.view.model.SubjectType;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
        CheckBoxTreeItem<String> root = new CheckBoxTreeItem<>("Summary");
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
    static class CenteredTableCell<T, S> extends TextFieldTableCell<T, S> {
        public CenteredTableCell() {
            super();
            setAlignment(Pos.CENTER);
        }
    }
}
