package ksr2.ksrproject2.logic.summarization.forms.multiForms;


import ksr2.ksrproject2.logic.model.PowerliftingResult;
import ksr2.ksrproject2.logic.summarization.Label;

import java.util.List;

public interface MultiSubjectSummary {

    default double fieldForLabel(Label l, PowerliftingResult result) {
        switch (l.getLinguisticVariableName()) {
            case "Age":
                return result.getAge();
            case "Weight":
                return result.getBodyWeight();
            case "Squat-strenght-level":
                return result.getSquatStrenghtLevel();
            case "Benchpress-strenght-level":
                return result.getBenchpressStrenghtLevel();
            case "Deadlift-strenght-level":
                return result.getDeadliftStrenghtLevel();
            case "Total-powerlift-strenght-level":
                return result.getTotalPowerliftStrenghtLevel();
            case "Dots-level":
                return result.getDotsLevel();
            case "Wilks-score":
                return result.getWilksLevel();
            case "Glossbrenner-level":
                return result.getGlossbrennerLevel();
            case "Goodlift-level":
                return result.getGoodliftLevel();
            default:
                throw new IllegalStateException(String.format("Wrong label name: %s", l.getName()));
        }
    }


    double getDegreeOfTruthT1();

    default double and(List<Label> labels, PowerliftingResult car) {
        if (labels == null) {
            return 1;
        }
        double min = 1.0;
        for (Label label : labels) {
            double degreeOfMembership = label.getFuzzySet().getMembershipDegree(fieldForLabel(label, car));
            if (degreeOfMembership < min) {
                min = degreeOfMembership;
            }
        }
        return min;
    }

    default double nfSigmaCount(List<PowerliftingResult> powerlifts, List<Label> labels) {
        double result = 0.0;
        for (PowerliftingResult powerlift : powerlifts ) {
            result += and(labels, powerlift);
        }
        return result;
    }

    String toString();
}