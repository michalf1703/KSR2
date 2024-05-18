package ksr2.ksrproject2.logic.summarization.forms;

import ksr2.ksrproject2.logic.model.PowerliftingResultDetalis;
import ksr2.ksrproject2.logic.summarization.Label;
import ksr2.ksrproject2.logic.summarization.MultiSubjectSummary;
import ksr2.ksrproject2.logic.summarization.Quantifier;

import java.util.List;

public class ThirdFormMultiSubjectSummary implements MultiSubjectSummary {

    private final Quantifier quantifier;
    private final List<Label> summarizers;
    private final List<Label> qualifiers;
    private final List<PowerliftingResultDetalis> detalis1;
    private final List<PowerliftingResultDetalis> detalis2;

    public ThirdFormMultiSubjectSummary(Quantifier quantifier, List<Label> summarizers, List<Label> qualifiers, List<PowerliftingResultDetalis> detalis1, List<PowerliftingResultDetalis> detalis2) {
        this.quantifier = quantifier;
        this.summarizers = summarizers;
        this.qualifiers = qualifiers;
        this.detalis1 = detalis1;
        this.detalis2 = detalis2;
    }

    @Override
    public double getDeegreeOfTruthT1() {
        return 0;
    }

    @Override
    public String toString() {
        return "ThirdFormMultiSubjectSummary{" +
                "quantifier=" + quantifier +
                ", summarizers=" + summarizers +
                ", qualifiers=" + qualifiers +
                ", detalis1=" + detalis1 +
                ", detalis2=" + detalis2 +
                '}';
    }

}
