package ksr2.ksrproject2.logic.summarization.forms;

import ksr2.ksrproject2.logic.model.PowerliftingResultDetalis;
import ksr2.ksrproject2.logic.summarization.Label;
import ksr2.ksrproject2.logic.summarization.MultiSubjectSummary;

import java.util.List;

public class FourthFormMultiSubjectSummary implements MultiSubjectSummary {

    private final List<Label> summarizers;
    private final List<PowerliftingResultDetalis> detalis1;
    private final List<PowerliftingResultDetalis> detalis2;

    public FourthFormMultiSubjectSummary(List<Label> summarizers, List<PowerliftingResultDetalis> detalis1, List<PowerliftingResultDetalis> detalis2) {
        this.summarizers = summarizers;
        this.detalis1 = detalis1;
        this.detalis2 = detalis2;
    }

    @Override
    public double getDeegreeOfTruthT1() {
        return 0;
    }

    @Override
    public String toString() {
        return "FourthFormMultiSubjectSummary{" +
                "summarizers=" + summarizers +
                ", detalis1=" + detalis1 +
                ", detalis2=" + detalis2 +
                '}';
    }

    private double lukasiewiczImplication(double a, double b) {
        return Math.min(1, 1 - a + b);
    }


}
