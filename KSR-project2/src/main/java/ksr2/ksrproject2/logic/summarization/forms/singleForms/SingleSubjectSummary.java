package ksr2.ksrproject2.logic.summarization.forms.singleForms;

import ksr2.ksrproject2.logic.model.PowerliftingResult;
import ksr2.ksrproject2.logic.summarization.Label;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SingleSubjectSummary {




    default Map<String, Double> calculateMeasures() {
        Map<String, Double> measures = new HashMap<>();
        measures.put("T1", getDegreeOfTruth_T1());
        measures.put("T2", getDegreeOfImprecision_T2());
        measures.put("T3", getDegreeOfCovering_T3());
        measures.put("T4", getDegreeOfAppropriateness_T4());
        measures.put("T5", getDegreeOfSummary_T5());
        measures.put("T6", getDegreeOfQuantifierImprecision_T6());
        measures.put("T7", getDegreeOfQuantifierCardinality_T7());
        measures.put("T8", getDegreeOfSummarizerCardinality_T8());
        measures.put("T9", getDegreeOfQualifierImprecision_T9());
        measures.put("T10", getDegreeOfQualifierCardinality_T10());
        measures.put("T11", getLengthOfQualifier_T11());
        return measures;
    }

    double getMainSummaryMeasure();

    double getDegreeOfTruth_T1();

    double getDegreeOfImprecision_T2();

    double getDegreeOfCovering_T3();

    double getDegreeOfAppropriateness_T4();

    double getDegreeOfSummary_T5();

    double getDegreeOfQuantifierImprecision_T6();

    double getDegreeOfQuantifierCardinality_T7();

    double getDegreeOfSummarizerCardinality_T8();

    double getDegreeOfQualifierImprecision_T9();

    double getDegreeOfQualifierCardinality_T10();

    double getLengthOfQualifier_T11();

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

    default double and(List<Label> labels, PowerliftingResult record) {
        if (labels == null) {
            return 1.0;
        }
        return labels.stream().mapToDouble(label -> label.getFuzzySet().getMembershipDegree(fieldForLabel(label, record))).min().orElse(1);
    }

    String toString();
}

