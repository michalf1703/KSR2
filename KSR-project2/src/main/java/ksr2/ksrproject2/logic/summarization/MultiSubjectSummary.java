package ksr2.ksrproject2.logic.summarization;

import ksr2.ksrproject2.logic.model.PowerliftingResultDetalis;

import java.util.List;

public interface MultiSubjectSummary {
    double getDeegreeOfTruthT1();
    default double fieldForLabel(Label l, PowerliftingResultDetalis d) {
        switch (l.getLinguisticVariableName()) {
            case "age":
                return d.getAge();
            case "bodyWeight":
                return d.getBodyWeight();
            case "squatStrenghtLevel":
                return d.getSquatStrenghtLevel();
            case "benchpressStrenghtLevel":
                return d.getBenchpressStrenghtLevel();
            case "deadliftStrenghtLevel":
                return d.getDeadliftStrenghtLevel();
            case "totalPowerliftStrenghtLevel":
                return d.getTotalPowerliftStrenghtLevel();
            case "dotsLevel":
                return d.getDotsLevel();
            case "wilksLevel":
                return d.getWilksLevel();
            case "glossbrennerLevel":
                return d.getGlossbrennerLevel();
            case "goodliftLevel":
                return d.getGoodliftLevel();
            default:
                throw new IllegalStateException("Unexpected value: " + l.getLinguisticVariableName());
        }
    }

    default double and(List<Label> labels, PowerliftingResultDetalis d) {
        if (labels == null) {
            return 1;
        }
        double min = 1;
        for (Label l : labels) {
            double degreeOfMembership = l.getFuzzySet().getMembershipDegree(fieldForLabel(l, d));
            if (degreeOfMembership < min) {
                min = degreeOfMembership;
            }
        }
        return min;
    }

    default double nfSigmaCount(List<PowerliftingResultDetalis> detalis, List<Label> labels) {
        double sum = 0;
        for (PowerliftingResultDetalis d : detalis) {
            sum += and(labels, d);
        }
        return sum;
    }

    String toString();
}