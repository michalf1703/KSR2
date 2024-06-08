package ksr2.ksrproject2.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ksr2.ksrproject2.logic.calculation.membershipFunctions.GaussianFunction;
import ksr2.ksrproject2.logic.calculation.membershipFunctions.MembershipFunction;
import ksr2.ksrproject2.logic.calculation.membershipFunctions.TrapezoidalFunction;
import ksr2.ksrproject2.logic.calculation.membershipFunctions.TriangularFunction;
import ksr2.ksrproject2.logic.calculation.sets.ContinuousSet;
import ksr2.ksrproject2.logic.calculation.sets.FuzzySet;
import ksr2.ksrproject2.logic.summarization.AbsoluteQuantifier;
import ksr2.ksrproject2.logic.summarization.Label;
import ksr2.ksrproject2.logic.summarization.LinguisticVariable;
import ksr2.ksrproject2.logic.summarization.RelativeQuantifier;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EditPanelView implements Initializable {
    @FXML
    private ComboBox<String> labelTypeCB;
    @FXML
    private Text objectTypeText;
    @FXML
    private ComboBox<String> optionCB;
    @FXML
    private ComboBox<String> functionCB;
    @FXML
    private AnchorPane membershipFunctionPane;
    @FXML
    private TextField labelNameTF;
    @FXML
    private Text aText;
    @FXML
    private Text bText;
    @FXML
    private Text cText;
    @FXML
    private Text dText;
    @FXML
    private TextField aTF;
    @FXML
    private TextField bTF;
    @FXML
    private TextField cTF;
    @FXML
    private TextField dTF;
    @FXML
    private StackPane stackPaneChart;

    private LineChart<Number, Number> lineChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        membershipFunctionPane.setVisible(true);
        dText.setVisible(false);
        dTF.setVisible(false);
        labelTypeCB.getItems().addAll(List.of("Quantifier", "Linguistic Variable"));
        functionCB.getItems().addAll(List.of("Triangular", "Trapezoidal", "Gaussian"));
    }

    @FXML
    private void labelTypeCB_onAction() {
        optionCB.getItems().clear();
        String value = labelTypeCB.getValue();
        if ("Quantifier".equals(value)) {
            objectTypeText.setText("Typ kwantyfikatora");
            optionCB.getItems().addAll(List.of("Absolute", "Relative"));
        } else if ("Linguistic Variable".equals(value)) {
            objectTypeText.setText("Linguistic Variable");
            optionCB.getItems().addAll(Data.linguisticVariables.stream().map(LinguisticVariable::getName).collect(Collectors.toList()));
        }
    }

    @FXML
    private void functionCB_onAction() {
        String value = functionCB.getValue();
        if ("Triangular".equals(value)) {
            aText.setText("Point A");
            bText.setText("Point B");
            cText.setText("Point C");
            dText.setVisible(false);
            dTF.setVisible(false);
        } else if ("Trapezoidal".equals(value)) {
            aText.setText("Point A");
            bText.setText("Point B");
            cText.setText("Point C");
            dText.setText("Point D");
            dText.setVisible(true);
            dTF.setVisible(true);
        } else if ("Gaussian".equals(value)) {
            aText.setText("Center");
            bText.setText("Standard deviation");
            cText.setText("Left limit");
            dText.setText("Right limit");
            dText.setVisible(true);
            dTF.setVisible(true);
        }
        membershipFunctionPane.setVisible(true);
    }

    @FXML
    private void addNewLabel_onAction() {
        MembershipFunction function;
        String functionValue = functionCB.getValue();
        if ("Triangular".equals(functionValue)) {
            function = new TriangularFunction(Double.parseDouble(aTF.getText()), Double.parseDouble(bTF.getText()), Double.parseDouble(cTF.getText()));
        } else if ("Trapezoidal".equals(functionValue)) {
            function = new TrapezoidalFunction(Double.parseDouble(aTF.getText()), Double.parseDouble(bTF.getText()), Double.parseDouble(cTF.getText()), Double.parseDouble(dTF.getText()));
        } else if ("Gaussian".equals(functionValue)) {
            function = new GaussianFunction(Double.parseDouble(aTF.getText()), Double.parseDouble(bTF.getText()), Double.parseDouble(cTF.getText()), Double.parseDouble(dTF.getText()));
        } else {
            throw new IllegalStateException("Unexpected value: " + functionCB.getValue());
        }

        String labelTypeValue = labelTypeCB.getValue();
        if ("Linguistic Variable".equals(labelTypeValue)) {
            LinguisticVariable variable = Data.linguisticVariables.stream()
                    .filter(var -> var.getName().equals(optionCB.getValue()))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("No value present"));

            ContinuousSet tempSet = ((ContinuousSet) variable.getLabels().get(0).getFuzzySet().getUniverseOfDiscourse());
            FuzzySet fuzzySet = new FuzzySet(function, new ContinuousSet(tempSet.getStartOfUniverseOFDiscourse(), tempSet.getEndOfUniverseOFDiscourse()));
            Label label = new Label(labelNameTF.getText(), fuzzySet, variable.getName());

            variable.getLabels().add(label);
        } else if ("Quantifier".equals(labelTypeValue)) {
            if (optionCB.getValue().equals("Absolute")) {
                ContinuousSet tempSet = (ContinuousSet) Data.absoluteQuantifiers.get(0).getFuzzySet().getUniverseOfDiscourse();
                FuzzySet fuzzySet = new FuzzySet(function, new ContinuousSet(tempSet.getStartOfUniverseOFDiscourse(), tempSet.getStartOfUniverseOFDiscourse()));
                AbsoluteQuantifier quantifier = new AbsoluteQuantifier(labelNameTF.getText(), fuzzySet);
                Data.absoluteQuantifiers.add(quantifier);
            } else {
                ContinuousSet tempSet = (ContinuousSet) Data.relativeQuantifiers.get(0).getFuzzySet().getUniverseOfDiscourse();
                FuzzySet fuzzySet = new FuzzySet(function, new ContinuousSet(tempSet.getStartOfUniverseOFDiscourse(), tempSet.getEndOfUniverseOFDiscourse()));
                RelativeQuantifier quantifier = new RelativeQuantifier(labelNameTF.getText(), fuzzySet);
                Data.relativeQuantifiers.add(quantifier);
            }
        }
        showChart();
        showInfoAlert(labelNameTF.getText() + " został dodany");
    }

    private void showInfoAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        alert.showAndWait();
    }

    public void showChart() {
        if (lineChart != null) {
            stackPaneChart.getChildren().clear();
        }

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        lineChart = new LineChart<>(xAxis, yAxis);
        XYChart.Series<Number, Number> series = new XYChart.Series<>();

        if (optionCB.getValue().equals("Absolute")) {
            xAxis.setLowerBound(0);
            xAxis.setUpperBound(Data.powerliftingResults.size());
        } else if (optionCB.getValue().equals("Relative")) {
            xAxis.setLowerBound(0);
            xAxis.setUpperBound(1);
        } else {
            LinguisticVariable variable = Data.linguisticVariables.stream()
                    .filter(var -> var.getName().equals(optionCB.getValue()))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("No value present"));

            ContinuousSet tempSet = ((ContinuousSet) variable.getLabels().get(0).getFuzzySet().getUniverseOfDiscourse());
            xAxis.setLowerBound(tempSet.getStartOfUniverseOFDiscourse());
            xAxis.setUpperBound(tempSet.getEndOfUniverseOFDiscourse());
        }

        lineChart.setTitle("Membership function");
        xAxis.setLabel(labelNameTF.getText());
        xAxis.setAutoRanging(false);
        series.setName("Membership function");

        double a, b, c;
        a = Double.parseDouble(aTF.getText());
        b = Double.parseDouble(bTF.getText());
        c = Double.parseDouble(cTF.getText());

        String functionValue = functionCB.getValue();
        if ("Triangular".equals(functionValue)) {
            series.getData().add(new XYChart.Data<>(a, 0));
            series.getData().add(new XYChart.Data<>(b, 1));
            series.getData().add(new XYChart.Data<>(c, 0));
        } else if ("Trapezoidal".equals(functionValue)) {
            double d = Double.parseDouble(dTF.getText());
            series.getData().add(new XYChart.Data<>(a, 0));
            series.getData().add(new XYChart.Data<>(b, 1));
            series.getData().add(new XYChart.Data<>(c, 1));
            series.getData().add(new XYChart.Data<>(d, 0));
        } else if ("Gaussian".equals(functionValue)) {
            double d = Double.parseDouble(dTF.getText());
            lineChart.setCreateSymbols(false);
            double x = c;
            double step = (d-c) / 100.0;
            GaussianFunction function = new GaussianFunction(a, b, c, d);
            while (x <= d) {
                double val = function.getValue(x);
                series.getData().add(new XYChart.Data<>(x, val));
                x += step;
            }
        }
        lineChart.getData().add(series);
        stackPaneChart.getChildren().add(lineChart);
        StackPane.setAlignment(lineChart, Pos.CENTER);
    }

    @FXML
    private void generateNewLabel_onAction() {
        showChart();
    }

    @FXML
    void onReturnToMainPage(ActionEvent event) {
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

}