package ksr2.ksrproject2.logic.summarization.forms;

import ksr2.ksrproject2.logic.model.PowerliftingResult;
import ksr2.ksrproject2.logic.summarization.Label;
import ksr2.ksrproject2.logic.summarization.RelativeQuantifier;

import java.util.List;
import java.util.Map;

public class SecondFormSingleSubjectSummary implements SingleSubjectSummary {
    private final Map<String, Double> weights;
    private final RelativeQuantifier quantifier;
    private final List<Label> qualifiers;
    private final List<Label> summarizers;
    private final List<PowerliftingResult> subject;

    public SecondFormSingleSubjectSummary(Map<String, Double> weights, RelativeQuantifier quantifier, List<Label> qualifiers, List<Label> summarizers, List<PowerliftingResult> subject) {
        this.weights = weights;
        this.quantifier = quantifier;
        this.qualifiers = qualifiers;
        this.summarizers = summarizers;
        this.subject = subject;
    }


    @Override
    public double getOptimalSummary() {
        Map<String, Double> measures = calculateMeasures();
        return measures.entrySet().stream().mapToDouble(e -> e.getValue() * weights.get(e.getKey())).sum();
    }

  public double getDegreeOfTruth_T1() {
        return 0;
    }

    public double getDegreeOfImprecision_T2() {
        return 0;
    }


    public double getDegreeOfCovering_T3() {
        return 0;
    }

    public double getDegreeOfAppropriateness_T4() {
        return 0;
    }

    public double getDegreeOfSummary_T5() {
        return 0;
    }

    public double getDegreeOfQuantifierImprecision_T6() {
        return 0;
    }


    public double getDegreeOfQuantifierCardinality_T7() {
        return 0;
    }

    public double getDegreeOfSummarizerCardinality_T8() {
        return 0;
    }

    public double getDegreeOfQualifierImprecision_T9() {
        return 0;
    }

    public double getDegreeOfQualifierCardinality_T10() {
        return 0;
    }

    public double getLengthOfQualifier_T11() {
        return 0;
    }

    @Override
    public String toString() {
        return "SecondFormSingleSubjectSummary{" +
                "measureWeight=" +
                ", quantifier=" + quantifier +
                ", qualifiers=" + qualifiers +
                ", summarizers=" + summarizers +
                ", detalis=" + subject +
                '}';
    }
}
