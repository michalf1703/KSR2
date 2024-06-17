package ksr2.ksrproject2.logic.summarization;


import ksr2.ksrproject2.logic.calculation.sets.FuzzySet;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Label {
    private String name;
    private FuzzySet fuzzySet;
    private String linguisticVariableName;

    public double getMembership(double x) {
        return fuzzySet.getMembershipDegree(x);
    }

    }
