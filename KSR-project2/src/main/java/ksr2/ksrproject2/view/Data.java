package ksr2.ksrproject2.view;


import ksr2.ksrproject2.logic.calculation.membershipFunctions.TrapezoidalFunction;
import ksr2.ksrproject2.logic.calculation.sets.ContinuousSet;
import ksr2.ksrproject2.logic.calculation.sets.FuzzySet;
import ksr2.ksrproject2.logic.model.PowerliftingResult;
import ksr2.ksrproject2.logic.summarization.*;
import ksr2.ksrproject2.logic.utilis.DetalisReader;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class Data {
    public static List<PowerliftingResult> powerliftingResults;
    public static MeasureWeights measureWeights;
    public static List<LinguisticVariable> linguisticVariables;
    public static List<RelativeQuantifier> relativeQuantifiers;
    public static List<AbsoluteQuantifier> absoluteQuantifiers;

    public static void initData() {
        powerliftingResults = DetalisReader.readData();
        Map<String, Double> measures = new HashMap<>();
        measures.put("T1", 0.5);
        measures.put("T2", 0.05);
        measures.put("T3", 0.05);
        measures.put("T4", 0.05);
        measures.put("T5", 0.05);
        measures.put("T6", 0.05);
        measures.put("T7", 0.05);
        measures.put("T8", 0.05);
        measures.put("T9", 0.05);
        measures.put("T10", 0.05);
        measures.put("T11", 0.05);
        measureWeights = new MeasureWeights(measures);

        //age
        Label junior = new Label("junior age", new FuzzySet(new TrapezoidalFunction(6, 6, 16, 23), new ContinuousSet(6, 23)), "Age");
        Label senior = new Label("senior age", new FuzzySet(new TrapezoidalFunction(20, 25, 35, 40), new ContinuousSet(20, 40)), "Age");
        Label master = new Label("master age", new FuzzySet(new TrapezoidalFunction(38, 45, 89, 89), new ContinuousSet(35, 89)), "Age");
        List<Label> ageLabels = new ArrayList<>(List.of(junior, senior, master));
        LinguisticVariable age = new LinguisticVariable("Age", ageLabels, new ContinuousSet(6, 89));

        //weight
        Label lightWeight = new Label("light-weight", new FuzzySet(new TrapezoidalFunction(35, 35, 65, 80), new ContinuousSet(35, 80)), "Weight");
        Label middleWeight = new Label("middle-weight", new FuzzySet(new TrapezoidalFunction(70, 80, 90, 100), new ContinuousSet(70, 100)), "Weight");
        Label heavyWeight = new Label("heavy-weight", new FuzzySet(new TrapezoidalFunction(90, 100, 130, 140), new ContinuousSet(90, 140)), "Weight");
        Label superHeavyWeight = new Label("superheavy-weight", new FuzzySet(new TrapezoidalFunction(130, 140, 220, 220), new ContinuousSet(130, 220)), "Weight");
        List<Label> weightLabels = new ArrayList<>(List.of(lightWeight, middleWeight, heavyWeight, superHeavyWeight));
        LinguisticVariable weight = new LinguisticVariable("Weight", weightLabels, new ContinuousSet(35, 220));

        //
    }
}
