package ksr2.ksrproject2.logic.summarization.forms;

import ksr2.ksrproject2.logic.model.PowerliftingResult;
import ksr2.ksrproject2.logic.summarization.Label;
import ksr2.ksrproject2.logic.summarization.Quantifier;

import java.util.List;
import java.util.Map;

public class FirstFormSingleSubjectSummary implements SingleSubjectSummary {
    private final Map<String, Double> weights;
    private final Quantifier quantifier;
    private final List<Label> summarizers;
    private final List<PowerliftingResult> subject;

    public FirstFormSingleSubjectSummary(Map<String, Double> weights, Quantifier quantifier, List<Label> summarizers, List<PowerliftingResult> subject) {
        this.weights = weights;
        this.quantifier = quantifier;
        this.summarizers = summarizers;
        this.subject = subject;
    }


    @Override
    public double getOptimalSummary() {
        Map<String, Double> measures = calculateMeasures();
        return measures.entrySet().stream().mapToDouble(e -> e.getValue() * weights.get(e.getKey())).sum();
    }
    @Override
    public double getDegreeOfTruth_T1() {
        return 0;
    }

    @Override
    public double getDegreeOfImprecision_T2() {
        return 0;
    }

    @Override
    public double getDegreeOfCovering_T3() {
        return 0;
    }

    @Override
    public double getDegreeOfAppropriateness_T4() {
        return 0;
    }

    @Override
    public double getDegreeOfSummary_T5() {
        return 0;
    }

    @Override
    public double getDegreeOfQuantifierImprecision_T6() {
        return 0;
    }

    @Override
    public double getDegreeOfQuantifierCardinality_T7() {
        return 0;
    }

    @Override
    public double getDegreeOfSummarizerCardinality_T8() {
        return 0;
    }

    @Override
    public double getDegreeOfQualifierImprecision_T9() {
        return 0;
    }

    @Override
    public double getDegreeOfQualifierCardinality_T10() {
        return 0;
    }

    @Override
    public double getLengthOfQualifier_T11() {
        return 0;
    }

    @Override
    public String toString() {
        return "FirstFormSingleSubjectSummary{" +
                "measureWeight="  +
                ", quantifier=" + quantifier +
                ", summarizers=" + summarizers +
                ", detalis=" + subject +
                '}';
    }

}
