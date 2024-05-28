package ksr2.ksrproject2.logic.summarization.forms.multiForms;

import ksr2.ksrproject2.logic.model.PowerliftingResult;
import ksr2.ksrproject2.logic.summarization.Label;

import java.util.List;

public class FourthFormMultiSubjectSummary implements MultiSubjectSummary {

    private final List<Label> summarizers;
    private final List<PowerliftingResult> subject1;
    private final List<PowerliftingResult> subject2;

    public FourthFormMultiSubjectSummary(List<Label> summarizers, List<PowerliftingResult> subject1, List<PowerliftingResult> subject2) {
        this.summarizers = summarizers;
        this.subject1 = subject1;
        this.subject2 = subject2;
    }

    @Override
    public double getDegreeOfTruthT1() {
        return 0;
    }

    @Override
    public String toString() {
        return "FourthFormMultiSubjectSummary{" +
                "summarizers=" + summarizers +
                ", detalis1=" + subject1 +
                ", detalis2=" + subject2 +
                '}';
    }

}
