package ksr2.ksrproject2.view;


import ksr2.ksrproject2.logic.calculation.membershipFunctions.GaussianFunction;
import ksr2.ksrproject2.logic.calculation.membershipFunctions.TrapezoidalFunction;
import ksr2.ksrproject2.logic.calculation.membershipFunctions.TriangularFunction;
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

        //squat
        Label lessThan3times = new Label("less than 3 times bodyweight score in squat", new FuzzySet(new TrapezoidalFunction(0, 0, 2, 3), new ContinuousSet(0, 3)), "Squat-strenght-level");
        Label about3times = new Label("about 3 times bodyweight score in squat", new FuzzySet(new TriangularFunction( 2.2, 2.5, 3.5), new ContinuousSet(2.2, 3.5)), "Squat-strenght-level");
        Label beetwen2and4 = new Label("beetwen 2 and 4 times bodyweight score in squat", new FuzzySet(new TrapezoidalFunction( 2, 2.5,3.5, 5), new ContinuousSet(2, 5)), "Squat-strenght-level");
        Label about4times = new Label("about 4 times bodyweight score in squat", new FuzzySet(new TriangularFunction( 3.7, 4, 4.8), new ContinuousSet(3.7, 4.8)), "Squat-strenght-level");
        Label moreThan4_5times = new Label("more than 4.5 times bodyweight score in squat", new FuzzySet(new TrapezoidalFunction( 4, 5, 6, 6), new ContinuousSet(4, 6)), "Squat-strenght-level");
        List<Label> squatLabels = new ArrayList<>(List.of(lessThan3times, about3times, beetwen2and4, about4times, moreThan4_5times));
        LinguisticVariable squat = new LinguisticVariable("Squat-strenght-level", squatLabels, new ContinuousSet(0, 6));

        //bench press
        Label lessThan2times = new Label("less than 2 times bodyweight score in benchpress", new FuzzySet(new TrapezoidalFunction(0.1, 0.1, 1, 2), new ContinuousSet(0.1, 2)), "Benchpress-strenght-level");
        Label about2times = new Label("about 2 times bodyweight score in benchpress", new FuzzySet(new TrapezoidalFunction( 1.7, 2, 2.5,3), new ContinuousSet(1.7, 3)), "Benchpress-strenght-level");
        Label about2_5times = new Label("about 2.5 times bodyweight score in benchpress", new FuzzySet(new TriangularFunction( 2.2, 2.5, 3.0), new ContinuousSet(2.2, 3.0)), "Benchpress-strenght-level");
        Label moreThan2_5times = new Label("more than 2.5 times bodyweight score in benchpress", new FuzzySet(new TrapezoidalFunction( 2.5, 3, 3.3,3.3), new ContinuousSet(2.5, 3.3)), "Benchpress-strenght-level");
        List<Label> benchpressLabels = new ArrayList<>(List.of(lessThan2times, about2times, about2_5times, moreThan2_5times));
        LinguisticVariable benchpress = new LinguisticVariable("Benchpress-strenght-level", benchpressLabels, new ContinuousSet(0.1, 3.3));

        //deadlift
        Label lessThan3timesDeadlift = new Label("less than 3 times bodyweight score in deadlift", new FuzzySet(new TrapezoidalFunction(0.3, 0.3, 2, 3), new ContinuousSet(0.3, 3)), "Deadlift-strenght-level");
        Label about3timesDeadlift = new Label("about 3 times bodyweight score in deadlift", new FuzzySet(new TriangularFunction( 2.5, 3, 3.5), new ContinuousSet(2.5, 3.5)), "Deadlift-strenght-level");
        Label beetwen2and4Deadlift = new Label("beetwen 2 and 4 times bodyweight score in deadlift", new FuzzySet(new TrapezoidalFunction( 1.8,2,3.5, 4), new ContinuousSet(1.8, 4)), "Deadlift-strenght-level");
        Label moreThan3_5timesDeadlift = new Label("more than 3.5 times bodyweight score in deadlift", new FuzzySet(new TrapezoidalFunction( 3.5, 4, 4.7, 4.7), new ContinuousSet(3.5, 4.7)), "Deadlift-strenght-level");
        List<Label> deadliftLabels = new ArrayList<>(List.of(lessThan3timesDeadlift, about3timesDeadlift, beetwen2and4Deadlift, moreThan3_5timesDeadlift));
        LinguisticVariable deadlift = new LinguisticVariable("Deadlift-strenght-level", deadliftLabels, new ContinuousSet(0.3, 4.7));

        //total
        Label beginnerTotal = new Label("beginner total powerlifgting score", new FuzzySet(new TrapezoidalFunction(1.4, 1.4, 3.2, 4.2), new ContinuousSet(1.4, 4.2)), "Total-powerlift-strenght-level");
        Label intermediateTotal = new Label("intermediate total powerlifgting score", new FuzzySet(new TrapezoidalFunction(3.6, 4, 5, 5.4), new ContinuousSet(3.6, 5.4)), "Total-powerlift-strenght-level");
        Label advancedTotal = new Label("advanced total powerlifgting score", new FuzzySet(new TrapezoidalFunction(5, 5.5, 6.4, 7.0), new ContinuousSet(5, 7)), "Total-powerlift-strenght-level");
        Label eliteTotal = new Label("elite total powerlifgting score", new FuzzySet(new TrapezoidalFunction(6, 7.3, 11.6, 11.6), new ContinuousSet(6, 11.6)), "Total-powerlift-strenght-level");
        List<Label> totalLabels = new ArrayList<>(List.of(beginnerTotal, intermediateTotal, advancedTotal, eliteTotal));
        LinguisticVariable total = new LinguisticVariable("Total-powerlift-strenght-level", totalLabels, new ContinuousSet(1.4, 11.6));

        //dots
        Label beginnerDots = new Label("beginner dots level", new FuzzySet(new TrapezoidalFunction(81, 81, 180, 220), new ContinuousSet(81, 220)), "Dots-level");
        Label intermediateDots = new Label("intermediate dots level", new FuzzySet(new TrapezoidalFunction(170, 220, 300, 340), new ContinuousSet(170, 340)), "Dots-level");
        Label advancedDots = new Label("advanced dots level", new FuzzySet(new TrapezoidalFunction(300, 340, 440, 460), new ContinuousSet(300, 460)), "Dots-level");
        Label masterDots = new Label("master dots level", new FuzzySet(new TrapezoidalFunction(430, 480, 520, 560), new ContinuousSet(430, 560)), "Dots-level");
        Label eliteDots = new Label("elite dots level", new FuzzySet(new TrapezoidalFunction(500, 580, 704, 704), new ContinuousSet(500, 704)), "Dots-level");
        List<Label> dotsLabels = new ArrayList<>(List.of(beginnerDots, intermediateDots, advancedDots, masterDots, eliteDots));
        LinguisticVariable dots = new LinguisticVariable("Dots-level", dotsLabels, new ContinuousSet(81, 704));

        //wilks
        Label lessThen300 = new Label("less then 300 wilks score", new FuzzySet(new TrapezoidalFunction(83, 83, 200, 350), new ContinuousSet(83, 350)), "Wilks-score");
        Label around250 = new Label("around 250 wilks score", new FuzzySet(new TriangularFunction(220, 250, 320), new ContinuousSet(220, 320)), "Wilks-score");
        Label beetween400and600 = new Label("beetween 400 and 600 wilks score", new FuzzySet(new TrapezoidalFunction(300, 400, 550, 600), new ContinuousSet(300, 640)), "Wilks-score");
        Label moreThen500 = new Label("more then 500 wilks score", new FuzzySet(new TrapezoidalFunction(400, 500, 700, 700), new ContinuousSet(400, 700)), "Wilks-score");
        List<Label> wilksLabels = new ArrayList<>(List.of(lessThen300, around250, beetween400and600, moreThen500));
        LinguisticVariable wilks = new LinguisticVariable("Wilks-score", wilksLabels, new ContinuousSet(83, 700));

        //glossbrenner
        Label begginerGlossbrenner = new Label("begginer glossbrenner level", new FuzzySet(new TrapezoidalFunction(80, 80, 140, 180), new ContinuousSet(80, 180)), "Glossbrenner-level");
        Label intermediateGlossbrenner = new Label("intermediate glossbrenner level", new FuzzySet(new TrapezoidalFunction(150, 200, 240, 300), new ContinuousSet(150, 300)), "Glossbrenner-level");
        Label advancedGlossbrenner = new Label("advanced glossbrenner level", new FuzzySet(new TrapezoidalFunction(270, 320, 390, 440), new ContinuousSet(270, 440)), "Glossbrenner-level");
        Label masterGlossbrenner = new Label("master glossbrenner level", new FuzzySet(new TrapezoidalFunction(410, 450, 500, 550), new ContinuousSet(410, 550)), "Glossbrenner-level");
        Label eliteGlossbrenner = new Label("elite glossbrenner level", new FuzzySet(new TrapezoidalFunction(520, 570, 670, 670), new ContinuousSet(520, 670)), "Glossbrenner-level");
        List<Label> glossbrennerLabels = new ArrayList<>(List.of(begginerGlossbrenner, intermediateGlossbrenner, advancedGlossbrenner, masterGlossbrenner, eliteGlossbrenner));
        LinguisticVariable glossbrenner = new LinguisticVariable("Glossbrenner-level", glossbrennerLabels, new ContinuousSet(80, 670));

        //goodlift
        Label begginerGoodlift = new Label("begginer goodlift level", new FuzzySet(new TrapezoidalFunction(15, 15, 25, 35), new ContinuousSet(15, 35)), "Goodlift-level");
        Label intermediateGoodlift = new Label("intermediate goodlift level", new FuzzySet(new TrapezoidalFunction(30, 35, 50, 60), new ContinuousSet(30, 60)), "Goodlift-level");
        Label advancedGoodlift = new Label("advanced goodlift level", new FuzzySet(new TrapezoidalFunction(55, 60, 75, 90), new ContinuousSet(55, 90)), "Goodlift-level");
        Label eliteGoodlift = new Label("elite goodlift level", new FuzzySet(new TrapezoidalFunction(75, 100, 120, 120), new ContinuousSet(75, 120)), "Goodlift-level");
        List<Label> goodliftLabels = new ArrayList<>(List.of(begginerGoodlift, intermediateGoodlift, advancedGoodlift, eliteGoodlift));
        LinguisticVariable goodlift = new LinguisticVariable("Goodlift-level", goodliftLabels, new ContinuousSet(15, 120));

        //zmienne ligwistyczne
        linguisticVariables = new ArrayList<>();
        linguisticVariables.addAll((new ArrayList<>(List.of(age, weight, squat, benchpress, deadlift, total, dots, wilks, glossbrenner, goodlift))));

        //kwantyfikatory wzgledne
        RelativeQuantifier almostNone = new RelativeQuantifier("Almost none", new FuzzySet(new TrapezoidalFunction(0, 0, 0.04, 0.16), new ContinuousSet(0, 0.16)));
        RelativeQuantifier some = new RelativeQuantifier("Some", new FuzzySet(new TrapezoidalFunction(0.12, 0.16, 0.32, 0.4), new ContinuousSet(0.12, 0.4)));
        RelativeQuantifier aboutHalf = new RelativeQuantifier("About half", new FuzzySet(new TrapezoidalFunction(0.32, 0.44, 0.56, 0.68), new ContinuousSet(0.32, 0.68)));
        RelativeQuantifier many = new RelativeQuantifier("Many", new FuzzySet(new TrapezoidalFunction(0.6, 0.68, 0.84, 0.88), new ContinuousSet(0.6, 0.88)));
        RelativeQuantifier almostAll = new RelativeQuantifier("Almost all", new FuzzySet(new TrapezoidalFunction(0.84, 0.96, 1, 1), new ContinuousSet(0.84, 1)));
        relativeQuantifiers = new ArrayList<>();
        relativeQuantifiers.addAll((new ArrayList<>(List.of(almostNone, some, aboutHalf, many, almostAll))));

        //kwantyfikatory absolutne
        AbsoluteQuantifier farBelow1000 = new AbsoluteQuantifier("Far below 1000", new FuzzySet(new TrapezoidalFunction(0, 0, 600, 1000), new ContinuousSet(0, 1000)));
        AbsoluteQuantifier around2000 = new AbsoluteQuantifier("Around 2000", new FuzzySet(new GaussianFunction(2000, 500, 500, 3500), new ContinuousSet(500, 3500)));
        AbsoluteQuantifier from3000to6000 = new AbsoluteQuantifier("From 3000 to 6000", new FuzzySet(new TrapezoidalFunction(2700, 3000, 6000, 6300), new ContinuousSet(2700, 6300)));
        AbsoluteQuantifier around7000 = new AbsoluteQuantifier("Around 7000", new FuzzySet(new GaussianFunction(7000, 500, 5500, 8500), new ContinuousSet(5500, 8500)));
        AbsoluteQuantifier muchMoreThan8000 = new AbsoluteQuantifier("Much more than 8000", new FuzzySet(new TrapezoidalFunction(7900, 8400, 12000, 12000), new ContinuousSet(7900, 12000)));
        absoluteQuantifiers = new ArrayList<>();
        absoluteQuantifiers.addAll((new ArrayList<>(List.of(farBelow1000, around2000, from3000to6000, around7000, muchMoreThan8000))));
    }
}
