package ksr2.ksrproject2.logic.summarization.forms;

import ksr2.ksrproject2.logic.model.PowerliftingResult;
import ksr2.ksrproject2.logic.summarization.Label;
import ksr2.ksrproject2.logic.summarization.Quantifier;

import java.util.List;

public class SecondFormMultiSubjectSummary implements MultiSubjectSummary {
    private final Quantifier quantifier;
    private final List<Label> summarizers;
    private final List<Label> qualifiers;
    private final List<PowerliftingResult> subject1;
    private final List<PowerliftingResult> subject2;

    public SecondFormMultiSubjectSummary(Quantifier quantifier, List<Label> summarizers, List<Label> qualifiers, List<PowerliftingResult> subject1, List<PowerliftingResult> subject2) {
        this.quantifier = quantifier;
        this.summarizers = summarizers;
        this.qualifiers = qualifiers;
        this.subject1 = subject1;
        this.subject2 = subject2;
    }

    @Override
    public double getDegreeOfTruthT1() {
        return 0;
    }

    @Override
    public String toString() {
        return "SecondFormMultiSubjectSummary{" +
                "quantifier=" + quantifier +
                ", summarizers=" + summarizers +
                ", qualifiers=" + qualifiers +
                ", detalis1=" + subject1 +
                ", detalis2=" + subject2 +
                '}';
    }



}
