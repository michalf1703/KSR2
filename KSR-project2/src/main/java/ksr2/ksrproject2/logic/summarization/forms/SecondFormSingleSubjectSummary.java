package ksr2.ksrproject2.logic.summarization.forms;

import ksr2.ksrproject2.logic.model.PowerliftingResultDetalis;
import ksr2.ksrproject2.logic.summarization.Label;
import ksr2.ksrproject2.logic.summarization.MeasureWeight;
import ksr2.ksrproject2.logic.summarization.Quantifier;
import ksr2.ksrproject2.logic.summarization.SingleSubjectSummary;

import java.util.List;

public class SecondFormSingleSubjectSummary implements SingleSubjectSummary {
    private final MeasureWeight measureWeight;
    private final Quantifier quantifier;
    private final List<Label> qualifiers;
    private final List<Label> summarizers;
    private final List<PowerliftingResultDetalis> subject;


    public SecondFormSingleSubjectSummary(MeasureWeight measureWeight, Quantifier quantifier, List<Label> qualifiers, List<Label> summarizers, List<PowerliftingResultDetalis> subject) {
        this.measureWeight = measureWeight;
        this.quantifier = quantifier;
        this.qualifiers = qualifiers;
        this.summarizers = summarizers;
        this.subject = subject;
    }

    @Override
    public double getOptimalSummary() {
        return 0;
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
                "measureWeight=" + measureWeight +
                ", quantifier=" + quantifier +
                ", qualifiers=" + qualifiers +
                ", summarizers=" + summarizers +
                ", detalis=" + subject +
                '}';
    }
}
