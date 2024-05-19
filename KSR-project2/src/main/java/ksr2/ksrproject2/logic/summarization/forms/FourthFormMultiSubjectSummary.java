package ksr2.ksrproject2.logic.summarization.forms;

import ksr2.ksrproject2.logic.model.PowerliftingResultDetalis;
import ksr2.ksrproject2.logic.summarization.Label;
import ksr2.ksrproject2.logic.summarization.MultiSubjectSummary;

import java.util.List;

public class FourthFormMultiSubjectSummary implements MultiSubjectSummary {

    private final List<Label> summarizers;
    private final List<PowerliftingResultDetalis> subject1;
    private final List<PowerliftingResultDetalis> subject2;

    public FourthFormMultiSubjectSummary(List<Label> summarizers, List<PowerliftingResultDetalis> subject1, List<PowerliftingResultDetalis> subject2) {
        this.summarizers = summarizers;
        this.subject1 = subject1;
        this.subject2 = subject2;
    }

    @Override
    public double getDeegreeOfTruthT1() {
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

    private double lukasiewiczImplication(double a, double b) {
        return Math.min(1, 1 - a + b);
    }


}
